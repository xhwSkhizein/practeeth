package com.hongv.proxyandmethod;

/**
 * Created by atom on 2017/3/14.
 */
public class C {
    public String sayHello(String name) {
        String message = "Hello " + name;
        System.out.println(message);
        return message;
    }
}