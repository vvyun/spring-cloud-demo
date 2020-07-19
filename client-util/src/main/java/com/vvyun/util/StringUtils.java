package com.vvyun.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

@SuppressWarnings("unused")
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 值为"NULL"的字符串
     */
    private static final String NULL_STRING = "NULL";

    private static final char SEPARATOR = '_';


    /**
     * 满足一下情况返回true<br/>
     * ①.入参为空
     * ②.入参为空字符串
     * ③.入参为"null"字符串
     *
     * @param string 需要判断的字符型
     * @return boolean
     */
    public static boolean isNullOrEmptyOrNULLString(String string) {
        return isBlank(string) || NULL_STRING.equalsIgnoreCase(string);
    }

    /**
     * 把字符串转为二进制码<br/>
     * 本方法不会返回null
     *
     * @param str 需要转换的字符串
     * @return 二进制字节码数组
     */
    public static byte[] toBytes(String str) {
        return isBlank(str) ? new byte[]{} : str.getBytes();
    }

    /**
     * 把字符串转为二进制码<br/>
     * 本方法不会返回null
     *
     * @param str     需要转换的字符串
     * @param charset 编码类型
     * @return 二进制字节码数组
     * @throws UnsupportedEncodingException 字符串转换的时候编码不支持时出现
     */
    public static byte[] toBytes(String str, Charset charset) throws UnsupportedEncodingException {
        return isBlank(str) ? new byte[]{} : str.getBytes(charset.displayName());
    }

    /**
     * 把字符串转为二进制码<br/>
     * 本方法不会返回null
     *
     * @param str     需要转换的字符串
     * @param charset 编码类型
     * @param locale  编码类型对应的地区
     * @return 二进制字节码数组
     * @throws UnsupportedEncodingException 字符串转换的时候编码不支持时出现
     */
    public static byte[] toBytes(String str, Charset charset, Locale locale) throws UnsupportedEncodingException {
        return isBlank(str) ? new byte[]{} : str.getBytes(charset.displayName(locale));
    }

    /**
     * 二进制码转字符串<br/>
     * 本方法不会返回null
     *
     * @param bytes 二进制码
     * @return 字符串
     */
    public static String bytesToString(byte[] bytes) {
        return bytes == null || bytes.length == 0 ? EMPTY : new String(bytes);
    }

    /**
     * 二进制码转字符串<br/>
     * 本方法不会返回null
     *
     * @param bytes   二进制码
     * @param charset 编码集
     * @return 字符串
     * @throws UnsupportedEncodingException 当前二进制码可能不支持传入的编码
     */
    public static String byteToString(byte[] bytes, Charset charset) throws UnsupportedEncodingException {
        return bytes == null || bytes.length == 0 ? EMPTY : new String(bytes, charset.displayName());
    }

    /**
     * 二进制码转字符串<br/>
     * 本方法不会返回null
     *
     * @param bytes   二进制码
     * @param charset 编码集
     * @param locale  本地化
     * @return 字符串
     * @throws UnsupportedEncodingException 当前二进制码可能不支持传入的编码
     */
    public static String byteToString(byte[] bytes, Charset charset, Locale locale) throws UnsupportedEncodingException {
        return bytes == null || bytes.length == 0 ? EMPTY : new String(bytes, charset.displayName(locale));
    }

    /**
     * 把对象转为字符串
     *
     * @param object 需要转化的字符串
     * @return 字符串, 可能为空
     */
    public static String parseString(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof byte[]) {
            return bytesToString((byte[]) object);
        }
        return object.toString();
    }

    /**
     * 把字符串转为int类型
     *
     * @param str 需要转化的字符串
     * @return int
     * @throws NumberFormatException 字符串格式不正确时抛出
     */
    public static int parseInt(String str) throws NumberFormatException {
        return isBlank(str) ? 0 : Integer.parseInt(str);
    }

    /**
     * 把字符串转为double类型
     *
     * @param str 需要转化的字符串
     * @return double
     * @throws NumberFormatException 字符串格式不正确时抛出
     */
    public static double parseDouble(String str) throws NumberFormatException {
        return isBlank(str) ? 0D : Double.parseDouble(str);
    }

    /**
     * 把字符串转为long类型
     *
     * @param str 需要转化的字符串
     * @return long
     * @throws NumberFormatException 字符串格式不正确时抛出
     */
    public static long parseLong(String str) throws NumberFormatException {
        return isBlank(str) ? 0L : Long.parseLong(str);
    }

    /**
     * 把字符串转为float类型
     *
     * @param str 需要转化的字符串
     * @return float
     * @throws NumberFormatException 字符串格式不正确时抛出
     */
    public static float parseFloat(String str) throws NumberFormatException {
        return isBlank(str) ? 0L : Float.parseFloat(str);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s, Locale locale, char split) {
        if (isBlank(s)) {
            return "";
        }

        s = s.toLowerCase(locale);

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c == split ? Character.toUpperCase(c) : c);
        }

        return sb.toString();
    }

    public static String toCamelCase(String s) {
        return toCamelCase(s, Locale.getDefault(), SEPARATOR);
    }

    public static String toCamelCase(String s, Locale locale) {
        return toCamelCase(s, locale, SEPARATOR);
    }

    public static String toCamelCase(String s, char split) {
        return toCamelCase(s, Locale.getDefault(), split);
    }

    public static String toUnderScoreCase(String s, char split) {
        if (isBlank(s)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUpperCase = (i < (s.length() - 1)) && Character.isUpperCase(s.charAt(i + 1));
            boolean upperCase = (i > 0) && Character.isUpperCase(c);
            sb.append((!upperCase || !nextUpperCase) ? split : "").append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    public static String toUnderScoreCase(String s) {
        return toUnderScoreCase(s, SEPARATOR);
    }

    /**
     * 把字符串转换为JS获取对象值的三目运算表达式
     *
     * @param objectString 对象串
     *                     例如：入参:row.user.id/返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String toJsGetValueExpression(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] fileds = split(objectString, ".");
        for (String filed : fileds) {
            val.append(".").append(filed);
            result.append("!").append(val.substring(1)).append("?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }
}
