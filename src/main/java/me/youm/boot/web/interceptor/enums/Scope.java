package me.youm.boot.web.interceptor.enums;

/**
 * Describe: 接口权限限制
 * @author youta
 * */
public enum Scope {

    /**
     * 全部
     * */
    ALL,

    /**
     * 自己
     * */
    SELF,

    /**
     * 部门
     * */
    DEPT,

    /**
     * 部门 及其 子部门
     * */
    DEPT_CHILD,

    /**
     * 自动
     * */
    AUTO,

    /**
     * 自定义
     * */
    CUSTOM

}
