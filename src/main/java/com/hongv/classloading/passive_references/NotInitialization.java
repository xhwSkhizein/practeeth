package com.hongv.classloading.passive_references;

/**
 * 类加载与初始化
 *
 * @note 使用 -XX:+TraceClassLoading 参数观察虚拟机类加载信息
 * Created by hongwei on 2016/12/4.
 */
public class NotInitialization {

    /**
     * 非主动使用类字段
     */
    public static void main(String[] args) {
            // 被动使用类字段，通过子类引用父类的静态字段，不会导致子类初始化
            System.out.println(SubClass.value);

            // 通过数组定义来引用类，不会触发此类的初始化
            // 但是这里会触发一个名为“[Lcom.hongv.classloading.SuperClass]”的类的初始化阶段，
            // 对于用户来说这不是一个合法的类名，它是由虚拟机自动生成、直接继承于{@link java.lang.Object}的子类
            // 创建动作由字节码指令newarray触发
            // 这个类代表了一个元素类型为SuperClass的一维数组
            //SuperClass[] arr = new SuperClass[10];
        }
}

