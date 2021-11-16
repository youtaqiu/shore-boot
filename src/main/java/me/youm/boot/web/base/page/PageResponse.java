package me.youm.boot.web.base.page;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分 页 结 果
 *
 * @author youta
 */
@Data
@NoArgsConstructor
public class PageResponse<T> {

    /**
     * 总页数
     */
    private long total;

    /**
     * 分页后的数据
     */
    private List<T> record;

    /**
     * 构建实体
     * @param total total
     * @param record records
     */
    public PageResponse(int total, List<T> record) {
        this.total = total;
        this.record = record;
    }

    /**
     * 构建实体
     * @param pageInfo pageInfo
     */
    public PageResponse(PageInfo<T> pageInfo) {
        this.record = pageInfo.getList();
        this.total = pageInfo.getTotal();
    }
}
