package me.youm.boot.context.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.youm.boot.context.domain.SysDataSource;
import me.youm.boot.context.param.SysDataSourceRequest;
import me.youm.boot.web.base.page.PageResponse;

import java.util.List;

/**
 * @author youta
 */
public interface IDataSourceService extends IService<SysDataSource> {

    /**
     * 获取数据源列表
     *
     * @param request 查询参数
     *
     * @return {@link SysDataSource}
     * */
    List<SysDataSource> list(SysDataSourceRequest request);

    /**
     * 获取数据源列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysDataSource}
     * */
    PageResponse<SysDataSource> page(SysDataSourceRequest request);

}
