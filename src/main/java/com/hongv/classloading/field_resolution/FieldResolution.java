package com.hongv.classloading.field_resolution;

/**
 * 字段解析
 * Created by hongwei on 2016/12/4.
 */
public class FieldResolution {

    interface Interface0{
        int A = 0;
    }
    interface Interface1 extends Interface0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        // 如果注释掉此行，编译器会报错：Reference A is ambiguous, both Parent.A and Interface2.A
        public static int A = 4;
    }

    public static void main(String[] args){
        System.out.println(Sub.A);
    }
}
