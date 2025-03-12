package com.wooow.helper;

import java.io.Closeable;

/**
 * @author shenhaijin
 * @date 2025/3/12 14:17
 * @description :
 */
public class IoHelper {
    /**
     * 关闭流
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

    public static void close(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }
}
