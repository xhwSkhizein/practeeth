package com.hongv.multithread;

/**
 * Created by atom on 2017/6/26.
 */
public class TestSynchronized {


    public static void main(String[] args) {

        A a = new A();

        new Thread(a::testA).run();
        new Thread(a::testB).run();

        System.exit(0);
    }
}

class A {
    public synchronized void testA() {
        System.out.println("testA exec");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("testA end");
    }

    public synchronized void testB() {
        System.out.println("testB exec");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("testB end");
    }
}
