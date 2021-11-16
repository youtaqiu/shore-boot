package me.youm.boot.secure.services;

import me.youm.boot.secure.domain.SysDept;

import java.util.List;

/**
 * @author xiyu
 **/
public interface IDeptService {

    /**
     * 查询部门列表
     * @return {@link SysDept}
     */
    List<SysDept> tree();

    /**
     * 根据 parent 查询部门列表
     *
     * @param parent 父级编号
     * @return {@link SysDept}
     */
    List<SysDept> treeAndChildren(String parent);

}
