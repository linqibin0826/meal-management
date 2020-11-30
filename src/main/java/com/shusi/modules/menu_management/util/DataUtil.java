package com.shusi.modules.menu_management.util;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author linqb
 * @date 2020/11/24 9:57
 */
public class DataUtil {
    /**
     * 判断集合是否为空
     *
     * @param collections
     * @return
     * @author cielo
     */
    public static boolean isEmpty(Collection<?> collections) {
        if (collections == null || collections.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合是否不为空
     *
     * @param collections
     * @return
     * @author cielo
     */
    public static boolean isNotEmpty(Collection<?> collections) {
        return !isEmpty(collections);
    }

    /**
     * 判断数组是否为空
     *
     * @param arr
     * @return
     * @author cielo
     */
    public static boolean isEmpty(Object[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合是否不为空
     *
     * @param arr
     * @return
     * @author cielo
     */
    public static boolean isNotEmpty(Object[] arr) {
        return !isEmpty(arr);
    }

    /**
     * 判断对象是否为null，若对象是字符串则会包括“null”、“NULL”
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        if (obj != null && obj instanceof String) {
            return DataUtil.isNull(obj.toString());
        }
        return obj == null;
    }

    /**
     * 字符串是否为空。包括“null”、“NULL”
     *
     * @param str
     * @return
     * @author cielo
     */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        return "".equals(str) || "null".equals(str) || "NULL".equals(str);
    }

    /**
     * 字符串是否不为空。包括“null”、“NULL”
     *
     * @param str
     * @return
     * @author cielo
     */
    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    /**
     * 获取字符串，为空时默认返回null
     *
     * @param object
     * @return
     */
    public static String getString(Object object) {
        return DataUtil.getString(object, null);
    }

    /**
     * 获取字符串
     *
     * @param object
     * @param nullDefault
     * @return
     * @author cielo 2014-3-21
     */
    public static String getString(Object object, String nullDefault) {
        if (object == null) {
            return nullDefault;
        }
        return object.toString();
    }

    /**
     * 获取字符串。不包含空字符串
     *
     * @param object
     * @param nullDefault
     * @return
     * @author cielo 2014-3-21
     */
    public static String getNotEmptyString(Object object, String nullDefault) {
        if (object == null) {
            return nullDefault;
        }
        String temp = object.toString();
        if ("".equals(temp)) {
            return nullDefault;
        }
        return temp;
    }

    /**
     * 获取Integer数据
     *
     * @param temp        字符串
     * @param nullDefault 为空时的默认值
     * @return
     * @author cielo 2013-12-9
     */
    public static Integer integerOfString(String temp, Integer nullDefault) {
        if (DataUtil.isNull(temp)) {
            return nullDefault;
        }
        try {
            Integer result = Integer.parseInt(temp);
            return result;
        } catch (NumberFormatException e) {
            return nullDefault;
        }
    }

    /**
     * 获取Integer数据
     *
     * @param temp 字符串
     * @return 为空时返回null
     * @author cielo 2013-12-9
     */
    public static Integer integerOfString(String temp) {
        return integerOfString(temp, null);
    }

    /**
     * 获取Integer数据
     *
     * @param temp 字符串
     * @return 为空时返回null
     * @author cielo 2013-12-9
     */
    public static Integer integerOfObject(Object temp) {
        if (temp == null) {
            return null;
        }
        return integerOfString(temp.toString(), null);
    }

    /**
     * 获取Long数据
     *
     * @param temp        字符串
     * @param nullDefault 为空时的默认值
     * @return
     * @author cielo 2013-12-9
     */
    public static Long longOfString(String temp, Long nullDefault) {
        if (DataUtil.isNull(temp)) {
            return nullDefault;
        }
        try {
            Long result = Long.parseLong(temp);
            return result;
        } catch (NumberFormatException e) {
            return nullDefault;
        }
    }

    /**
     * 获取Long数据
     *
     * @param temp 字符串
     * @return 为空时返回null
     * @author cielo 2013-12-9
     */
    public static Long longOfString(String temp) {
        return longOfString(temp, null);
    }

    /**
     * 获取Long数据
     *
     * @param temp Object
     * @return 为空时返回null
     * @author cielo 2013-12-9
     */
    public static Long longOfObject(Object temp) {
        if (temp == null) {
            return null;
        }
        return longOfString(temp.toString(), null);
    }

    /**
     * 将首尾的某个字符去掉，类似首尾空格
     *
     * @param content
     * @param c
     * @return
     * @author cielo 2013-12-18
     */
    public static String trim(String content, String c) {
        if (content == null) {
            return null;
        }
        content = content.replaceAll("(^" + c + "+)|(" + c + "+$)", "");
        return content;
    }

    /**
     * 去掉首尾空字符，包括tab，回车
     *
     * @param content
     * @return
     */
    public static String trim(String content) {
        return DataUtil.trim(content, "\\s");
    }

    /**
     * 从字符串获取数组
     *
     * @param content
     * @param regex
     * @return
     * @author cielo
     */
    public static String[] split(String content, String regex) {
        if (DataUtil.isNull(content)) {
            return null;
        }
        content = DataUtil.trim(content, regex);
        String[] arr = content.split(regex);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!list.contains(arr[i]) && DataUtil.isNotNull(arr[i])) {
                list.add(arr[i]);
            }
        }
        String[] result = new String[list.size()];
        return list.toArray(result);
    }

    /**
     * 从字符串获取数组
     *
     * @param content
     * @param regex
     * @return
     * @author cielo
     */
    public static List<String> splitToList(String content, String regex) {
        String[] arr = DataUtil.split(content, regex);
        if (arr != null) {
            return Arrays.asList(arr);
        }
        return null;
    }

    /**
     * 从字符串获取Long数组
     *
     * @param content
     * @param regex
     * @return
     * @author cielo
     */
    public static Integer[] splitToInteger(String content, String regex) {
        if (DataUtil.isNull(content)) {
            return null;
        }
        content = DataUtil.trim(content, regex);
        String[] arr = content.split(regex);
        Integer[] arr2 = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = DataUtil.integerOfString(arr[i]);
        }
        return arr2;
    }

    /**
     * 从字符串获取Long数组
     *
     * @param content
     * @param regex
     * @return
     * @author cielo
     */
    public static Long[] splitToLong(String content, String regex) {
        if (DataUtil.isNull(content)) {
            return null;
        }
        content = DataUtil.trim(content, regex);
        String[] arr = content.split(regex);
        Long[] arr2 = new Long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = DataUtil.longOfString(arr[i]);
        }
        return arr2;
    }

    /**
     * 判断数组arr是否包含 item
     *
     * @param arr
     * @param item
     * @return
     * @author cielo
     */
    public static boolean contains(Object[] arr, Object item) {
        if (DataUtil.isEmpty(arr) || DataUtil.isNull(item)) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断数组arr是否不包含 item
     *
     * @param arr
     * @param item
     * @return
     * @author cielo
     */
    public static boolean notContains(Object[] arr, Object item) {
        return !DataUtil.contains(arr, item);
    }

    /**
     * 首字母大写
     *
     * @param name
     * @return
     * @author cielo
     */
    public static String firstUpperCase(String name) {
        if (DataUtil.isNull(name)) {
            return null;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param name
     * @return
     * @author cielo
     */
    public static String firstLowerCase(String name) {
        if (DataUtil.isNull(name)) {
            return null;
        }
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    /**
     * 名称转Java驼峰名称
     *
     * @param name
     * @return
     * @author cielo
     */
    public static String toCamel(String name) {
        if (DataUtil.isNull(name)) {
            return null;
        }
        name = name.toLowerCase();
        String[] arr = DataUtil.split(name, "_");
        if (arr.length > 1) {
            StringBuffer sb = new StringBuffer();
            sb.append(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                sb.append(DataUtil.firstUpperCase(arr[i]));
            }
            return sb.toString();
        } else {
            return name;
        }

    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String camelToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 判断两字符串相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equals(str2);
        }
    }

    /**
     * 判断两字符串不相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean notEquals(String str1, String str2) {
        return DataUtil.equals(str1, str2) == false;
    }

    /**
     * 解析区域ID
     *
     * @param regionId
     * @return
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static String[] getRegion(String regionId) {
        if (regionId.endsWith("0000")) {
            return new String[]{regionId};
        } else if (regionId.endsWith("00")) {
            return new String[]{regionId.substring(0, 1) + "0000", regionId};
        } else {
            return new String[]{regionId.substring(0, 1) + "0000", regionId.substring(0, 3) + "00", regionId};
        }
    }



    /**
     * 根据正则获取值
     *
     * @param content
     * @param reg
     * @return
     */
    public static List<String> getListByReg(String content, String reg, int group) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group(group));
        }
        return list;
    }

}
