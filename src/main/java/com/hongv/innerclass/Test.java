package com.hongv.innerclass;

/**
 * Created by hongweixu at 2017/11/1 12:53
 */
public class Test {
    public static void main(String[] args) {

        try {
            Class.forName("com.hongv.innerclass.StaticInnerClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(StaticInnerClass.getInnerClassHoldData());

        System.exit(0);
    }
}
