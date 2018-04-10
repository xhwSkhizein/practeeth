package com.hongv.frameworkd;

import java.util.function.Consumer;

/**
 * -
 * Created by hongweixu on 2017/7/8.
 */
public interface TimerStealK {


    /**
     * 注册一个任务
     *
     * @param timeInMillis 执行时间戳
     * @param consumer     任务执行器
     * @return
     */
    <T> TimerStealK regist(long timeInMillis, Consumer<T> consumer);


    /**
     * 监听主程
     */
    default void mainLoop() {

    }
}

/**
 * 注册，执行，
 * <p>
 * 保障：
 * 任务按时执行。
 * <p>
 * 关于任务：
 * a. 任务标记是否支持并发
 * b. 任务自身描述
 * 关于执行线程
 * a. 可以配置执行线程池大小
 * b. 无法执行更多任务，拒绝服务，通知注册线程
 * c. 执行完一个任务且等待队列空，及时通知注册线程
 * d. 统计执行时间等相关信息
 * 关于注册线程
 * a. 了解cpu状况，如果满负载，抛出异常尝试拒绝注册，通知调用者寻找其他空闲服务
 * b. 应该不需要很多
 * c.
 * <p>
 * <p>
 * q1： 如何及时通知执行线程
 * a： 轮询？ 一个轮询主程
 * <p>
 * 注册点触发事件执行， 直接扔到线程池里，
 * <p>
 * q2： 需要执行器， 处理任务开始结束
 * a： 执行器应该只是一个任务和执行的胶水层，应该保障尽可能的轻量，使用回调，不要产生对象的创建和销毁操作
 * <p>
 * q3： 控制cpu使用率，即控制线程数
 */