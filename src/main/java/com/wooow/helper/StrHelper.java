package com.wooow.helper;

import cn.hutool.core.util.CharUtil;

/**
 * @author shenhaijin
 * @date 2025/3/12 11:21
 * @description :
 */
public class StrHelper {

//    public static boolean isAllNotBlank(String... strs){
//        return false;
//    }
//
//    public static boolean hasBlank(String... strs){
//        if(strs == null || strs.length == 0){
//            return true;
//        }
//        for(String str : strs){
//            if(isNull(str) || str.trim().length() == 0){
//                return true;
//            }
//        }
//
//    }
//
    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return equals(str1, str2, true);
    }
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (null == str1) {
            // 只有两个都为null才判断相等
            return str2 == null;
        }
        if (null == str2) {
            // 字符串2空，字符串1非空，直接false
            return false;
        }
        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.toString().contentEquals(str2);
        }
    }
    public static boolean isBlank(String str){
        final int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!CharHelper.isBlankChar(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }
    public static String concat(boolean isNulltoEmpty,String... strs){
        StringBuilder builder = new StringBuilder();
        for(String str : strs){
            builder.append(isNulltoEmpty ? nullToEmpty(str) : str);
        }
        return builder.toString();
    }
    public static String nullToEmpty(String str){
        return isNull(str) ? "" : str;
    }
    public static boolean isNull(String str){
        return str == null;
    }
}
