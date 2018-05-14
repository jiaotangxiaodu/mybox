package com.github.jiaotangxiaodu.mybox.set;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

/**
 * 可以对元素创建索引的Set
 * @param <E>
 */
public interface IndexSet<E> extends Set<E> {
    /**
     * 创建索引。你可以创造多个索引,但是每个属性只能创造一个索引。
     * @param indexFieldName 索引的属性名,必须有对应的get方法
     * @param indexStru 索引的结构,推荐TreeSet.class或者HashSet.class
     */
    void index(String indexFieldName, Class<? extends Collection> indexStru);

    /**
     * 通过索引获取元素
     * @param indexFieldName 索引的属性名,必须有对应的get方法
     * @param index
     * @return
     */
    E find(String indexFieldName,Object index);

    /**
     * 通过indexFieldName所构建的索引来判断集合中是否存在元素o
     * @param indexFieldName
     * @param o
     * @return
     */
    default boolean contains(String indexFieldName,Object o){
        return find(indexFieldName,getIndex(indexFieldName,o)) != null;
    }

    /**
     * 获取元素o某一属性的索引
     * @param indexFieldName
     * @param o
     * @return
     */
    default Object getIndex(String indexFieldName, Object o){
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(indexFieldName, o.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            Object index = readMethod.invoke(o);
            return index;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
