package com.wooow.helper;

/**
 * @author shenhaijin
 * @date 2025/3/12 14:12
 * @description :
 */
public class CharHelper {
    public static boolean isBlankChar(char c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a'
                || c == '\u0000'
                || c == '\u3164'
                || c == '\u2800'
                || c == '\u180e';
    }
}
