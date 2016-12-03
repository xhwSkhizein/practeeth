package com.hongv.classloading.passive_references;

/**
 * Created by hongwei on 2016/12/4.
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 42;
}
