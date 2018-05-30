package org.lelouch.tools;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证
 *
 * @author zhu
 * @since 2015-07-16 08:40:23
 */
public class Check {
    /**
     * 判断是否是windows
     *
     * @return
     */
    public static boolean isWindows() {
        boolean b = true;
        String res = System.getProperties().getProperty("os.name").toUpperCase();
        if (res.indexOf("WINDOWS") >= 0){
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }

    /**
     * Gets the system property indicated by the specified key.
     *
     * @return
     */
    public static String getProperty(String property) {
        return System.getProperty(property);
    }

    /**
     * 整数校验
     *
     * @param value
     *
     * @return
     */
    public static boolean isInteger(String value) {
        try {
            if (!isNotNULL(value)){
                return false;
            }
            Integer.parseInt(trim(value));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param value
     *
     * @return
     */
    public static boolean isBoolean(String value) {
        try {
            if (!isNotNULL(value)){
                return false;
            }
            Boolean.parseBoolean(trim(value));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Double校验
     *
     * @param value
     *
     * @return
     */
    public static boolean isDouble(String value) {
        try {
            if (!isNotNULL(value)){
                return false;
            }
            Double.parseDouble(trim(value));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String value) {
        try {
            if (!isNotNULL(value)){
                return false;
            }
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBigDecimal(String value) {

        try {
            if (!isNotNULL(value)){
                return false;
            }

            new BigDecimal(trim(value));
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isUrl(String str) {
        if (str != null && !"".equals(str)) {
            return str.matches("^(http|https|ftp|){1}(://){1}(.)*$");
        } else {
            return false;
        }
    }

//    /**
//     * null 验证
//     *
//     * @param value
//     *
//     * @return
//     */
//    public static boolean isNotNUll(String value) {
//        if (value != null && (!value.equals(""))){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

//    private static  boolean isNotNUll(Object[] value) {
//        if (value != null && value.length > 0){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

//    /**
//     * @param value
//     *
//     * @return
//     */
//    public static boolean isNotNULL_Array(Object value) {
////        if (value != null && value.length > 0){
////            return true;
////        }
////        else {
////            return false;
////        }
//        if (value != null){
//            if (value.getClass().isArray()){
//                String type = value.getClass().getComponentType().toString();
//                System.err.println("type " + type);
//                System.err.println("type " + Array.getLength(value));
//
//                System.err.println("ssssssssssssss");
//                switch (type) {
//                    case "string":
//                        if (((String[]) value).length == 0){
//                            return false;
//                        }
//                        else {
//                            return true;
//                        }
//                    case "int":
//                    case "integer":
//                    case "double":
//                        System.err.println("xxxxxxxxxxxxxxxx");
////                        if (((String. []) value).length == 0){
////                            return false;
////                        }
////                        else {
////                            return true;
////                        }
//
//                }
//            }
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

//    public static boolean isNotNUll(List<?> value) {
//        if (value != null && value.size() > 0){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

//    public static boolean isNotNULL(HashMap<?, ?> value) {
//        if (value != null && value.size() > 0){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

    public static boolean isNotNULL(Map<?, ?> value) {
        if (value != null && value.size() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isNotNULL_MapKey(Map m, Object value) {

        if (m.get(value) != null && m.get(value).toString().length() != 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * null 验证
     *
     * @param value
     *
     * @return
     */
    public static boolean isNotNULL(Object value) {
        if (value != null){
            switch (value.getClass().getSimpleName()) {
                case "String":
                    if (value.toString().length() == 0){
                        return false;
                    }
                    else {
                        return true;
                    }

                case "ArrayList":
                    if (((List) value).size() == 0){
                        return false;
                    }
                    else {
                        return true;
                    }

            }
            if (value.getClass().isArray()){
                //String type = value.getClass().getComponentType().toString();
                if (Array.getLength(value) == 0){
                    return false;
                }
                else {
                    return true;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isFile(File file) {

        if (file != null && file.exists() && file.isFile()){
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * 程序是否跑在jar里
     *
     * @param classes
     *
     * @return
     */
    public static boolean isJar(Class classes) {
        String url = String.valueOf(classes.getResource(classes.getSimpleName() + ".class"));
        if (isNotNULL(url)){
            if (url.indexOf("jar:") == 0){
                return true;
            }
            else {
                return false;
            }

        }
        else {
            return false;
        }
    }

    /**
     * 判断是否是64bit
     *
     * @param classes
     *
     * @return
     */
    public static boolean is64bit() {
        String arch = System.getProperty("os.arch");

        if (arch.indexOf("64") >= 0){
            return true;
        }
        else {
            return false;
        }


    }

    /**
     * 去首尾空格【全角、半角,\u00a0】
     *
     * @param value
     *
     * @return
     */
    public static String trim(String value) {

        return value.replaceAll("[\u3000]|[\\s]|[\\u00a0]", " ").replaceAll("(^\\s*)|(\\s*$)", "");
    }

    /**
     * 字符串转换unicode
     */
    public static String stringToUnicode(String string) {

        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            String val = Integer.toHexString(c);

            int j = val.length();
            if (j == 3){
                val = "0" + val;
            }
            else if (j == 2){
                val = "00" + val;
            }
            else if (j == 1){
                val = "000" + val;
            }
            else {

            }
            unicode.append("\\u" + val);
        }

        return unicode.toString();
    }

    /**
     * null to ""
     *
     * @param value
     *
     * @return
     */
    public static String nullchange(String value) {
        if (value == null){
            return "";
        }
        else {
            return value;
        }
    }

    /**
     * @param date
     *
     * @return
     */
    public static boolean isMysqldate(String date) {
        if (date != null && (!date.equals("")) && (!date.equals("0000-00-00"))){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 字符串截取
     *
     * @param text
     *
     * @return
     */
    public static String ellipsis(String text, int length) {
        if (isNotNULL(text)){
            if (text.length() <= length){
                return text;
            }
            else {
                return text.substring(0, length) + "…";
            }
        }
        else {
            return "";
        }
    }

    /**
     * 是date(yyyy-MM-dd)
     *
     * @param value
     */
    public static boolean isDateString(String value) {
        Pattern p = null;// 正则表达式
        Matcher m = null;// 操作符表达式
        boolean b = false;
        p = p.compile(
                "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)");
        m = p.matcher(value);
        b = m.matches();
        return b;
    }

    public static String getSplitFirst(String value) {
        String[] res = value.split(" ");
        if (isNotNULL(res)){
            return res[0];
        }
        else {
            return null;
        }
    }

    /**
     * 过滤掉html 只显示文本
     *
     * @param html
     *
     * @return
     */
    public static String filterHtml(String html) {
        return html.replaceAll("(<!--[\\s\\S]+?-->)|(<[^>]*>)", "");
    }

    /**
     * 过滤掉html 只显示文本(保留p,img)
     *
     * @param html
     *
     * @return
     */
    public static String filterHtml2(String html) {
        return html.replaceAll("(<!--[\\s\\S]+?-->)|(</?(?!(img)|p|IMG|P)[^><]*>)|(\\s]*(?i)style[^>]*?\"[^\"]*\")", "");
    }

    /**
     * 是否匹配
     *
     * @param regex
     *         The expression to be compiled (org.lelouch.tools.Regexs)
     * @param input
     *         The character sequence to be matched
     *
     * @return
     */
    public static boolean matches(String regex, CharSequence input) {
        return Pattern.matches(regex, input);
    }

}
