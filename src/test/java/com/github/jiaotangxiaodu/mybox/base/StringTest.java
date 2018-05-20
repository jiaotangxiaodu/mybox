package com.github.jiaotangxiaodu.mybox.base;

import java.util.UUID;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public interface StringTest {
    /**
     * 返回一个随机生成的元素,任意两次生成的element之间调用equals都应该返回false
     * 不能返回null
     */
    default String newRandomElement() {
        return UUID.randomUUID().toString().trim();
    }

    /**
     * 返回一个特定的元素,任意两次返回的Element之间调用Equals都应该返回true
     * 不能返回null
     */
    default String getSpecialElement() {
        return "2333";
    }
}
