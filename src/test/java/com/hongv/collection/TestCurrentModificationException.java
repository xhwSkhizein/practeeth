package com.hongv.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * {@link java.util.ConcurrentModificationException}
 * <p>
 * Created by hongv on 16/12/3.
 */
public class TestCurrentModificationException {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2)
                list.remove(integer); //
        }

        System.exit(0);
    }

    /**
     * {@link java.util.ArrayList.Itr}
     * <p>
     * <pre>
     *    在调用iterator()时，会创建一个Itr实例， Itr实例内expectedModCount初始化为modCount
     *  第一次执行iterator.next() => modCount = 0,
     *  执行 list.remove() => modCount = 1,
     *  第二次执行iterator.next()， modCount != expectedModCount
     * </pre>
     * <pre>
     *    使用{@link java.util.Iterator#remove() }
     *  在遍历时删除集合中的元素(删除时会修改modCount和expectedModCount,
     *  但需要注意{@link List#iterator()}每次都会创建一个新的{@Itr}实例)
     * </pre>
     * </p>
     */
//    private class Itr implements Iterator<E> {
//        int cursor;       // index of next element to return
//        int lastRet = -1; // index of last element returned; -1 if no such
//        int expectedModCount = modCount;
//
//        public boolean hasNext() {
//            return cursor != size;
//        }
//
//        final void checkForComodification() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//        }
//
    /** {@link java.util.ArrayList.Itr#next() } */
//        @SuppressWarnings("unchecked")
//        public E next() {
//            checkForComodification();
//            int i = cursor;
//            if (i >= size)
//                throw new NoSuchElementException();
//            Object[] elementData = ArrayList.this.elementData;
//            if (i >= elementData.length)
//                throw new ConcurrentModificationException();
//            cursor = i + 1;
//            return (E) elementData[lastRet = i];
//        }
//
    /** {@link java.util.AbstractList.Itr#next() } */
//        public E next() {
//            checkForComodification();
//            try {
//                int i = cursor;
//                E next = get(i);
//                lastRet = i;
//                cursor = i + 1;
//                return next;
//            } catch (IndexOutOfBoundsException e) {
//                checkForComodification();
//                throw new NoSuchElementException();
//            }
//        }
//
//        public void remove() {
//            if (lastRet < 0)
//                throw new IllegalStateException();
//            checkForComodification();
//
//            try {
//                ArrayList.this.remove(lastRet);
//                cursor = lastRet;
//                lastRet = -1;
//                expectedModCount = modCount;
//            } catch (IndexOutOfBoundsException ex) {
//                throw new ConcurrentModificationException();
//            }
//        }
//
//        @Override
//        @SuppressWarnings("unchecked")
//        public void forEachRemaining(Consumer<? super E> consumer) {
//            Objects.requireNonNull(consumer);
//            final int size = ArrayList.this.size;
//            int i = cursor;
//            if (i >= size) {
//                return;
//            }
//            final Object[] elementData = ArrayList.this.elementData;
//            if (i >= elementData.length) {
//                throw new ConcurrentModificationException();
//            }
//            while (i != size && modCount == expectedModCount) {
//                consumer.accept((E) elementData[i++]);
//            }
//            // update once at end of iteration to reduce heap write traffic
//            cursor = i;
//            lastRet = i - 1;
//            checkForComodification();
//        }
//
//    }
}
