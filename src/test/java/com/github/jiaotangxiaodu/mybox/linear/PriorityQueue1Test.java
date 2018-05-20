package com.github.jiaotangxiaodu.mybox.linear;

import com.github.jiaotangxiaodu.mybox.base.CollectionTest;
import com.github.jiaotangxiaodu.mybox.base.StringTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 * 不使用Comparator,元素自身实现Comparable
 */
public class PriorityQueue1Test extends CollectionTest<String> implements StringTest {

    @Override
    protected boolean testAddNull() {
        return false;
    }

    @Override
    protected PriorityQueue<String> newBox() {
        return CoreFactory.create(PriorityQueue.class);
    }

    @Override
    public String newRandomElement() {
        return StringTest.super.newRandomElement();
    }

    @Override
    public String getSpecialElement() {
        return StringTest.super.getSpecialElement();
    }

    @Test
    public void testPriorityQueueAll() {
        PriorityQueue<String> box = newBox();
        String[] targetArr = new String[100];
        for (int i = 0; i < 100; i++) {
            targetArr[i] = newRandomElement();
            box.offer(targetArr[i]);
        }
        Arrays.sort(targetArr);

        for (int i = 0; i < 100; i++) {
            String poll = box.poll();
            Assert.assertEquals(poll, targetArr[i]);
        }
    }

}
