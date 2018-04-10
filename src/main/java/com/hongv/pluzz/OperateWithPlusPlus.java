package com.hongv.pluzz;

/**
 * ++ 运算
 *
 * Created by hongweixu on 2017/6/23.
 */
public class OperateWithPlusPlus {

    private volatile static int num = 50;

    public static void main(String[] args){
        num = num++ * 2;

        System.out.println(num);

    }
}
