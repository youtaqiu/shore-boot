package me.youm.boot.tools.core;


import me.youm.boot.constant.SystemConstant;

/**
 * Describe: String 工具类
 *
 * @author youta
 */
public class StringUtil {

    /**
     * 判断是否为空
     * @param text text
     * @return b
     */
    public static boolean isBlank(String text) {
        if (text.equals(SystemConstant.EMPTY)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为空
     * @param text text
     * @return b
     */
    public static boolean isEmpty(String text) {
        return text == null;
    }

    /**
     * 判断是否不为空
     * @param text text
     * @return b
     */
    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    /**
     * 判断是否不为空
     * @param text text
     * @return b
     */
    public static boolean isNotEmpty(String text) {
        return !isNotEmpty(text);
    }

}
