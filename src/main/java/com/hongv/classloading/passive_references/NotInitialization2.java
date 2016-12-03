package com.hongv.classloading.passive_references;

/**
 * 常量引用
 * Created by hongwei on 2016/12/4.
 */
public class NotInitialization2 {
    public static void main(String[] args) {
        /**
         * 在编译时，通过常量传播优化，已将常量值存储到了NotInitialization类的常量池中，
         * 以后NotInitialization对ConstClass.HELLO的引用都被转化为对自身常量池的引用，
         * 即在编译后，ConstClass与NotInitialization就没有任何联系了。
         */
        System.out.println(ConstClass.HELLO);
    }
}
