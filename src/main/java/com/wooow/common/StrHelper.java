//package com.wooow.common;
//
///**
// * @author shenhaijin
// * @date 2025/3/12 11:21
// * @description :
// */
//public class StrHelper {
//
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
//    public static boolean isBlank(String str){
//        return false;
//    }
//    public static String concat(boolean isNulltoEmpty,String... strs){
//        StringBuilder builder = new StringBuilder();
//        for(String str : strs){
//            builder.append(isNulltoEmpty ? nullToEmpty(str) : str);
//        }
//        return builder.toString();
//    }
//    public static String nullToEmpty(String str){
//        return isNull(str) ? "" : str;
//    }
//    public static boolean isNull(String str){
//        return str == null;
//    }
//}
