package com.hongv.reflect;

import com.hongv.proxyandmethod.B;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by atom on 2017/4/1.
 */
public class ReflectCauseMetaspaceLeakTest {


    public static void main(String[] args) throws NoSuchMethodException {

        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass",
                new Class[]{String.class, byte[].class, int.class, int.class});

        defineClass.setAccessible(true);


        File file = new File("/Users/atom/BBB.class");

        byte[] bcs = new byte[(int) file.length()];
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            while ((in.read(bcs)) != -1) {

            }
        } catch (Exception e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }

        while (true) {
            try {
                defineClass.invoke(B.class.getClassLoader(), new Object[]{"BBB", bcs, 0, bcs.length});
            } catch (Throwable t) {

            }
        }

    }
}
