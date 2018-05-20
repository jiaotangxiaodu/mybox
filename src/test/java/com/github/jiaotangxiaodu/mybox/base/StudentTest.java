package com.github.jiaotangxiaodu.mybox.base;

import com.github.jiaotangxiaodu.mybox.pojo.Student;

import java.util.Random;
import java.util.UUID;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public interface StudentTest {
    /**
     * 返回一个随机生成的元素,任意两次生成的element之间调用equals都应该返回false
     * 不能返回null
     */
    default Student newRandomElement(){
        return new Student(UUID.randomUUID().toString().trim(),new Random().nextInt(100),new Random().nextBoolean()?"male":"female");
    };

    /**
     * 返回一个特定的元素,任意两次返回的Element之间调用Equals都应该返回true
     * 不能返回null
     */
    default Student getSpecialElement(){
        return new Student("nglszs",12,"male");
    };
}
