package com.hongv.proxyandmethod;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * -
 * Created by atom on 2017/3/14.
 */
public class ProxyWithMethodHandlerTest {
    public static void main(String[] mainArgs) {

        ClassLoader classLoader = A.class.getClassLoader();
        System.out.println(classLoader);
        A a = (A) Proxy.newProxyInstance(classLoader, new Class[]{A.class},
                (proxy, method, args) -> {
                    System.out.println("Proxying: " + method.getName() + " " + Arrays.toString(args));
//                    return method.invoke(proxy, args); // won't work!
                    if (method.isDefault()) {
                        final Class<?> declaringClass = method.getDeclaringClass();
                        Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
                        constructor.setAccessible(true);
                        return
                                constructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE)
                                        .unreflectSpecial(method, declaringClass)
                                        .bindTo(proxy)
                                        .invokeWithArguments(args);
                    }
                    System.out.println("Proxying: " + method.getName() + " " + Arrays.toString(args));
                    return "Success";
                }
        );

        String amplify = a.amplify(10);
        System.out.println(amplify);
        Number a1 = a.getA();
        System.out.println(a1);
//        a.zzz();
        System.out.println(a.toString());

        System.exit(0);
    }
}
