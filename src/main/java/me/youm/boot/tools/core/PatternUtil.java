package me.youm.boot.tools.core;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author youta
 */
public class PatternUtil {

    public static boolean matches(String pattern,String content){
        return Pattern.matches(getRegPath(pattern), content);
    }

    public static boolean matches(String pattern, List<String> content){
        for ( String item :content) {
            if(matches(getRegPath(pattern),item)){
                return true;
            }
        }
        return false;
    }

    public static boolean matches(List<String> pattern, String content){
        for ( String item : pattern) {
            if(matches(getRegPath(item),content)){
                return true;
            }
        }
        return false;
    }

    private static String getRegPath(String path) {
        char[] chars = path.toCharArray();
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        boolean preX = false;
        for(int i=0;i<len;i++){
            if (chars[i] == '*'){
                if (preX){
                    sb.append(".*");
                    preX = false;
                }else if(i+1 == len){
                    sb.append("[^/]*");
                }else{
                    preX = true;
                }
            }else{
                if (preX){
                    sb.append("[^/]*");
                    preX = false;
                }
                if (chars[i] == '?'){
                    sb.append('.');
                }else{
                    sb.append(chars[i]);
                }
            }
        }
        return sb.toString();
    }
}
