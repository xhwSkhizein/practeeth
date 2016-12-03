package com.hongv.classloading.passive_references;

/**
 * Created by hongwei on 2016/12/4.
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}