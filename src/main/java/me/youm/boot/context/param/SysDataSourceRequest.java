package me.youm.boot.context.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.youm.boot.web.base.page.PageRequest;

/**
 * @author youta
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDataSourceRequest extends PageRequest {

    /**
     * 多库名称
     * */
    private String name;

}
