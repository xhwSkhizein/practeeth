package com.hongv.innerclass;

/**
 * Created by hongweixu at 2017/11/1 12:50
 */
public class StaticInnerClass {

    public final static int OUTER_CLASS_VALUE = 1;

    static {
        System.out.println("Outer class static block invoke");
    }

    public StaticInnerClass() {
        System.out.println("Outer class constructor invoke");
    }

    public static HoldData getInnerClassHoldData() {
        return InnerClass.getHoldData();
    }


    private static class InnerClass {
        public final static int INNER_CLASS_VALUE = 9;
        public final static HoldData HOLD_DATA = new HoldData();

        static {
            System.out.println("Inner class static block invoke");
        }

        public InnerClass() {
            System.out.println("Inner class constructor invoke");
        }

        public static int getInnerClassValue() {
            return INNER_CLASS_VALUE;
        }

        public static HoldData getHoldData() {
            return HOLD_DATA;
        }
    }

}

class HoldData {
    public HoldData() {
        System.out.println("hold data construct");
    }
}