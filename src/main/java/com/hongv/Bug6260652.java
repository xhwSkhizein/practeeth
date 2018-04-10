package com.hongv;

import org.jooq.lambda.Seq;

import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://bugs.java.com/view_bug.do?bug_id=JDK-6260652">JDK-6260652</a>
 * <p>
 * Created by atom on 2017/4/1.
 */
public class Bug6260652 {
    public static void main(String[] args) {

        List l = Arrays.asList("1", "2", "3");

        Object[] objArray = l.toArray(); // use Object.clone()
        System.out.println(objArray);

        // error: java.lang.ArrayStoreException: java.lang.Object
        // objArray[objArray.length - 1] = new Object();

        Object[] objArrayX = l.toArray(new Object[0]); // Arrays.copyOf()
        System.out.println(objArrayX);

        objArrayX[objArrayX.length - 1] = new Object();

        Seq.of(objArrayX).forEach(System.out::println);

        // OUTPUT:
        //        [Ljava.lang.String;@65ab7765
        //        [Ljava.lang.Object;@1b28cdfa
        //
        //        1
        //        2
        //        java.lang.Object@3d646c37

        System.exit(0);
    }
}
