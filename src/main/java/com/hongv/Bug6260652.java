package com.hongv;

import java.util.Arrays;
import java.util.List;

/**
 * Created by atom on 2017/4/1.
 */
public class Bug6260652 {
    public static void main(String[] args) {


        List l = Arrays.asList(args);

        System.out.println(l.toArray());

        System.out.println(l.toArray(new Object[0]));

        System.exit(0);
    }
}
