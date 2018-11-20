package com.hongv.leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongweixu at 2018/10/9 15:42
 */
public class PalindromeNum {

    static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        while (x / 10 != 0) {
            int last = x % 10;
            list.add(last);
            x = x / 10;
        }
        list.add(x); // last

        // System.out.println(StringUtils.join(list, ","));

        int len = list.size();
        int mid = len / 2;
        // System.out.println("--------- len=" + len + ", mid=" + mid);
        for (int i = 0, j = len - 1; i < mid && j >= mid; i++, j--) {
            int val1 = list.get(i);
            int val2 = list.get(j);
            // System.out.println("--------- val1=" + val1 + ",val2=" + val2);
            if (val1 != val2) {
                return false;
            }
        }
        return true;
    }

    static boolean isPalindrome2(int x) {
        // 反转整数，判断相等

        return false;
    }

    public static void main(String[] args) {

        System.out.println(isPalindrome(10));

        System.exit(0);
    }
}
