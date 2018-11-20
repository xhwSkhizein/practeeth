package com.hongv.datastructure.linkedlist;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * !!! BUG !!!
 * create by hongwei at 2018/7/9
 */
public class CycleIterator<T> implements Iterator {

    private final Map<Integer, T> dataMap;
    private final int startIndex;
    private final int maxIndex;

    private int currentIndex;
    private boolean firstIterate;

    CycleIterator(List<T> dataList, int startIndex) {
        int size = CollectionUtils.size(dataList);
        if (size >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("迭代列表不能超过Integer.MAX_VALUE, dataList.size=" + dataList.size());
        }
        int[] i = {0};
        this.dataMap = dataList.stream().collect(Collectors.toMap(k -> i[0]++, v -> v));
        if (startIndex < 0 || startIndex >= size) {
            throw new IllegalArgumentException("起始游标不能为负， startIndex=" + startIndex);
        }

        this.startIndex = startIndex;
        this.maxIndex = size - 1;
        this.currentIndex = startIndex;
        this.firstIterate = true;
    }

    @Override
    public boolean hasNext() {
        if (!firstIterate && currentIndex == startIndex) {
            return false;
        }
        if (firstIterate) {
            firstIterate = false;
        }

        return true;
    }

    @Override
    public T next() {
        if (currentIndex > maxIndex) {
            currentIndex = 0;
        }

        T next = dataMap.get(currentIndex);
        currentIndex++;

        return next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException();
    }
}
