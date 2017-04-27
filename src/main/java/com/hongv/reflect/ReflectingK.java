package com.hongv.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * - 反射
 * Created by atom on 2017/3/22.
 */
public class ReflectingK {

    /**
     * 打印类的信息，包括类的成员函数、成员变量(只获取成员函数)
     *
     * @param obj 该对象所属类的信息
     */
    public static void printClassMethodMessage(Object obj) {
        //要获取类的信息  首先要获取类的类类型
        Class c = obj.getClass();//传递的是哪个子类的对象  c就是该子类的类类型
        //获取类的名称
        System.out.println("类的名称是:" + c.getName());
        /*
         * Method类，方法对象
         * 一个成员方法就是一个Method对象
         * getMethods()方法获取的是所有的public的函数，包括父类继承而来的
         * getDeclaredMethods()获取的是所有该类自己声明的方法，不问访问权限
         */
        Method[] ms = c.getMethods();//c.getDeclaredMethods()
        for (int i = 0; i < ms.length; i++) {
            //得到方法的返回值类型的类类型
            Class returnType = ms[i].getReturnType();
            System.out.print(returnType.getName() + " ");
            //得到方法的名称
            System.out.print(ms[i].getName() + "(");
            //获取参数类型--->得到的是参数列表的类型的类类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.print(class1.getName() + ",");
            }
            System.out.println(")");
        }
    }

    /**
     * 获取成员变量的信息
     *
     * @param obj
     */
    public static void printFieldMessage(Object obj) {
        Class c = obj.getClass();
        /**
         * 成员变量也是对象
         * java.lang.reflect.Field
         * Field类封装了关于成员变量的操作
         * - getFields()方法获取的是所有的public的成员变量的信息
         * - getDeclaredFields获取的是该类自己声明的成员变量的信息
         */
        //Field[] fs = c.getFields();
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            //得到成员变量的类型的类类型
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            //得到成员变量的名称
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    public static void invokeMethod() {
        //要获取print(int ,int )方法  1.要获取一个方法就是获取类的信息，获取类的信息首先要获取类的类类型
        K a1 = new K(1l, "joe");
        Class c = a1.getClass();
        /*
         * 2.获取方法 名称和参数列表来决定
         * getMethod获取的是public的方法
         * getDelcaredMethod自己声明的方法
         */
        try {
            Method m = c.getMethod("print", int.class, int.class);
            //Method m =  c.getMethod("print", new Class[]{int.class,int.class});也可
            //方法的反射操作
            //a1.print(10, 20);方法的反射操作是用m对象来进行方法调用 和a1.print调用的效果完全相同
            //Object o = m.invoke(a1,new Object[]{10,20});或
            Object o = m.invoke(a1, 10, 20);
            //获取方法print(String,String)
            Method m1 = c.getMethod("print", String.class, String.class);
            //用方法进行反射操作
            o = m1.invoke(a1, "hello", "WORLD");
            Method m2 = c.getMethod("print");
            //Method m2 = c.getMethod("print", new Class[]{});也可
            // m2.invoke(a1, new Object[]{});
            m2.invoke(a1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        /**
         * 类的名称是:com.hongv.reflect.K
         *   java.lang.String getName()
         *   long getId()
         *   java.lang.String decoK()
         *   java.lang.String decoK(com.hongv.reflect.K,)
         *   void wait(long,int,)
         *   void wait(long,)
         *   void wait()
         *   boolean equals(java.lang.Object,)
         *   java.lang.String toString()
         *   int hashCode()
         *   java.lang.Class getClass()
         *   void notify()
         *   void notifyAll()
         */
        ReflectingK.printClassMethodMessage(new K(1l, "k"));

        System.exit(0);
    }
}

class K {
    private final long id;
    private final String name;

    public K(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private String echo() {
        return name;
    }

    public static String decoK() {
        return "*K*";
    }

    public static String decoK(K k) {
        return "*" + k.getName() + "*";
    }

    public void print() {
        System.out.println("helloworld");
    }

    public void print(int a, int b) {
        System.out.println(a + b);
    }

    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + "," + b.toLowerCase());
    }
}