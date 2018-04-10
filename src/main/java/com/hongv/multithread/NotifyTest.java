package com.hongv.multithread;

/**
 * Created by atom on 2017/6/26.
 */
public class NotifyTest {

    /**
     * 这里如果不加synchronized修饰，直接调用{@link Object#wait()}时，
     * 因为当前对象无法获取到monitor对象的所有权, 会抛出{@link IllegalMonitorStateException}
     * 获取monitor的方法就是使用synchronized修饰方法
     */
    public synchronized void testWait() {
        System.out.println(Thread.currentThread().getName() + " Start-----");
        try {
            wait(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End-------");
    }

    public static void main(String[] args) throws InterruptedException {
        // 对同一个同步对象
        final NotifyTest test = new NotifyTest();

        for (int i = 0; i < 5; i++) {
            new Thread(test::testWait).start();
        }

        synchronized (test) {
            test.notify();
        }
        Thread.sleep(3000);
        System.out.println("-----------分割线-------------");

        synchronized (test) {
            test.notifyAll();
        }
    }

//    （1）调用wait方法后，线程是会释放对monitor对象的所有权的。
//    （2）一个通过wait方法阻塞的线程，必须同时满足以下两个条件才能被真正执行：
//            线程需要被唤醒（超时唤醒或调用notify/notifyll）。
//            线程唤醒后需要竞争到锁（monitor）。
}
