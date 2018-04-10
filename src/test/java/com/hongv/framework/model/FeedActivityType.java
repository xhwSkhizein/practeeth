package com.hongv.framework.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * -
 * Created by atom on 2017/7/16.
 */
public enum  FeedActivityType {
    Normal(0),
    MolenDay(1),
    MoonFestival(2),
    ;

    private final int value;

    private static Map<Integer, FeedActivityType> valuesMap;

    static {
        valuesMap = new HashMap<>();
        for (FeedActivityType t : values()) {
            FeedActivityType exist = valuesMap.put(t.getValue(), t);
            if (exist != null) {
                throw new IllegalStateException("value冲突: " + exist + " " + t);
            }
        }
        valuesMap = Collections.unmodifiableMap(valuesMap);
    }

    FeedActivityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FeedActivityType fromValue(Integer value) {
        return valuesMap.get(value);
    }
}
