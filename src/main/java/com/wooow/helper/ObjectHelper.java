package com.wooow.helper;

/**
 * @author shenhaijin
 * @date 2025/3/12 10:36
 * @description :
 */
public class ObjectHelper {
    private ObjectHelper(){}

    public static boolean isNull(Object obj){
        return null == obj || obj.equals(null);
    }

    public static boolean isNotNull(Object obj){
        return null != obj && false == obj.equals(null);
    }

}
