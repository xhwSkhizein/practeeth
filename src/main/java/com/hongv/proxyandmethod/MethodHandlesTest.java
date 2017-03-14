package com.hongv.proxyandmethod;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by atom on 2017/3/14.
 */
public class MethodHandlesTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {

        MethodHandle sayHelloHandle =
                MethodHandles.lookup().findVirtual(C.class,"sayHello", MethodType.methodType(String.class, String.class));

        System.out.println(sayHelloHandle);

        System.exit(0);
    }

}
