package me.youm.boot.web.interceptor;

import me.youm.boot.constant.SystemConstant;
import me.youm.boot.context.BeanContext;
import me.youm.boot.context.UserContext;
import me.youm.boot.secure.domain.SysDept;
import me.youm.boot.secure.domain.SysRole;
import me.youm.boot.secure.services.IDeptService;
import me.youm.boot.secure.services.IUserService;
import me.youm.boot.web.interceptor.annotation.DataScope;
import me.youm.boot.web.interceptor.annotation.DataScopeRule;
import me.youm.boot.web.interceptor.enums.Scope;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.util.Strings;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数 据 权 限 拦 截 器
 *
 * @author youta
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class DataScopeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        UserContext userContext = BeanContext.getBean(UserContext.class);
        try {
            MappedStatement mappedStatement = InvocationHandler.getMappedStatement(invocation);
            DataScope annotation = getAnnotation(mappedStatement);
            if (annotation != null) {

                String sql = InvocationHandler.getSql(invocation);
                DataScopeRule[] rules = annotation.rules();

                sql = preHandler(sql);
                String where = SystemConstant.EMPTY;

                if (rules.length > 0) {
                    //规 则 模 式
                    List<SysRole> roles = userContext.getRoles();
                    List<Scope> scopes = getScope(roles, rules);

                    for (Scope scopeItem : scopes) {
                        where += sqlHandler(scopeItem);
                    }
                } else {
                    Scope scope = annotation.scope();
                    if (Scope.AUTO.equals(scope)) {
                        //自 动 模 式
                        List<SysRole> roles = userContext.getRoles();
                        List<Scope> scopes = getScope(roles);
                        for (Scope scopeItem : scopes) {
                            where += sqlHandler(scopeItem);
                        }
                    } else {
                        //指 定 模 式
                        where += sqlHandler(scope);
                    }
                }
                sql = aftHandler(sql, where);
                InvocationHandler.setSql(invocation, sql);
            }
        } catch (NullPointerException e) {
            // TODO 当 userId 表示非 request 执行 SQL
        }
        return invocation.proceed();
    }

    /**
     * 获 取 权 限
     *
     * @param roles 角色列表
     */
    private List<Scope> getScope(List<SysRole> roles) {
        return roles.stream().map(SysRole::getScope).collect(Collectors.toList());
    }

    /**
     * 获 取 权 限
     *
     * @param roles 角色列表
     * @param rules 规则列表
     */
    private List<Scope> getScope(List<SysRole> roles, DataScopeRule[] rules) {
        List<Scope> scopes = new ArrayList<>();
        for (SysRole role : roles) {
            for (DataScopeRule rule : rules) {
                if (role.getCode().equals(rule.role())) {
                    scopes.add(rule.scope());
                }
            }
        }
        return scopes;
    }

    /**
     * 前置处理 Sql 规范
     */
    private String preHandler(String sql) {
        if (sql.contains("order")) sql += " limit 9999";
        sql = "select * from (" + sql + ") data left join sys_user b on b.id = data.create_by";
        return sql;
    }

    /**
     * 后置处理 Sql 规范
     */
    private String aftHandler(String sql, String where) {
        if (Strings.isNotBlank(where)) {
            where = where.replaceFirst("or", "");
            sql += " where " + where;
        }
        return sql;
    }

    /**
     * 处理 Sql 权限
     */
    private String sqlHandler(Scope scope) {
        IDeptService deptService = BeanContext.getBean(IDeptService.class);
        IUserService userService = BeanContext.getBean(IUserService.class);
        UserContext userContext = BeanContext.getBean(UserContext.class);
        String userId = userContext.getUserId();
        String deptId = userContext.getDeptId();
        if (Scope.SELF.equals(scope)) {
            return "or data.create_by = " + userId;
        } else if (Scope.DEPT.equals(scope)) {
            return "or b.dept_id = " + deptId;
        } else if (Scope.DEPT_CHILD.equals(scope)) {
            return "or b.dept_id in (" + convertDept(deptService.treeAndChildren(deptId)) + ")";
        } else if (Scope.CUSTOM.equals(scope)) {
            return "or b.dept_id in (" + convertDept(userService.dept(userId)) + ")";
        } else {
            return SystemConstant.EMPTY;
        }
    }

    /**
     * 获取权限注解
     */
    private DataScope getAnnotation(MappedStatement mappedStatement) throws ClassNotFoundException {
        DataScope dataAuth = null;
        String id = mappedStatement.getId();
        String className = id.substring(0, id.lastIndexOf("."));
        String methodName = id.substring(id.lastIndexOf(".") + 1, id.length());
        final Class<?> cls = Class.forName(className);
        final Method[] method = cls.getMethods();
        for (Method me : method) {
            if (me.getName().equals(methodName)) {
                dataAuth = me.getAnnotation(DataScope.class);
            }
        }
        return dataAuth;
    }

    /**
     * 格式 Convert
     */
    private String convertDept(List<SysDept> deptList) {
        List<String> deptIds = deptList.stream().map(d -> d.getId()).collect(Collectors.toList());
        return StringUtils.join(deptIds, ",");
    }
}
