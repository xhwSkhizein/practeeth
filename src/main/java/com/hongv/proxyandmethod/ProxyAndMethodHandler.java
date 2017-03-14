package com.hongv.proxyandmethod;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by atom on 2017/3/14.
 */
public class ProxyAndMethodHandler {

    public static void main(String[] mainArgs) {

        A a = (A) Proxy.newProxyInstance(C.class.getClassLoader(), new Class[] { A.class },
                (proxy, method, args) -> {
                    System.out.println("Proxying: " + method.getName() + " " + Arrays.toString(args));
                    return 1; // java.lang.Number here
                }
        );

        /**
         * Exception in thread "main" java.lang.ClassCastException: java.lang.Number cannot be cast to java.lang.String
         */
        String amplify = a.amplify(10); // java.lang.String here.
        System.out.println(amplify);
        Number a1 = a.getA();
        System.out.println(a1);
//        a.zzz();
//        System.out.println(a.toString());

        System.exit(0);
    }
}
