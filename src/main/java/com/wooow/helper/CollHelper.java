package com.wooow.helper;

import java.util.Collection;

/**
 * @author shenhaijin
 * @date 2025/3/12 14:10
 * @description :
 */
public class CollHelper {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
