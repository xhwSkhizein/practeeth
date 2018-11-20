package com.hongv.unsafed;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by hongweixu at 2018/8/30 15:14
 */
public class GetUnsafeInstance {

    /**
     * <pre>
     *  public static Unsafe getUnsafe(){
     *      Class var0 = Reflection.getCallerClass();
     *      if (!VM.isSystemDomainLoader(var0.getClassLoader())) {
     *          throw new SecurityException("Unsafe");
     *      } else {
     *          return theUnsafe;
     *      }
     *  }
     * </pre>
     *  可以通过上面的方法获取Unsafe实例，获取时会检查当前调用此方法的类是否是由Bootstrap类加载器加载的(Bootstrap class loader由c++实现，所以它加载的类classloader都为null)
     *  如果不是会抛出异常
     *      a. 可以通过 java -Xbootclasspath:/usr/jdk/jre/lib/rt.jar:./com.xxx.yy.UnsafeClient, ！但这样做显然不好！
     *      b. Unsafe类持有theUnsafe实例字段，即使被声明成private，我们也可以通过反射获取到它
     *
     * @return the {@link Unsafe} object
     */
    public static Unsafe getUnsafe() {
        Field theUnsafeField = null;
        try {
            theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            // the unsafe instance
            return (Unsafe) theUnsafeField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // fixme
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) throws NoSuchFieldException {

        // unsafe 操作对象
        TestBean john = new TestBean("John", 23, Gender.Male);

        // FIXME

        System.exit(0);
    }

}

class TestBean {
    private final String name;
    private final int age;
    private final Gender gender;

    public TestBean(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}

enum Gender {
    Male,
    Femal;
}
