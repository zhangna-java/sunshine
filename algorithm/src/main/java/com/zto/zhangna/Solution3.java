package com.zto.zhangna;

/**
 * Created by zhangna on 2020/9/11 8:53 上午
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class Solution3 {

    public static void main(String[] args) {
        String s = "%20";
        String s1 = replaceSpace(s);
        System.out.println("---------"+s1);

    }


    public static String replaceSpace(String s) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == ' '){
                string.append("%20");
            } else {
                string.append(c);
            }
        }
        return string.toString();
    }
}
