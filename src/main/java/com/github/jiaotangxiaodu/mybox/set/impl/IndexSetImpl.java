package com.github.jiaotangxiaodu.mybox.set.impl;

import com.github.jiaotangxiaodu.mybox.set.IndexSet;

import java.util.*;

/**
 * IndexSet的默认实现,物理结构是HashSet
 * IndexSet要求传入的元素的索引不可重复
 */
public class IndexSetImpl<E> implements IndexSet<E> {

    /**
     * 被装饰者,未创建索引时本类的行为与被装饰者一致
     * 创建索引后,这个对象会被置空
     */
    private Set<E> set = new HashSet<>();

    /**
     * 创建索引后,元素会放在这个List集合里
     */
    private List<E> list;

    /**
     * 标记是否已经创建了索引,未创建时本类的行为和被装饰的Set一致
     */
    private boolean isIndexed = false;

    /**
     * 存放索引的集合,键为索引名,值为存放索引的集合
     */
    private Map<String, Collection> indexMapper;


    @Override
    public int size() {
        return getReference().size();
    }

    @Override
    public boolean isEmpty() {
        return getReference().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        if (!isIndexed) {
            return set.contains(o);
        }
        //获取索引映射里的第一个索引,通过这个索引来判断集合里是否存在o
        return contains(indexMapper.keySet().iterator().next(), o);
    }

    @Override
    public Iterator<E> iterator() {
        return getReference().iterator();
    }

    @Override
    public Object[] toArray() {
        return getReference().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return getReference().toArray(a);
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void index(String indexFieldName, Class<? extends Collection> indexStru) {

    }

    @Override
    public E find(String indexFieldName, Object index) {
        return null;
    }

    /**
     * 获得被装饰者
     * @return
     */
    public Collection<E> getReference() {
        return isIndexed ? list : set;
    }

    /**
     * 索引节点
     */
    static class IndexNode {
        Object index;//索引
        int position;//这个元素在集合的下标
    }


}
