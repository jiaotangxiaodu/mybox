package com.github.jiaotangxiaodu.mybox.base;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 */
public abstract class CollectionTest<E> {

    /**
     * 返回测试用容器
     *
     * @return
     */
    protected abstract Collection<E> newBox();

    /**
     * 返回一个随机生成的元素,任意两次生成的element之间调用equals都应该返回false
     * 不能返回null
     */
    protected abstract E newRandomElement();

    /**
     * 返回一个特定的元素,任意两次返回的Element之间调用Equals都应该返回true
     * 不能返回null
     */
    protected abstract E getSpecialElement();

    /**
     * 是否需要测试add(null)
     *
     * @return
     */
    protected boolean testAddNull() {
        return true;
    }

    /**
     * 是否需要测试添加重复的元素
     */
    protected boolean testAddRepeatedElement() {
        return false;
    }


    @Test
    public void testIsEmpty() {
        Collection<E> box = newBox();
        Assert.assertEquals(box.isEmpty(), true);
        box.add(newRandomElement());
        Assert.assertEquals(box.isEmpty(), false);
    }

    @Test
    public void testContains() {
        Collection<E> box = newBox();
        Assert.assertEquals(box.contains(getSpecialElement()), false);
        box.add(newRandomElement());
        Assert.assertEquals(box.contains(getSpecialElement()), false);
        box.add(getSpecialElement());
        Assert.assertEquals(box.contains(getSpecialElement()), true);
    }

    @Test
    public void testIterator() {
        Collection<E> box = newBox();
        E randomElement = newRandomElement();
        box.add(randomElement);
        box.add(newRandomElement());
        box.add(newRandomElement());

        int trueSize = 0;
        Iterator<E> iterator = box.iterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (randomElement.equals(next)) {
                trueSize++;
            }
        }
        Assert.assertEquals(trueSize, 1);

    }

    @Test
    public void testToArray() {
        Collection<E> box = newBox();
        E e1 = newRandomElement();
        box.add(e1);
        E e2 = newRandomElement();
        box.add(e2);
        E e3 = newRandomElement();
        box.add(e3);

        Object[] arr2 = box.toArray();


//        Assert.assertTrue(Arrays.equals(arr1, arr2));
        Assert.assertEquals(box.size(), arr2.length);
        for (Object o : arr2) {
            box.remove(o);
        }
        Assert.assertEquals(box.size(), 0);
    }


    @Test
    public void testAdd() {

        int expectedSize = 0;
        Collection<E> box = newBox();
        //测试没有任何元素时size==0
        Assert.assertEquals(expectedSize, box.size());

        box.add(newRandomElement());
        expectedSize++;
        //测试添加一个元素后size++
        Assert.assertEquals(expectedSize, box.size());

        box.add(newRandomElement());
        expectedSize++;
        //测试再次添加一个元素后size再次++
        Assert.assertEquals(expectedSize, box.size());

        if (testAddNull()) {
            box.add(null);
            expectedSize++;
            Assert.assertEquals(expectedSize, box.size());
        }
    }

    @Test
    public void testRemove() {

        int expectedSize = 0;
        Collection<E> box = newBox();
        E e1 = newRandomElement();
        box.add(e1);
        E e2 = newRandomElement();
        box.add(e2);
        E e3 = newRandomElement();
        box.add(e3);
        expectedSize = 3;
        Assert.assertEquals(expectedSize, box.size());

        if (testAddNull()) {
            box.add(null);
            expectedSize++;
            Assert.assertEquals(expectedSize, box.size());
        }
    }

    @Test
    public void testContainsAll() {
        Collection<E> box = newBox();
        E e1 = newRandomElement();
        box.add(e1);
        E e2 = newRandomElement();
        box.add(e2);
        E e3 = newRandomElement();
        box.add(e3);

        Collection<E> box2 = newBox();
        box2.add(e1);
        box2.add(e2);
        Assert.assertTrue(box.containsAll(box2));

        E e4 = newRandomElement();
        box2.add(e4);
        Assert.assertFalse(box.containsAll(box2));
    }

    @Test
    public void testAddAll() {
        Collection<E> box = newBox();
        E e1 = newRandomElement();
        box.add(e1);
        E e2 = newRandomElement();
        box.add(e2);
        E e3 = newRandomElement();
        box.add(e3);

        Collection<E> box2 = newBox();
        E e4 = newRandomElement();
        box2.add(e4);
        E e5 = newRandomElement();
        box2.add(e5);
        E e6 = newRandomElement();
        box2.add(e6);

        box.addAll(box2);
        Assert.assertEquals(box.size(), 6);
    }

    @Test
    public void testClear() {
        Collection<E> box = newBox();
        E e1 = newRandomElement();
        box.add(e1);
        E e2 = newRandomElement();
        box.add(e2);
        E e3 = newRandomElement();
        box.add(e3);

        box.clear();
        Assert.assertEquals(box.size(), 0);
    }


}
