package com.hongv.proxyandmethod;

/**
 * Created by atom on 2017/3/14.
 */
public interface A {

    String amplify(Number a);

    Number getA();

    String toString();

//    default String zzz() {
//        System.out.println("Sleeping: zzzz");
//        return "42";
//    }

}
