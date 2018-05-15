package com.github.jiaotangxiaodu.mybox.inf;

import java.util.List;

/**
 * 带有索引的容器
 * @param <E>
 */
public interface Indexed<E> {

    /**
     * 通过索引查找元素
     *
     * @param fieldName pojo中的属性名（索引名）
     * @param index     索引值
     * @return
     */
    List<E> selectByIndex(String fieldName, Object index);

    /**
     * 通过索引查找单个元素
     *
     * @param fieldName pojo中的属性名（索引名）
     * @param index     索引值
     * @return
     */
    E selectOneByIndex(String fieldName, Object index);

    /**
     * 为某一属性创建索引
     *
     * @param fieldName pojo的属性名
     */
    void createIndex(String fieldName);

    /**
     * 通过某一索引来查找并删除元素o
     *
     * @param o
     * @param indexFieldName
     * @return
     */
    boolean remove(Object o, String indexFieldName);
}
