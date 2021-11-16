package me.youm.boot.web.base.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Tree 实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/24
 * */
@EqualsAndHashCode(callSuper = true)
@Data
public class TreeDomain<T> extends BaseDomain{

    /**
     * 父级编号
     * */
    @TableField("parent")
    private String parent;

    /**
     * 子级集合
     * */
    @TableField(exist = false)
    private List<T> children = new ArrayList<>();
}
