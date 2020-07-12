package com.uaa.auth.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class StringUtil
{
    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;
    private static final int PAD_LIMIT = 8192;

    public static String addSplit(String str, String separatorChars)
    {
        return separatorChars + str + separatorChars;
    }

    public static String TranRealLong(long value)
    {
        return toString((int) Math.floor(value / 10)) + "." + toString(value % 10);
    }

    public static String TranRealWeight(long value)
    {
        long l = value % 1000;
        if (l == 0)
        {
            return toString(Math.floor(value / 1000));
        }
        else
        {
            return toString((int) Math.floor(value / 1000)) + "." + toString((int) Math.ceil(l / 100));
        }
    }

    public static String[] split(String str)
    {
        return split(str, null, -1);
    }

    public static String[] split(String str, char separatorChar)
    {
        return splitWorker(str, separatorChar, false);
    }

    public static String[] split(String str, String separatorChars)
    {
        return splitWorker(str, separatorChars, -1, false);
    }

    public static String[] split(String str, String separatorChars, int max)
    {
        return splitWorker(str, separatorChars, max, false);
    }

    public static String[] splitByWholeSeparator(String str, String separator)
    {
        return splitByWholeSeparatorWorker(str, separator, -1, false);
    }

    public static String[] splitByWholeSeparator(String str, String separator, int max)
    {
        return splitByWholeSeparatorWorker(str, separator, max, false);
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator)
    {
        return splitByWholeSeparatorWorker(str, separator, -1, true);
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max)
    {
        return splitByWholeSeparatorWorker(str, separator, max, true);
    }

    private static String[] splitByWholeSeparatorWorker(String str, String separator, int max, boolean preserveAllTokens)
    {
        if (str == null)
        {
            return null;
        }
        int len = str.length();
        if (len == 0)
        {
            return null;
        }
        if (separator == null || "".equals(separator))
        {
            return splitWorker(str, null, max, preserveAllTokens);
        }
        int separatorLength = separator.length();
        ArrayList substrings = new ArrayList();
        int numberOfSubstrings = 0;
        int beg = 0;
        for (int end = 0; end < len; )
        {
            end = str.indexOf(separator, beg);
            if (end > -1)
            {
                if (end > beg)
                {
                    if (++numberOfSubstrings == max)
                    {
                        end = len;
                        substrings.add(str.substring(beg));
                    }
                    else
                    {
                        substrings.add(str.substring(beg, end));
                        beg = end + separatorLength;
                    }
                }
                else
                {
                    if (preserveAllTokens)
                    {
                        if (++numberOfSubstrings == max)
                        {
                            end = len;
                            substrings.add(str.substring(beg));
                        }
                        else
                        {
                            substrings.add("");
                        }
                    }
                    beg = end + separatorLength;
                }
            }
            else
            {
                substrings.add(str.substring(beg));
                end = len;
            }
        }

        return (String[]) (String[]) substrings.toArray(new String[substrings.size()]);
    }

    public static String[] splitPreserveAllTokens(String str)
    {
        return splitWorker(str, null, -1, true);
    }

    public static String[] splitPreserveAllTokens(String str, char separatorChar)
    {
        return splitWorker(str, separatorChar, true);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens)
    {
        if (str == null)
        {
            return null;
        }
        int len = str.length();
        if (len == 0)
        {
            return null;
        }
        List list = new ArrayList();
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len)
        {
            if (str.charAt(i) == separatorChar)
            {
                if (match || preserveAllTokens)
                {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
            }
            else
            {
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch)
        {
            list.add(str.substring(start, i));
        }
        return (String[]) (String[]) list.toArray(new String[list.size()]);
    }

    public static String[] splitPreserveAllTokens(String str, String separatorChars)
    {
        return splitWorker(str, separatorChars, -1, true);
    }

    public static String[] splitPreserveAllTokens(String str, String separatorChars, int max)
    {
        return splitWorker(str, separatorChars, max, true);
    }

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens)
    {
        if (str == null)
        {
            return null;
        }
        int len = str.length();
        if (len == 0)
        {
            return null;
        }
        List list = new ArrayList();
        int sizePlus1 = 1;
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null)
        {
            while (i < len)
            {
                if (Character.isWhitespace(str.charAt(i)))
                {
                    if (match || preserveAllTokens)
                    {
                        lastMatch = true;
                        if (sizePlus1++ == max)
                        {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                }
                else
                {
                    lastMatch = false;
                    match = true;
                    i++;
                }
            }
        }
        else if (separatorChars.length() == 1)
        {
            char sep = separatorChars.charAt(0);
            while (i < len)
            {
                if (str.charAt(i) == sep)
                {
                    if (match || preserveAllTokens)
                    {
                        lastMatch = true;
                        if (sizePlus1++ == max)
                        {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                }
                else
                {
                    lastMatch = false;
                    match = true;
                    i++;
                }
            }
        }
        else
        {
            while (i < len)
            {
                if (separatorChars.indexOf(str.charAt(i)) >= 0)
                {
                    if (match || preserveAllTokens)
                    {
                        lastMatch = true;
                        if (sizePlus1++ == max)
                        {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                }
                else
                {
                    lastMatch = false;
                    match = true;
                    i++;
                }
            }
        }
        if (match || preserveAllTokens && lastMatch)
        {
            list.add(str.substring(start, i));
        }
        return (String[]) (String[]) list.toArray(new String[list.size()]);
    }

    public static String[] splitByCharacterType(String str)
    {
        return splitByCharacterType(str, false);
    }

    public static String[] splitByCharacterTypeCamelCase(String str)
    {
        return splitByCharacterType(str, true);
    }

    private static String[] splitByCharacterType(String str, boolean camelCase)
    {
        if (str == null)
        {
            return null;
        }
        if (str.length() == 0)
        {
            return null;
        }
        char c[] = str.toCharArray();
        List list = new ArrayList();
        int tokenStart = 0;
        int currentType = Character.getType(c[tokenStart]);
        for (int pos = tokenStart + 1; pos < c.length; pos++)
        {
            int type = Character.getType(c[pos]);
            if (type == currentType)
            {
                continue;
            }
            if (camelCase && type == 2 && currentType == 1)
            {
                int newTokenStart = pos - 1;
                if (newTokenStart != tokenStart)
                {
                    list.add(new String(c, tokenStart, newTokenStart - tokenStart));
                    tokenStart = newTokenStart;
                }
            }
            else
            {
                list.add(new String(c, tokenStart, pos - tokenStart));
                tokenStart = pos;
            }
            currentType = type;
        }

        list.add(new String(c, tokenStart, c.length - tokenStart));
        return (String[]) (String[]) list.toArray(new String[list.size()]);
    }

    private static final String _BR = "<br/>";


    /**
     * 替换字符串，将被编码的转换成原始码（替换成双引号和小于号）
     *
     * @param str String
     * @return String
     */
    public static String htmldecode(String str)
    {
        if (str == null)
        {
            return null;
        }

        return replace("&quot;", "\"", replace("&lt;", "<", str));
    }

    /**
     * 替换字符串，能能够在HTML页面上直接显示(替换双引号和小于号)
     *
     * @param str String 原始字符串
     * @return String 替换后的字符串
     */
    public static String htmlencode(String str)
    {
        if (str == null)
        {
            return null;
        }

        return replace("\"", "&quot;", replace("<", "&lt;", str));
    }

    /**
     * 在页面上直接显示文本内容，替换小于号，空格，回车，TAB
     *
     * @param str String 原始字符串
     * @return String 替换后的字符串
     */
    public static String htmlshow(String str)
    {
        if (str == null)
        {
            return null;
        }

        str = replace("<", "&lt;", str);
        str = replace(" ", "&nbsp;", str);
        str = replace("\r\n", _BR, str);
        str = replace("\n", _BR, str);
        str = replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;", str);
        return str;
    }

    public static boolean isEmpty(String str)
    {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str)
    {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotBlank(String str)
    {
        return !isBlank(str);
    }

    /**
     * 判断输入的字符串是否为纯汉字
     *
     * @param str 传入的字符窜
     * @return 如果是纯汉字返回true, 否则返回false
     */
    public static boolean isChinese(String str)
    {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     *
     * @param str 传入的字符串
     * @return 是浮点数返回true, 否则返回false
     */
    public static boolean isDouble(String str)
    {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断输入的字符串是否符合Email样式.
     *
     * @param str 传入的字符串
     * @return 是Email样式返回true, 否则返回false
     */
    public static boolean isEmail(String str)
    {
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str)
    {
        if (str == null)
        {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为质数
     *
     * @param x
     * @return
     */
    public static boolean isPrime(int x)
    {
        if (x <= 7)
        {
            if (x == 2 || x == 3 || x == 5 || x == 7)
            {
                return true;
            }
        }
        int c = 7;
        if (x % 2 == 0)
        {
            return false;
        }
        if (x % 3 == 0)
        {
            return false;
        }
        if (x % 5 == 0)
        {
            return false;
        }
        int end = (int) Math.sqrt(x);
        while (c <= end)
        {
            if (x % c == 0)
            {
                return false;
            }
            c += 4;
            if (x % c == 0)
            {
                return false;
            }
            c += 2;
            if (x % c == 0)
            {
                return false;
            }
            c += 4;
            if (x % c == 0)
            {
                return false;
            }
            c += 2;
            if (x % c == 0)
            {
                return false;
            }
            c += 4;
            if (x % c == 0)
            {
                return false;
            }
            c += 6;
            if (x % c == 0)
            {
                return false;
            }
            c += 2;
            if (x % c == 0)
            {
                return false;
            }
            c += 6;
        }
        return true;
    }
/**
 * 删除String start  - 起始索引(包括),end - 结束索引(不包括)。
 */
public static String delete(String str, int start, int end){
    if (isEmpty(str))
    {
        return str;
    }
    StringBuffer sb = new StringBuffer(str);
    return String.valueOf(sb.delete(start,end));
}


    /**
     * 去掉字符串中重复的子字符串
     *
     * @param str
     * @return String
     */
    public static String removeSameString(String str)
    {
        Set<String> mLinkedSet = new LinkedHashSet<String>();
        String[] strArray = str.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < strArray.length; i++)
        {
            if (!mLinkedSet.contains(strArray[i]))
            {
                mLinkedSet.add(strArray[i]);
                sb.append(strArray[i] + " ");
            }
        }
        System.out.println(mLinkedSet);
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 从指定的字符串中提取Email content 指定的字符串
     *
     * @param content
     * @return
     */
    public static String parse(String content)
    {
        String email = null;
        if (content == null || content.length() < 1)
        {
            return email;
        }
        // 找出含有@
        int beginPos;
        int i;
        String token = "@";
        String preHalf = "";
        String sufHalf = "";

        beginPos = content.indexOf(token);
        if (beginPos > -1)
        {
            // 前项扫描
            String s = null;
            i = beginPos;
            while (i > 0)
            {
                s = content.substring(i - 1, i);
                if (isLetter(s))
                {
                    preHalf = s + preHalf;
                }
                else
                {
                    break;
                }
                i--;
            }
            // 后项扫描
            i = beginPos + 1;
            while (i < content.length())
            {
                s = content.substring(i, i + 1);
                if (isLetter(s))
                {
                    sufHalf = sufHalf + s;
                }
                else
                {
                    break;
                }
                i++;
            }
            // 判断合法性
            email = preHalf + "@" + sufHalf;
            if (isEmail(email))
            {
                return email;
            }
        }
        return null;
    }

    /**
     * 返回指定字节长度的字符串
     *
     * @param str    String 字符串
     * @param length int 指定长度
     * @return String 返回的字符串
     */
    public static String toLength(String str, int length)
    {
        if (str == null)
        {
            return null;
        }
        if (length <= 0)
        {
            return "";
        }
        try
        {
            if (str.getBytes("GBK").length <= length)
            {
                return str;
            }
        }
        catch (Exception ex)
        {
        }
        StringBuffer buff = new StringBuffer();

        int index = 0;
        char c;
        length -= 3;
        while (length > 0)
        {
            c = str.charAt(index);
            if (c < 128)
            {
                length--;
            }
            else
            {
                length--;
                length--;
            }
            buff.append(c);
            index++;
        }
        buff.append("...");
        return buff.toString();
    }

    /**
     * 将带一位小数实型转换成百分号
     */
    public static String TranPercent(double value)
    {
        Long IntValue;
        IntValue = Math.round(value * 1000);
        return Long.toString(IntValue / 10).trim() + "." + Long.toString(IntValue % 10).trim() + "%";
    }

    /**
     * 将带一位小数实型转换成百分号
     */
    public static String TranPercent(float value)
    {
        int IntValue;
        IntValue = Math.round(value * 1000);
        return Integer.toString(IntValue / 10).trim() + "." + Integer.toString(IntValue % 10).trim() + "%";
    }

    /**
     * 将带一位小数实型转换成字符，如果为0返回空串
     */
    public static String TranReal(double value)
    {
        Long IntValue;
        IntValue = Math.round(value * 10);
        if (IntValue == 0)
        {
            return "";
        }
        return Long.toString(IntValue / 10).trim() + "." + Long.toString(IntValue % 10).trim();
    }

    /**
     * 将带一位小数实型转换成字符，如果为0返回空串
     */
    public static String TranReal(float value)
    {
        int IntValue;
        IntValue = Math.round(value * 10);
        if (IntValue == 0)
        {
            return "";
        }
        return Integer.toString(IntValue / 10).trim() + "." + Integer.toString(IntValue % 10).trim();
    }

    /**
     * 往字符串前填充指定字符
     */
    public static String PrefixFillChar(String Source, String Character, int Len)
    {
        int count;
        count = getWordCount(Source);
        StringBuffer ExtentStr = new StringBuffer();
        for (int i = 0; i <= Len - count - 1; i++)
        {
            ExtentStr.append(Character);
        }
        ExtentStr.append(Source);
        return ExtentStr.toString();
    }

    /**
     * 往字符串后填充指定字符
     */
    public static String SuffixFillChar(String Source, String Character, int Len)
    {
        int count;
        count = getWordCount(Source);
        StringBuffer ExtentStr = new StringBuffer();
        ExtentStr.append(Source);
        for (int i = 0; i <= Len - count - 1; i++)
        {
            ExtentStr.append(Character);
        }
        return ExtentStr.toString();
    }

    /**
     * 往字符串前后填充到指定长度
     */
    public static String MiddleChar(String Source, String Character, int Len)
    {
        int left, right;
        int count;
        count = getWordCount(Source);
        if (count > Len)
        {
            return Source;
        }
        left = (Len - count) / 2;
        if (left == 0)
        {
            left = 1;
        }
        right = Len - count - left;
        StringBuffer ExtentStr = new StringBuffer();
        ExtentStr.append(PrefixFillChar("", Character, left));
        ExtentStr.append(Source);
        ExtentStr.append(PrefixFillChar("", Character, right));
        return ExtentStr.toString();
    }

    /**
     * 判断是不是合法字符 c 要判断的字符
     */
    public static boolean isLetter(String c)
    {
        boolean result = false;
        if (c == null || c.length() < 0)
        {
            return false;
        }
        // a-z
        if (c.compareToIgnoreCase("a") >= 0 && c.compareToIgnoreCase("z") <= 0)
        {
            return true;
        }
        // 0-9
        if (c.compareToIgnoreCase("0") >= 0 && c.compareToIgnoreCase("9") <= 0)
        {
            return true;
        }
        // . - _
        if (c.equals(".") || c.equals("-") || c.equals("_"))
        {
            return true;
        }
        return result;
    }

    /**
     * 功能描述：过滤特殊字符
     *
     * @param src
     * @return
     */
    public static String encoding(String src)
    {
        if (src == null)
        {
            return "";
        }
        StringBuilder result = new StringBuilder();
        if (src != null)
        {
            src = src.trim();
            for (int pos = 0; pos < src.length(); pos++)
            {
                switch (src.charAt(pos))
                {
                    case '\"':
                        result.append("&quot;");
                        break;
                    case '<':
                        result.append("&lt;");
                        break;
                    case '>':
                        result.append("&gt;");
                        break;
                    case '\'':
                        result.append("&apos;");
                        break;
                    case '&':
                        result.append("&amp;");
                        break;
                    case '%':
                        result.append("&pc;");
                        break;
                    case '_':
                        result.append("&ul;");
                        break;
                    case '#':
                        result.append("&shap;");
                        break;
                    case '?':
                        result.append("&ques;");
                        break;
                    default:
                        result.append(src.charAt(pos));
                        break;
                }
            }
        }
        return result.toString();
    }

    /**
     * 功能描述：判断是不是合法的手机号码
     *
     * @param handset
     * @return boolean
     */
    public static boolean isHandset(String handset)
    {
        try
        {
            String regex = "^1[\\d]{10}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(handset);
            return matcher.matches();

        }
        catch (RuntimeException e)
        {
            return false;
        }
    }

    /**
     * 功能描述：反过滤特殊字符
     *
     * @param src
     * @return
     */
    public static String decoding(String src)
    {
        if (src == null)
        {
            return "";
        }
        String result = src;
        result = result.replace("&quot;", "\"").replace("&apos;", "\'");
        result = result.replace("&lt;", "<").replace("&gt;", ">");
        result = result.replace("&amp;", "&");
        result = result.replace("&pc;", "%").replace("&ul", "_");
        result = result.replace("&shap;", "#").replace("&ques", "?");
        return result;
    }

    /**
     * 提取长度超过5位连续数字
     *
     * @param intputStr
     * @return
     */
    public static String Continumax(String intputStr)
    {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(intputStr);
        while (matcher.find())
        {
            if (!"".equals(matcher.group()))
            {
                if (matcher.group().length() >= 5)
                {
                    return matcher.group();
                }
            }
        }
        return "";
    }

    public static String substring(final String str, int start)
    {
        if (str == null)
        {
            return null;
        }

        // handle negatives, which means last n characters
        if (start < 0)
        {
            start = str.length() + start; // remember start is negative
        }

        if (start < 0)
        {
            start = 0;
        }
        if (start > str.length())
        {
            return EMPTY;
        }

        return str.substring(start);
    }

    public static String subString(String text, int length, String endWith)
    {
        int textLength = text.length();
        int byteLength = 0;
        StringBuffer returnStr = new StringBuffer();
        for (int i = 0; i < textLength && byteLength < length * 2; i++)
        {
            String str_i = text.substring(i, i + 1);
            if (str_i.getBytes().length == 1)
            {//英文
                byteLength++;
            }
            else
            {//中文
                byteLength += 2;
            }
            returnStr.append(str_i);
        }
        try
        {
            if (byteLength < text.getBytes("GBK").length)
            {//getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3
                returnStr.append(endWith);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return returnStr.toString();
    }

    public static String trim(String str)
    {
        return str != null ? str.trim() : null;
    }

    public static String trimToNull(String str)
    {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(String str)
    {
        return str != null ? str.trim() : "";
    }

    public static String strip(String str)
    {
        return strip(str, null);
    }

    public static String stripToNull(String str)
    {
        if (str == null)
        {
            return null;
        }
        else
        {
            str = strip(str, null);
            return str.length() != 0 ? str : null;
        }
    }

    public static String stripToEmpty(String str)
    {
        return str != null ? strip(str, null) : "";
    }

    public static String strip(String str, String stripChars)
    {
        if (isEmpty(str))
        {
            return str;
        }
        else
        {
            str = stripStart(str, stripChars);
            return stripEnd(str, stripChars);
        }
    }

    /**
     * * <pre>
     * StringUtils.stripStart(null, *)          = null
     * StringUtils.stripStart("", *)            = ""
     * StringUtils.stripStart("abc", "")        = "abc"
     * StringUtils.stripStart("abc", null)      = "abc"
     * StringUtils.stripStart("  abc", null)    = "abc"
     * StringUtils.stripStart("abc  ", null)    = "abc  "
     * StringUtils.stripStart(" abc ", null)    = "abc "
     * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str
     * @param stripChars
     * @return
     */
    public static String stripStart(String str, String stripChars)
    {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
        {
            return str;
        }
        int start = 0;
        if (stripChars == null)
        {
            for (; start != strLen && Character.isWhitespace(str.charAt(start)); start++)
            {
                ;
            }
        }
        else
        {
            if (stripChars.length() == 0)
            {
                return str;
            }
            for (; start != strLen && stripChars.indexOf(str.charAt(start)) != -1; start++)
            {
                ;
            }
        }
        return str.substring(start);
    }

    /**
     * StringUtils.stripEnd(null, *)          = null
     * StringUtils.stripEnd("", *)            = ""
     * StringUtils.stripEnd("abc", "")        = "abc"
     * StringUtils.stripEnd("abc", null)      = "abc"
     * StringUtils.stripEnd("  abc", null)    = "  abc"
     * StringUtils.stripEnd("abc  ", null)    = "abc"
     * StringUtils.stripEnd(" abc ", null)    = " abc"
     * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
     * StringUtils.stripEnd("120.00", ".0")   = "12"s
     *
     * @param str
     * @param stripChars
     * @return
     */
    public static String stripEnd(String str, String stripChars)
    {
        int end;
        if (str == null || (end = str.length()) == 0)
        {
            return str;
        }
        if (stripChars == null)
        {
            for (; end != 0 && Character.isWhitespace(str.charAt(end - 1)); end--)
            {
                ;
            }
        }
        else
        {
            if (stripChars.length() == 0)
            {
                return str;
            }
            for (; end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1; end--)
            {
                ;
            }
        }
        return str.substring(0, end);
    }

    public static String[] stripAll(String strs[])
    {
        return stripAll(strs, null);
    }

    public static String[] stripAll(String strs[], String stripChars)
    {
        int strsLen;
        if (strs == null || (strsLen = strs.length) == 0)
        {
            return strs;
        }
        String newArr[] = new String[strsLen];
        for (int i = 0; i < strsLen; i++)
        {
            newArr[i] = strip(strs[i], stripChars);
        }

        return newArr;
    }

    public static boolean equals(String str1, String str2)
    {
        return str1 != null ? str1.equals(str2) : str2 == null;
    }

    //特征码分析使用
    public static boolean equals(String str1, String[] str2)
    {
        if (str1 != null && str2 != null)
        {
            for (String s : str2)
            {
                if (str1.equals(s))
                {
                    return true;
                }
            }
            return false;
        }
        else
        {
            return str1 == null && str2 == null;
        }
    }

    //特征码分析使用
    public static boolean contains(String str, String[] searchStr)
    {
        if (str != null && searchStr != null)
        {
            for (String s : searchStr)
            {
                if (str.contains(s))
                {
                    return true;
                }
            }
            return false;
        }
        else
        {
            return str == null && searchStr == null;
        }
    }

    //特征码分析使用
    public static boolean isFirstindex(String str, String[] searchStr)
    {
        if (str != null && searchStr != null)
        {
            for (String s : searchStr)
            {
                if (str.indexOf(s) == 0)
                {
                    return true;
                }
            }
            return false;
        }
        else
        {
            return false;
        }
    }

    public static String strNullFormat(String str)
    {
        if (StringUtil.equals(str,"null"))
        {
            str = "";
        }
        return str;
    }
    public static Integer numNullFormat(String str)
    {
        if (StringUtil.equals(str,"null"))
        {
            return 0;
        }
        return Integer.valueOf(str);
    }

    public static boolean equalsIgnoreCase(String str1, String str2)
    {
        return str1 != null ? str1.equalsIgnoreCase(str2) : str2 == null;
    }

    /**
     * <pre>
     * StringUtils.indexOf(null, *)         = -1
     * StringUtils.indexOf("", *)           = -1
     * StringUtils.indexOf("aabaabaa", 'a') = 0
     * StringUtils.indexOf("aabaabaa", 'b') = 2
     * </pre>
     *
     * @param str
     * @param searchChar
     * @return
     */
    public static int indexOf(String str, char searchChar)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        else
        {
            return str.indexOf(searchChar);
        }
    }

    /**
     * <pre>
     * StringUtils.indexOf(null, *, *)          = -1
     * StringUtils.indexOf("", *, *)            = -1
     * StringUtils.indexOf("aabaabaa", 'b', 0)  = 2
     * StringUtils.indexOf("aabaabaa", 'b', 3)  = 5
     * StringUtils.indexOf("aabaabaa", 'b', 9)  = -1
     * StringUtils.indexOf("aabaabaa", 'b', -1) = 2
     * </pre>
     *
     * @param str
     * @param searchChar
     * @param startPos
     * @return
     */
    public static int indexOf(String str, char searchChar, int startPos)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        else
        {
            return str.indexOf(searchChar, startPos);
        }
    }

    public static int indexOf(String str, String searchStr)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        else
        {
            return str.indexOf(searchStr);
        }
    }

    public static int ordinalIndexOf(String str, String searchStr, int ordinal)
    {
        return ordinalIndexOf(str, searchStr, ordinal, false);
    }

    private static int ordinalIndexOf(String str, String searchStr, int ordinal, boolean lastIndex)
    {
        if (str == null || searchStr == null || ordinal <= 0)
        {
            return -1;
        }
        if (searchStr.length() == 0)
        {
            return lastIndex ? str.length() : 0;
        }
        int found = 0;
        int index = lastIndex ? str.length() : -1;
        do
        {
            if (lastIndex)
            {
                index = str.lastIndexOf(searchStr, index - 1);
            }
            else
            {
                index = str.indexOf(searchStr, index + 1);
            }
            if (index < 0)
            {
                return index;
            }
        } while (++found < ordinal);
        return index;
    }

    public static int indexOf(String str, String searchStr, int startPos)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        if (searchStr.length() == 0 && startPos >= str.length())
        {
            return str.length();
        }
        else
        {
            return str.indexOf(searchStr, startPos);
        }
    }

    public static int indexOfIgnoreCase(String str, String searchStr)
    {
        return indexOfIgnoreCase(str, searchStr, 0);
    }

    public static int indexOfIgnoreCase(String str, String searchStr, int startPos)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        if (startPos < 0)
        {
            startPos = 0;
        }
        int endLimit = (str.length() - searchStr.length()) + 1;
        if (startPos > endLimit)
        {
            return -1;
        }
        if (searchStr.length() == 0)
        {
            return startPos;
        }
        for (int i = startPos; i < endLimit; i++)
        {
            if (str.regionMatches(true, i, searchStr, 0, searchStr.length()))
            {
                return i;
            }
        }

        return -1;
    }

    public static int lastIndexOf(String str, char searchChar)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        else
        {
            return str.lastIndexOf(searchChar);
        }
    }

    public static int lastIndexOf(String str, char searchChar, int startPos)
    {
        if (isEmpty(str))
        {
            return -1;
        }
        else
        {
            return str.lastIndexOf(searchChar, startPos);
        }
    }

    public static int lastIndexOf(String str, String searchStr)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        else
        {
            return str.lastIndexOf(searchStr);
        }
    }

    public static int lastOrdinalIndexOf(String str, String searchStr, int ordinal)
    {
        return ordinalIndexOf(str, searchStr, ordinal, true);
    }

    public static int lastIndexOf(String str, String searchStr, int startPos)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        else
        {
            return str.lastIndexOf(searchStr, startPos);
        }
    }

    public static int lastIndexOfIgnoreCase(String str, String searchStr)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        else
        {
            return lastIndexOfIgnoreCase(str, searchStr, str.length());
        }
    }

    public static int lastIndexOfIgnoreCase(String str, String searchStr, int startPos)
    {
        if (str == null || searchStr == null)
        {
            return -1;
        }
        if (startPos > str.length() - searchStr.length())
        {
            startPos = str.length() - searchStr.length();
        }
        if (startPos < 0)
        {
            return -1;
        }
        if (searchStr.length() == 0)
        {
            return startPos;
        }
        for (int i = startPos; i >= 0; i--)
        {
            if (str.regionMatches(true, i, searchStr, 0, searchStr.length()))
            {
                return i;
            }
        }

        return -1;
    }

    public static boolean contains(String str, char searchChar)
    {
        return !isEmpty(str) && str.indexOf(searchChar) >= 0;
    }

    public static boolean contains(String str, String searchStr)
    {
        return !(str == null || searchStr == null) && str.indexOf(searchStr) >= 0;
    }

    public static boolean containsIgnoreCase(String str, String searchStr)
    {
        if (str == null || searchStr == null)
        {
            return false;
        }
        int len = searchStr.length();
        int max = str.length() - len;
        for (int i = 0; i <= max; i++)
        {
            if (str.regionMatches(true, i, searchStr, 0, len))
            {
                return true;
            }
        }

        return false;
    }


    public static boolean containsAny(String str, char searchChars[])
    {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0)
        {
            return false;
        }
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            for (char searchChar : searchChars)
            {
                if (searchChar == ch)
                {
                    return true;
                }
            }

        }

        return false;
    }

    public static boolean containsAny(String str, String searchChars)
    {
        return searchChars != null && containsAny(str, searchChars.toCharArray());
    }

    public static String substring(String str, int start, int end)
    {
        if (str == null)
        {
            return null;
        }
        if (end < 0)
        {
            end = str.length() + end;
        }
        if (start < 0)
        {
            start = str.length() + start;
        }
        if (end > str.length())
        {
            end = str.length();
        }
        if (start > end)
        {
            return "";
        }
        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }
        return str.substring(start, end);
    }

    public static String left(String str, int len)
    {
        if (str == null)
        {
            return null;
        }
        if (len < 0)
        {
            return "";
        }
        if (str.length() <= len)
        {
            return str;
        }
        else
        {
            return str.substring(0, len);
        }
    }

    public static String right(String str, int len)
    {
        if (str == null)
        {
            return null;
        }
        if (len < 0)
        {
            return "";
        }
        if (str.length() <= len)
        {
            return str;
        }
        else
        {
            return str.substring(str.length() - len);
        }
    }

    public static String mid(String str, int pos, int len)
    {
        if (str == null)
        {
            return null;
        }
        if (len < 0 || pos > str.length())
        {
            return "";
        }
        if (pos < 0)
        {
            pos = 0;
        }
        if (str.length() <= pos + len)
        {
            return str.substring(pos);
        }
        else
        {
            return str.substring(pos, pos + len);
        }
    }

    public static String substringBefore(String str, String separator)
    {
        if (isEmpty(str) || separator == null)
        {
            return str;
        }
        if (separator.length() == 0)
        {
            return "";
        }
        int pos = str.indexOf(separator);
        if (pos == -1)
        {
            return str;
        }
        else
        {
            return str.substring(0, pos);
        }
    }

    public static String substringAfter(String str, String separator)
    {
        if (isEmpty(str))
        {
            return str;
        }
        if (separator == null)
        {
            return "";
        }
        int pos = str.indexOf(separator);
        if (pos == -1)
        {
            return "";
        }
        else
        {
            return str.substring(pos + separator.length());
        }
    }

    public static String substringBeforeLast(String str, String separator)
    {
        if (isEmpty(str) || isEmpty(separator))
        {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1)
        {
            return str;
        }
        else
        {
            return str.substring(0, pos);
        }
    }

    public static String substringAfterLast(String str, String separator)
    {
        if (isEmpty(str))
        {
            return str;
        }
        if (isEmpty(separator))
        {
            return "";
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == str.length() - separator.length())
        {
            return "";
        }
        else
        {
            return str.substring(pos + separator.length());
        }
    }

    /**
     * Converts safely an object to a string.
     */
    public static String toString(Object value)
    {
        if (value == null)
        {
            return "";
        }
        return value.toString();
    }

    public static String capitalize(String str)
    {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
        {
            return str;
        }
        else
        {
            return (new StringBuffer(strLen)).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * Utility method to take a string and convert it to normal Java variable
     * name capitalization.  This normally means converting the first
     * character from upper case to lower case, but in the (unusual) special
     * case when there is more than one character and both the first and
     * second characters are upper case, we leave it alone.
     * <p>
     * Thus "FooBah" becomes "fooBah" and "X" becomes "x", but "URL" stays
     * as "URL".
     *
     * @param name The string to be decapitalized.
     * @return The decapitalized version of the string.
     */
    public static String decapitalize(String name)
    {
        if (name.length() == 0)
        {
            return name;
        }
        if (name.length() > 1 &&
                Character.isUpperCase(name.charAt(1)) &&
                Character.isUpperCase(name.charAt(0)))
        {
            return name;
        }

        char[] chars = name.toCharArray();
        char c = chars[0];
        char modifiedChar = Character.toLowerCase(c);
        if (modifiedChar == c)
        {
            return name;
        }
        chars[0] = modifiedChar;
        return new String(chars);
    }

    public static String substringBetween(String str, String tag)
    {
        return substringBetween(str, tag, tag);
    }

    public static String substringBetween(String str, String open, String close)
    {
        if (str == null || open == null || close == null)
        {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1)
        {
            int end = str.indexOf(close, start + open.length());
            if (end != -1)
            {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    public static String join(Object array[], String separator)
    {
        if (array == null)
        {
            return null;
        }
        else
        {
            return join(array, separator, 0, array.length);
        }
    }

    public static String join(Object array[], String separator, int startIndex, int endIndex)
    {
        if (array == null)
        {
            return null;
        }
        if (separator == null)
        {
            separator = "";
        }
        int bufSize = endIndex - startIndex;
        if (bufSize <= 0)
        {
            return "";
        }
        bufSize *= (array[startIndex] != null ? array[startIndex].toString().length() : 16) + separator.length();
        StringBuffer buf = new StringBuffer(bufSize);
        for (int i = startIndex; i < endIndex; i++)
        {
            if (i > startIndex)
            {
                buf.append(separator);
            }
            if (array[i] != null)
            {
                buf.append(array[i]);
            }
        }

        return buf.toString();
    }

    public static String deleteWhitespace(String str)
    {
        if (isEmpty(str))
        {
            return str;
        }
        int sz = str.length();
        char chs[] = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                chs[count++] = str.charAt(i);
            }
        }

        if (count == sz)
        {
            return str;
        }
        else
        {
            return new String(chs, 0, count);
        }
    }

    public static String removeStart(String str, String remove)
    {
        if (isEmpty(str) || isEmpty(remove))
        {
            return str;
        }
        if (str.startsWith(remove))
        {
            return str.substring(remove.length());
        }
        else
        {
            return str;
        }
    }


    public static String removeEnd(String str, String remove)
    {
        if (isEmpty(str) || isEmpty(remove))
        {
            return str;
        }
        if (str.endsWith(remove))
        {
            return str.substring(0, str.length() - remove.length());
        }
        else
        {
            return str;
        }
    }

    public static String remove(String str, String remove)
    {
        if (isEmpty(str) || isEmpty(remove))
        {
            return str;
        }
        else
        {
            return replace(str, remove, "", -1);
        }
    }

    public static String remove(String str, char remove)
    {
        if (isEmpty(str) || str.indexOf(remove) == -1)
        {
            return str;
        }
        char chars[] = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] != remove)
            {
                chars[pos++] = chars[i];
            }
        }

        return new String(chars, 0, pos);
    }

    public static String replaceOnce(String text, String searchString, String replacement)
    {
        return replace(text, searchString, replacement, 1);
    }

    public static String replace(String text, String searchString, String replacement)
    {
        return replace(text, searchString, replacement, -1);
    }

    public static String replace(String text, String searchString, String replacement, int max)
    {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0)
        {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == -1)
        {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = increase >= 0 ? increase : 0;
        increase *= max >= 0 ? max <= 64 ? max : 64 : 16;
        StringBuffer buf = new StringBuffer(text.length() + increase);
        do
        {
            if (end == -1)
            {
                break;
            }
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0)
            {
                break;
            }
            end = text.indexOf(searchString, start);
        } while (true);
        buf.append(text.substring(start));
        return buf.toString();
    }

    public static String replaceEach(String text, String searchList[], String replacementList[])
    {
        return replaceEach(text, searchList, replacementList, false, 0);
    }

    public static String replaceEachRepeatedly(String text, String searchList[], String replacementList[])
    {
        int timeToLive = searchList != null ? searchList.length : 0;
        return replaceEach(text, searchList, replacementList, true, timeToLive);
    }

    private static String replaceEach(String text, String searchList[], String replacementList[], boolean repeat, int timeToLive)
    {
        if (text == null || text.length() == 0 || searchList == null || searchList.length == 0 || replacementList == null || replacementList.length == 0)
        {
            return text;
        }
        if (timeToLive < 0)
        {
            throw new IllegalStateException("TimeToLive of " + timeToLive + " is less than 0: " + text);
        }
        int searchLength = searchList.length;
        int replacementLength = replacementList.length;
        if (searchLength != replacementLength)
        {
            throw new IllegalArgumentException("Search and Replace array lengths don't match: " + searchLength + " vs " + replacementLength);
        }
        boolean noMoreMatchesForReplIndex[] = new boolean[searchLength];
        int textIndex = -1;
        int replaceIndex = -1;
        int tempIndex = -1;
        for (int i = 0; i < searchLength; i++)
        {
            if (noMoreMatchesForReplIndex[i] || searchList[i] == null || searchList[i].length() == 0 || replacementList[i] == null)
            {
                continue;
            }
            tempIndex = text.indexOf(searchList[i]);
            if (tempIndex == -1)
            {
                noMoreMatchesForReplIndex[i] = true;
                continue;
            }
            if (textIndex == -1 || tempIndex < textIndex)
            {
                textIndex = tempIndex;
                replaceIndex = i;
            }
        }

        if (textIndex == -1)
        {
            return text;
        }
        int start = 0;
        int increase = 0;
        for (int i = 0; i < searchList.length; i++)
        {
            if (searchList[i] == null || replacementList[i] == null)
            {
                continue;
            }
            int greater = replacementList[i].length() - searchList[i].length();
            if (greater > 0)
            {
                increase += 3 * greater;
            }
        }

        increase = Math.min(increase, text.length() / 5);
        StringBuffer buf = new StringBuffer(text.length() + increase);
        while (textIndex != -1)
        {
            int i;
            for (i = start; i < textIndex; i++)
            {
                buf.append(text.charAt(i));
            }

            buf.append(replacementList[replaceIndex]);
            start = textIndex + searchList[replaceIndex].length();
            textIndex = -1;
            replaceIndex = -1;
            tempIndex = -1;
            i = 0;
            while (i < searchLength)
            {
                if (!noMoreMatchesForReplIndex[i] && searchList[i] != null && searchList[i].length() != 0 && replacementList[i] != null)
                {
                    tempIndex = text.indexOf(searchList[i], start);
                    if (tempIndex == -1)
                    {
                        noMoreMatchesForReplIndex[i] = true;
                    }
                    else if (textIndex == -1 || tempIndex < textIndex)
                    {
                        textIndex = tempIndex;
                        replaceIndex = i;
                    }
                }
                i++;
            }
        }
        int textLength = text.length();
        for (int i = start; i < textLength; i++)
        {
            buf.append(text.charAt(i));
        }

        String result = buf.toString();
        if (!repeat)
        {
            return result;
        }
        else
        {
            return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
        }
    }

    public static String replaceChars(String str, char searchChar, char replaceChar)
    {
        if (str == null)
        {
            return null;
        }
        else
        {
            return str.replace(searchChar, replaceChar);
        }
    }

    public static String replaceChars(String str, String searchChars, String replaceChars)
    {
        if (isEmpty(str) || isEmpty(searchChars))
        {
            return str;
        }
        if (replaceChars == null)
        {
            replaceChars = "";
        }
        boolean modified = false;
        int replaceCharsLength = replaceChars.length();
        int strLength = str.length();
        StringBuffer buf = new StringBuffer(strLength);
        for (int i = 0; i < strLength; i++)
        {
            char ch = str.charAt(i);
            int index = searchChars.indexOf(ch);
            if (index >= 0)
            {
                modified = true;
                if (index < replaceCharsLength)
                {
                    buf.append(replaceChars.charAt(index));
                }
            }
            else
            {
                buf.append(ch);
            }
        }

        if (modified)
        {
            return buf.toString();
        }
        else
        {
            return str;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        //region 汉字处理
        String s = "我ZWR爱ILU你们JAVA";
        System.out.println("原始字符串：" + s + " : 字节数是: " + s.getBytes().length + ":" + getWordCount(s));
        System.out.println("截取前6位：" + subStringB(s, 2));
        System.out.println(substring("[目录#1]", 1, 3));
        //endregion
        //region 字符集汉字站位测试
        /*String en = "A";
        String ch = "人";

        // 计算一个英文字母在各种编码下的字节数
        System.out.println("英文字母：" + en);
        printByteLength(en, "GB2312");
        printByteLength(en, "GBK");
        printByteLength(en, "GB18030");
        printByteLength(en, "ISO-8859-1");
        printByteLength(en, "UTF-8");
        printByteLength(en, "UTF-16");
        printByteLength(en, "UTF-16BE");
        printByteLength(en, "UTF-16LE");

        System.out.println();

        // 计算一个中文汉字在各种编码下的字节数
        System.out.println("中文汉字：" + ch);
        printByteLength(ch, "GB2312");
        printByteLength(ch, "GBK");
        printByteLength(ch, "GB18030");
        printByteLength(ch, "ISO-8859-1");
        printByteLength(ch, "UTF-8");
        printByteLength(ch, "UTF-16");
        printByteLength(ch, "UTF-16BE");
        printByteLength(ch, "UTF-16LE");*/

        /*英文字母：A
        字节数：1;编码：GB2312
        字节数：1;编码：GBK
        字节数：1;编码：GB18030
        字节数：1;编码：ISO-8859-1
        字节数：1;编码：UTF-8
        字节数：4;编码：UTF-16
        字节数：2;编码：UTF-16BE
        字节数：2;编码：UTF-16LE

        中文汉字：人
        字节数：2;编码：GB2312
        字节数：2;编码：GBK
        字节数：2;编码：GB18030
        字节数：1;编码：ISO-8859-1
        字节数：3;编码：UTF-8
        字节数：4;编码：UTF-16
        字节数：2;编码：UTF-16BE
        字节数：2;编码：UTF-16LE*/
        //endregion
        //region 传统测试
        /*String source = "abcdefgabcdefgabcdefgabcdefgabcdefgabcdefg";
        String from = "efg";
        String to = "房贺威";
        System.out.println("在字符串source中，用to替换from，替换结果为："
                + replace(from, to, source));
        System.out.println("返回指定字节长度的字符串："
                + toLength("abcdefgabcdefgabcdefgabcdefgabcdefgabcdefg", 9));
        System.out.println("判断是否为整数：" + isInteger("+0"));
        System.out.println("判断是否为浮点数，包括double和float：" + isDouble("+0.36"));
        System.out.println("判断输入的字符串是否符合Email样式：" +
                isEmail("fhwbj@163.com"));
        System.out.println("判断输入的字符串是否为纯汉字：" + isChinese("你好！"));
        System.out.println("判断输入的数据是否是质数：" + isPrime(12));
        // System.out.println("人民币转换成大写：" + hangeToBig("10019658"));
        System.out.println("去掉字符串中重复的子字符串：" + removeSameString("100 100 9658"));
        System.out.println("过滤特殊字符：" + encoding("100\"s<>fdsd100 9658"));
        System.out.println("判断是不是合法的手机号码：" + isHandset("15981807340"));

        System.out.println("从字符串中取值Email：" + parse("159818 fwhbj@163.com 07340"));
*/
        //null 和 ""操作~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //判断是否Null 或者 ""
        //System.out.println(StringUtil.isEmpty(null));
        //System.out.println(StringUtils.isNotEmpty(null));
        //判断是否null 或者 "" 去空格~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //System.out.println(StringUtils.isBlank("  "));
        //System.out.println(StringUtils.isNotBlank(null));
        //去空格.Null返回null~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //System.out.println(StringUtils.trim(null));
        //去空格，将Null和"" 转换为Null
        //System.out.println(StringUtils.trimToNull(""));
        //去空格，将NULL 和 "" 转换为""
        //System.out.println(StringUtils.trimToEmpty(null));
        //可能是对特殊空格符号去除？？
        //System.out.println(StringUtils.strip("大家好  啊  \t"));
        //同上，将""和null转换为Null
        //System.out.println(StringUtils.stripToNull(" \t"));
        //同上，将""和null转换为""
        //System.out.println(StringUtils.stripToEmpty(null));
        //将""或者Null 转换为 ""
        //System.out.println(StringUtils.defaultString(null));
        //仅当字符串为Null时 转换为指定的字符串(二参数)
        //System.out.println(StringUtils.defaultString("", "df"));
        //当字符串为null或者""时，转换为指定的字符串(二参数)
        //System.out.println(StringUtils.defaultIfEmpty(null, "sos"));
        //去空格.去字符~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //如果第二个参数为null去空格(否则去掉字符串2边一样的字符，到不一样为止)
        //System.out.println(StringUtils.strip("fsfsdf", "f"));
        //如果第二个参数为null只去前面空格(否则去掉字符串前面一样的字符，到不一样为止)
        //System.out.println(StringUtils.stripStart("ddsuuu ", "d"));
        //如果第二个参数为null只去后面空格，(否则去掉字符串后面一样的字符，到不一样为止)
        //System.out.println(StringUtils.stripEnd("dabads", "das"));
        //对数组没个字符串进行去空格。
        //ArrayToList(StringUtils.stripAll(new String[]{" 中华 ", "民 国 ", "共和 "}));
        //如果第二个参数为null.对数组每个字符串进行去空格。(否则去掉数组每个元素开始和结尾一样的字符)
        //ArrayToList(StringUtils.stripAll(new String[]{" 中华 ", "民 国", "国共和国"}, "国"));
        //查找,判断~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //判断2个字符串是否相等相等,Null也相等
        //System.out.println(StringUtils.equals(null, null));
        //不区分大小写比较
        //System.out.println(StringUtils.equalsIgnoreCase("abc", "ABc"));
        //查找，不知道怎么弄这么多查找，很多不知道区别在哪？费劲~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //普通查找字符，如果一参数为null或者""返回-1
        //System.out.println(StringUtils.indexOf(null, "a"));
        //从指定位置(三参数)开始查找，本例从第2个字符开始查找k字符
        //System.out.println(StringUtils.indexOf("akfekcd中华", "k", 2));
        //未发现不同之处
        //System.out.println(StringUtils.ordinalIndexOf("akfekcd中华", "k", 2));
        //查找,不区分大小写
        //System.out.println(StringUtils.indexOfIgnoreCase("adfs", "D"));
        //从指定位置(三参数)开始查找,不区分大小写
        //System.out.println(StringUtils.indexOfIgnoreCase("adfs", "a", 3));
        //从后往前查找
        //System.out.println(StringUtils.lastIndexOf("adfas", "a"));
        //未理解,此结果为2
        //System.out.println(StringUtils.lastIndexOf("d饿abasdafs我", "a", 3));
        //未解,此结果为-1
        //System.out.println(StringUtils.lastOrdinalIndexOf("yksdfdht", "f", 2));
        //从后往前查，不区分大小写
        //System.out.println(StringUtils.lastIndexOfIgnoreCase("sdffet", "E"));
        //未解,此结果为1
        //System.out.println(StringUtils.lastIndexOfIgnoreCase("efefrfs看", "F" , 2));
        //检查是否查到，返回boolean,null返回假
        //System.out.println(StringUtils.contains("sdf", "dg"));
        //检查是否查到，返回boolean,null返回假,不区分大小写
        //System.out.println(StringUtils.containsIgnoreCase("sdf", "D"));
        //检查是否有含有空格,返回boolean
        //System.out.println(StringUtils.containsWhitespace(" d"));
        //查询字符串跟数组任一元素相同的第一次相同的位置
        //System.out.println(StringUtils.indexOfAny("absfekf", new String[]{"f", "b"}));
        //查询字符串中指定字符串(参数二)出现的次数
        //System.out.println(StringUtils.indexOfAny("afefes", "e"));
        //查找字符串中是否有字符数组中相同的字符，返回boolean
        //System.out.println(StringUtils.containsAny("asfsd", new char[]{'k', 'e', 's'}));
        //未理解与lastIndexOf不同之处。是否查到，返回boolean
        //System.out.println(StringUtils.containsAny("啡f咖啡", "咖"));
        //未解
        //System.out.println(StringUtils.indexOfAnyBut("seefaff", "af"));
        //判断字符串中所有字符，都是出自参数二中。
        //System.out.println(StringUtils.containsOnly("中华华", "华"));
        //判断字符串中所有字符，都是出自参数二的数组中。
        //System.out.println(StringUtils.containsOnly("中华中", new char[]{'中', '华'}));
        //判断字符串中所有字符，都不在参数二中。
        //System.out.println(StringUtils.containsNone("中华华", "国"));
        //判断字符串中所有字符，都不在参数二的数组中。
        //System.out.println(StringUtils.containsNone("中华中", new char[]{'中', '达人'}));
        //从后往前查找字符串中与字符数组中相同的元素第一次出现的位置。本例为4
        //System.out.println(StringUtils.lastIndexOfAny("中国人民共和国", new String[]{"国人", "共和"}));
        //未发现与indexOfAny不同之处  查询字符串中指定字符串(参数二)出现的次数
        //System.out.println(StringUtils.countMatches("中国人民共和中国", "中国"));
        //检查是否CharSequence的只包含Unicode的字母。空将返回false。一个空的CharSequence（长（）= 0）将返回true
        //System.out.println(StringUtils.isAlpha("这是干什么的2"));
        //检查是否只包含Unicode的CharSequence的字母和空格（''）。空将返回一个空的CharSequence假（长（）= 0）将返回true。
        //System.out.println(StringUtils.isAlphaSpace("NBA直播 "));
        //检查是否只包含Unicode的CharSequence的字母或数字。空将返回false。一个空的CharSequence（长（）= 0）将返回true。
        //System.out.println(StringUtils.isAlphanumeric("NBA直播"));
        //如果检查的Unicode CharSequence的只包含字母，数字或空格（''）。空将返回false。一个空的CharSequence（长（）= 0）将返回true。
        //System.out.println(StringUtils.isAlphanumericSpace("NBA直播"));
        //检查是否只包含ASCII可CharSequence的字符。空将返回false。一个空的CharSequence（长（）= 0）将返回true。
        //System.out.println(StringUtils.isAsciiPrintable("NBA直播"));
        //检查是否只包含数值。
        //System.out.println(StringUtils.isNumeric("NBA直播"));
        //检查是否只包含数值或者空格
        //System.out.println(StringUtils.isNumericSpace("33 545"));
        //检查是否只是空格或""。
        //System.out.println(StringUtils.isWhitespace(" "));
        //检查是否全是英文小写。
        //System.out.println(StringUtils.isAllLowerCase("kjk33"));
        //检查是否全是英文大写。
        //System.out.println(StringUtils.isAllUpperCase("KJKJ"));
        //交集操作~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //去掉参数2字符串中在参数一中开头部分共有的部分，结果为:人民共和加油
        //System.out.println(StringUtils.difference("中国加油", "中国人民共和加油"));
        //统计2个字符串开始部分共有的字符个数
        //System.out.println(StringUtils.indexOfDifference("ww.taobao", "www.taobao.com"));
        //统计数组中各个元素的字符串开始都一样的字符个数
        //System.out.println(StringUtils.indexOfDifference(new String[] {"中国加油", "中国共和", "中国人民"}));
        //取数组每个元素共同的部分字符串
        //System.out.println(StringUtils.getCommonPrefix(new String[] {"中国加油", "中国共和", "中国人民"}));
        //统计参数一中每个字符与参数二中每个字符不同部分的字符个数
        //System.out.println(StringUtils.getLevenshteinDistance("中国共和发国人民", "共和国"));
        //判断开始部分是否与二参数相同
        //System.out.println(StringUtils.startsWith("中国共和国人民", "中国"));
        //判断开始部分是否与二参数相同。不区分大小写
        //System.out.println(StringUtils.startsWithIgnoreCase("中国共和国人民", "中国"));
        //判断字符串开始部分是否与数组中的某一元素相同
        //System.out.println(StringUtils.startsWithAny("abef", new String[]{"ge", "af", "ab"}));
        //判断结尾是否相同
        //System.out.println(StringUtils.endsWith("abcdef", "def"));
        //判断结尾是否相同，不区分大小写
        //System.out.println(StringUtils.endsWithIgnoreCase("abcdef", "Def"));
        //字符串截取~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //截取指定位置的字符，null返回null.""返回""
        //System.out.println(StringUtil.substring("国民党", 2));
        //截取指定区间的字符
        //
        //从左截取指定长度的字符串
        //System.out.println(StringUtils.left("说点什么好呢", 3));
        //从右截取指定长度的字符串
        //System.out.println(StringUtils.right("说点什么好呢", 3));
        //从第几个开始截取，三参数表示截取的长度
        //System.out.println(StringUtils.mid("说点什么好呢", 3, 2));
        //截取到等于第二个参数的字符串为止
        //System.out.println(StringUtils.substringBefore("说点什么好呢", "好"));
        //从左往右查到相等的字符开始，保留后边的，不包含等于的字符。本例：什么好呢
        //System.out.println(StringUtils.substringAfter("说点什么好呢", "点"));
        //这个也是截取到相等的字符，但是是从右往左.本例结果：说点什么好
        //System.out.println(StringUtils.substringBeforeLast("说点什么好点呢", "点"));
        //这个截取同上是从右往左。但是保留右边的字符
        //System.out.println(StringUtils.substringAfterLast("说点什么好点呢？", "点"));
        //截取查找到第一次的位置，和第二次的位置中间的字符。如果没找到第二个返回null。本例结果:2010世界杯在
        //System.out.println(StringUtils.substringBetween("南非2010世界杯在南非，在南非", "南非"));
        //返回参数二和参数三中间的字符串，返回数组形式
        //ArrayToList(StringUtils.substringsBetween("[a][b][c]", "[", "]"));
        //分割~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //用空格分割成数组，null为null
        //ArrayToList(StringUtils.split("中华 人民  共和"));
        //以指定字符分割成数组
        //ArrayToList(StringUtils.split("中华 ,人民,共和", ","));
        //以指定字符分割成数组，第三个参数表示分隔成数组的长度，如果为0全体分割
        //ArrayToList(StringUtils.split("中华 ：人民：共和", "：", 2));
        //未发现不同的地方,指定字符分割成数组
        //ArrayToList(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-"));
        //未发现不同的地方,以指定字符分割成数组，第三个参数表示分隔成数组的长度
        //ArrayToList(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2));
        //分割，但" "不会被忽略算一个元素,二参数为null默认为空格分隔
        //ArrayToList(StringUtils.splitByWholeSeparatorPreserveAllTokens(" ab   de fg ", null));
        //同上，分割," "不会被忽略算一个元素。第三个参数代表分割的数组长度。
        //ArrayToList(StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null, 3));
        //未发现不同地方,分割
        //ArrayToList(StringUtils.splitPreserveAllTokens(" ab   de fg "));
        //未发现不同地方,指定字符分割成数组
        //ArrayToList(StringUtils.splitPreserveAllTokens(" ab   de fg ", null));
        //未发现不同地方,以指定字符分割成数组，第三个参数表示分隔成数组的长度
        //ArrayToList(StringUtils.splitPreserveAllTokens(" ab   de fg ", null, 2));
        //以不同类型进行分隔
        //ArrayToList(StringUtils.splitByCharacterType("AEkjKr i39:。中文"));
        //未解
        //ArrayToList(StringUtils.splitByCharacterTypeCamelCase("ASFSRules234"));
        //拼接~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //将数组转换为字符串形式
        //System.out.println(StringUtils.concat(getArrayData()));
        //拼接时用参数一得字符相连接.注意null也用连接符连接了
        //System.out.println(StringUtils.concatWith(",", getArrayData()));
        //也是拼接。未发现区别
        //System.out.println(StringUtils.join(getArrayData()));
        //用连接符拼接，为发现区别
        //System.out.println(StringUtils.join(getArrayData(), ":"));
        //拼接指定数组下标的开始(三参数)和结束(四参数,不包含)的中间这些元素，用连接符连接
        //System.out.println(StringUtils.join(getArrayData(), ":", 1, 3));
        //用于集合连接字符串.用于集合
        //System.out.println(StringUtils.join(getListData(), ":"));
        //移除，删除~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //删除所有空格符
        //System.out.println(StringUtils.deleteWhitespace(" s 中 你 4j"));
        //移除开始部分的相同的字符
        //System.out.println(StringUtils.removeStart("www.baidu.com", "www."));
        //移除开始部分的相同的字符,不区分大小写
        //System.out.println(StringUtils.removeStartIgnoreCase("www.baidu.com", "WWW"));
        //移除后面相同的部分
        //System.out.println(StringUtils.removeEnd("www.baidu.com", ".com"));
        //移除后面相同的部分，不区分大小写
        //System.out.println(StringUtils.removeEndIgnoreCase("www.baidu.com", ".COM"));
        //移除所有相同的部分
        //System.out.println(StringUtils.remove("www.baidu.com/baidu", "bai"));
        //移除结尾字符为"\n", "\r", 或者 "\r\n".
        //System.out.println(StringUtils.chomp("abcrabc\r"));
        //也是移除，未解。去结尾相同字符
        //System.out.println(StringUtils.chomp("baidu.com", "com"));
        //去掉末尾最后一个字符.如果是"\n", "\r", 或者 "\r\n"也去除
        //System.out.println(StringUtils.chop("wwe.baidu"));
        //替换~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //替换指定的字符，只替换第一次出现的
        //System.out.println(StringUtils.replaceOnce("www.baidu.com/baidu", "baidu", "hao123"));
        //替换所有出现过的字符
        //System.out.println(StringUtils.replace("www.baidu.com/baidu", "baidu", "hao123"));
        //也是替换，最后一个参数表示替换几个
        //System.out.println(StringUtils.replace("www.baidu.com/baidu", "baidu", "hao123", 1));
        //这个有意识，二三参数对应的数组，查找二参数数组一样的值，替换三参数对应数组的值。本例:baidu替换为taobao。com替换为net
        //System.out.println(StringUtils.replaceEach("www.baidu.com/baidu", new String[]{"baidu", "com"}, new String[]{"taobao", "net"}));
        //同上，未发现不同
        //System.out.println(StringUtils.replaceEachRepeatedly("www.baidu.com/baidu", new String[]{"baidu", "com"}, new String[]{"taobao", "net"}));
        //这个更好，不是数组对应，是字符串参数二和参数三对应替换.(二三参数不对应的话，自己看后果)
        //System.out.println(StringUtils.replaceChars("www.baidu.com", "bdm", "qo"));
        //替换指定开始(参数三)和结束(参数四)中间的所有字符
        //System.out.println(StringUtils.overlay("www.baidu.com", "hao123", 4, 9));
        //添加，增加~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //复制参数一的字符串，参数二为复制的次数
        //System.out.println(StringUtils.repeat("ba", 3));
        //复制参数一的字符串，参数三为复制的次数。参数二为复制字符串中间的连接字符串
        //System.out.println(StringUtils.repeat("ab", "ou", 3));
        //如何字符串长度小于参数二的值，末尾加空格补全。(小于字符串长度不处理返回)
        //System.out.println(StringUtils.rightPad("海川", 4));
        //字符串长度小于二参数，末尾用参数三补上，多于的截取(截取补上的字符串)
        //System.out.println(StringUtils.rightPad("海川", 4, "河流啊"));
        //同上在前面补全空格
        //System.out.println(StringUtils.leftPad("海川", 4));
        //字符串长度小于二参数，前面用参数三补上，多于的截取(截取补上的字符串)
        //System.out.println(StringUtils.leftPad("海川", 4, "大家好"));
        //字符串长度小于二参数。在两侧用空格平均补全（测试后面补空格优先）
        //System.out.println(StringUtils.center("海川", 3));
        //字符串长度小于二参数。在两侧用三参数的字符串平均补全（测试后面补空格优先）
        //System.out.println(StringUtils.center("海川", 5, "流"));
        //只显示指定数量(二参数)的字符,后面以三个点补充(参数一截取+三个点=二参数)
        //System.out.println(StringUtils.abbreviate("中华人民共和国", 5));
        //2头加点这个有点乱。本例结果: ...ijklmno
        //System.out.println(StringUtils.abbreviate("abcdefghijklmno", 12, 10));
        //保留指定长度，最后一个字符前加点.本例结果: ab.f
        //System.out.println(StringUtils.abbreviateMiddle("abcdef", ".", 4));
        //转换,刷选~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //转换第一个字符为大写.如何第一个字符是大写原始返回
        //System.out.println(StringUtils.capitalize("Ddf"));
        //转换第一个字符为大写.如何第一个字符是大写原始返回
        //System.out.println(StringUtils.uncapitalize("DTf"));
        //反向转换，大写变小写，小写变大写
        //System.out.println(StringUtils.swapCase("I am Jiang, Hello"));
        //将字符串倒序排列
        //System.out.println(StringUtils.reverse("中国人民"));
        //根据特定字符(二参数)分隔进行反转
        //System.out.println(StringUtils.reverseDelimited("中:国:人民", ':'));
        //重量
        //System.out.println(TranRealWeight(4000600));
        //换长
        //System.out.println(TranRealLong(16));
        //endregion
    }

    public static String ParseSqlTemplate(String sql)
    {
        if (sql == null)
        {
            return "Error";
        }
        sql = sql.toUpperCase();
        if (indexOf(sql, "SELECT") < 0 || (indexOf(sql, "FROM") < 0))
        {
            return "Error";
        }
        String bottom = substringAfter(sql, "FROM");
        sql = substringBefore(sql, "FROM");
        sql = remove(sql, "SELECT");
        sql = sql.trim();
        if ("*".equals(sql))
        {
            return "Error";
        }
        String[] ss = split(sql, ",");
        String sqlvalue = "";
        for (String s : ss)
        {
            sqlvalue = sqlvalue + s + "||'`'||";
        }
        sqlvalue = substringBeforeLast(sqlvalue, "||'`'||");
        sqlvalue = "SELECT " + sqlvalue + " FROM" + bottom;
        return sqlvalue;
    }

    public static boolean isRailwayCh(String ch)
    {
        if (ch == null)
        {
            return false;
        }
        ch = ch.trim();
        if (!isInteger(ch))
        {
            return false;
        }
        return !(ch.length() > 8 || ch.length() < 4);
    }

    public static String upperCase(final String str)
    {
        if (str == null)
        {
            return null;
        }
        return str.toUpperCase();
    }

    public static long DefaultValue(Integer value)
    {
        if (value == null)
        {
            return 0;
        }
        return value;
    }

    /**
     * 打印字符串在指定编码下的字节数和编码名称到控制台
     *
     * @param s            字符串
     * @param encodingName 编码格式
     */
    public static void printByteLength(String s, String encodingName)
    {
        System.out.print("字节数：");
        try
        {
            System.out.print(s.getBytes(encodingName).length);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        System.out.println(";编码：" + encodingName);
    }

    /**
     * 判断是否是一个中文汉字
     *
     * @param c 字符
     * @return true表示是中文汉字，false表示是英文字母
     * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
     */
    public static boolean isChineseChar(char c) throws UnsupportedEncodingException
    {
        // 如果字节数大于1，是汉字
        // 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
        return String.valueOf(c).getBytes("UTF-8").length > 2;
    }

    /*由于Java是基于Unicode编码的，因此，一个汉字的长度为1，而不是2。
     * 但有时需要以字节单位获得字符串的长度。例如，“123abc长城”按字节长度计算是10，而按Unicode计算长度是8。
     * 为了获得10，需要从头扫描根据字符的Ascii来获得具体的长度。如果是标准的字符，Ascii的范围是0至255，如果是汉字或其他全角字符，Ascii会大于255。
     * 因此，可以编写如下的方法来获得以字节为单位的字符串长度。*/
    public static int getWordCount(String s)
    {
        if(s==null)
        {
            return 0;
        }
        int length = 0;
        for (int i = 0; i < s.length(); i++)
        {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
            {
                length++;
            }
            else
            {
                length += 2;
            }

        }
        return length;

    }

    public static String subStringB(String s, int length) throws Exception
    {

        byte[] bytes = s.getBytes("Unicode");
        int n = 0; // 表示当前的字节数
        int i = 2; // 前两个字节是标志位，bytes[0] = -2，bytes[1] = -1。所以从第3位开始截取。
        for (; i < bytes.length && n < length; i++)
        {
            // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
            if (i % 2 == 1)
            {
                n++; // 在UCS2第二个字节时n加1
            }
            else
            {
                // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                if (bytes[i] != 0)
                {
                    n++;
                }
            }
        }
        // 如果i为奇数时，处理成偶数
        if (i % 2 == 1)
        {
            // 该UCS2字符是汉字时，去掉这个截一半的汉字
            if (bytes[i - 1] != 0)
            {
                i = i - 1;
            }// 该UCS2字符是字母或数字，则保留该字符
            else
            {
                i = i + 1;
            }
        }
        return new String(bytes, 0, i, "Unicode");
    }


}
