package me.youm.boot.secure.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.youm.boot.web.base.domain.BaseDomain;
import me.youm.boot.web.interceptor.enums.Scope;

/**
 * @author youta
 */
@Data
@ApiModel("角色实体")
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseDomain {

    /**
     * 编号
     */
    @TableId("id")
    @ApiModelProperty("角色编号")
    private String id;

    /**
     * 名称
     */
    @TableField("name")
    @ApiModelProperty("角色名称")
    private String name;

    /**
     * 标识
     */
    @TableField("code")
    @ApiModelProperty("角色标识")
    private String code;

    /**
     * 启用
     */
    @TableField("enable")
    @ApiModelProperty("是否开启")
    private boolean enable;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 数据权限
     */
    @TableField("scope")
    @ApiModelProperty("数据权限")
    private Scope scope;

    /**
     * 租户编号
     * */
    @TableField("tenant_id")
    @ApiModelProperty("所属租户")
    private String tenantId;
}
