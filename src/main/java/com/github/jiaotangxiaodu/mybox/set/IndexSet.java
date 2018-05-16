package com.github.jiaotangxiaodu.mybox.set;

import com.github.jiaotangxiaodu.mybox.inf.Indexed;

import java.util.Set;

/**
 * 可以对元素创建索引的Set
 * 赋予Set容器中POJO通过索引查找的能力
 * v1.0支持创建多个索引，每次能用一个索引进行查找，不支持复杂pojo
 *
 * @param <E>
 */
public interface IndexSet<E> extends Set<E>, Indexed<E> {

}
