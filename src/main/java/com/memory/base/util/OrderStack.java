package com.memory.base.util;

import com.memory.base.constant.OrderSeq;
import com.memory.base.model.OrderField;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Author: MemoRyAxis
 * Date: 16-10-28
 * Time: 下午7:21
 */
public class OrderStack extends Stack<OrderField> {

    public synchronized boolean add(String field, OrderSeq seq) {
        return super.add(new OrderField(field, seq));
    }

    public synchronized Iterator<OrderField> iterator() {
        return new StackItr();
    }

    OrderField elementData(int index) {
        return (OrderField) elementData[index];
    }

    private class StackItr implements Iterator<OrderField> {
        int cursor = elementCount;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != 0;
        }

        public OrderField next() {
            synchronized (OrderStack.this) {
                checkForComodification();
                int i = cursor - 1;
                if (i < 0)
                    throw new NoSuchElementException();
                cursor = i;
                return elementData(lastRet = i);
            }
        }

        public void remove() {
            if (lastRet == -1)
                throw new IllegalStateException();
            synchronized (OrderStack.this) {
                checkForComodification();
                OrderStack.this.remove(lastRet);
                expectedModCount = modCount;
            }
            cursor = lastRet;
            lastRet = -1;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

}
