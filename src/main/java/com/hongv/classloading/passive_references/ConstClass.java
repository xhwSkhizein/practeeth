package com.hongv.classloading.passive_references;

/**
 * Created by hongwei on 2016/12/4.
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }
    public static final String HELLO = "hello";
}
