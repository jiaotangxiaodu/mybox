package com.github.jiaotangxiaodu.mybox.set.impl;

import com.github.jiaotangxiaodu.mybox.core.util.CommonUtil;
import com.github.jiaotangxiaodu.mybox.set.IndexSet;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * IndexSet的默认实现
 *
 * @param <E>
 */
public class IndexSetImpl<E> implements IndexSet<E> {

    /**
     * 存放所有索引映射,值为主键值
     * 外层Map：键为索引名（pojo属性名），值为索引序列
     * 第二层Map（索引序列）：键为索引值（pojo索引值），值为该索引所对应的所有元素的主键
     */
    private Map<String, Map<Object, LinkedList<Integer>>> indexMappers = new HashMap<>();
    /**
     * 主键映射
     */
    private HashMap<Integer, E> primaryKeyMap = new HashMap<>();

    /**
     * 自增长的主键值
     */
    private int pkIndex = 0;


    @Override
    public List<E> selectByIndex(String fieldName, Object index) {
        Map<?, LinkedList<Integer>> indexMapper = indexMappers.get(fieldName);

        if(indexMapper == null){
            //此时未建立该字段的索引,自动建索引
            this.createIndex(fieldName);
            return selectByIndex(fieldName,index);
        }

        LinkedList<Integer> primaryKeys = indexMapper.get(index);
        if (primaryKeys == null) {
            return new LinkedList<>();
        }
        List<E> elements = new ArrayList<>();
        for (Integer pk : primaryKeys) {
            elements.add(selectByPK(pk));
        }
        return elements;
    }

    @Override
    public E selectOneByIndex(String fieldName, Object index) {
        Map<?, LinkedList<Integer>> indexCollection = indexMappers.get(fieldName);
        if (indexCollection == null) {
//            throw new RuntimeException("未建立索引：" + fieldName);
            //此时未建立该字段的索引,自动建立
            this.createIndex(fieldName);
            return selectOneByIndex(fieldName,index);
        }
        LinkedList<Integer> primaryKeys = indexCollection.get(index);
        if (primaryKeys == null || primaryKeys.size() == 0) {
            return null;//index的查询结果为空
        }
        return selectByPK(primaryKeys.get(0));
    }

    @Override
    public void createIndex(String fieldName) {
        if (indexMappers.containsKey(fieldName)) {
            throw new RuntimeException("你已经建立过这个索引了:" + fieldName);
        }
        Map<Object, LinkedList<Integer>> indexMapper = createIndexMapper();
        indexMappers.put(fieldName, indexMapper);
        for (Map.Entry<Integer, E> nodeEntry : primaryKeyMap.entrySet()) {
            Integer pk = nodeEntry.getKey();
            E value = nodeEntry.getValue();
            Object index = getElementIndex(fieldName, value);
            LinkedList<Integer> pks = indexMapper.get(index);
            if (pks == null) {
                pks = new LinkedList<>();
                indexMapper.put(index, pks);
            }
            pks.push(pk);
        }
    }


    @Override
    public int size() {
        return primaryKeyMap.size();
    }

    @Override
    public boolean isEmpty() {
        return primaryKeyMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return primaryKeyMap.values().contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return primaryKeyMap.values().iterator();
    }

    @Override
    public Object[] toArray() {
        return primaryKeyMap.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return primaryKeyMap.values().toArray(a);
    }

    @Override
    public boolean add(E e) {

        if (contains(e)) return false;//Set不允许重复元素

        primaryKeyMap.put(pkIndex, e);
        notifyAdd(e);
        pkIndex++;
        return true;
    }


    @Override
    public boolean remove(Object o) {
//        if (!primaryKeyMap.values().contains(o)) return false;
        if (indexMappers.size() > 0) {//有索引的情况下通过第一个索引查找元素来删除
            return remove(o, indexMappers.keySet().iterator().next());
        } else {//没有创建任何索引时遍历全表
            Set<Map.Entry<Integer, E>> pkEntrySet = primaryKeyMap.entrySet();
            for (Map.Entry<Integer, E> pkEntry : pkEntrySet) {
                if (equals(o, pkEntry.getValue())) {
                    primaryKeyMap.remove(pkEntry.getKey());
                    return true;
                }
            }
            return false;
        }
    }


    @Override
    public boolean remove(Object o, String indexFieldName) {
        Object index = getElementIndex(indexFieldName, o);
        Map<Object, LinkedList<Integer>> indexMapper = indexMappers.get(indexFieldName);
        LinkedList<Integer> pks = indexMapper.get(index);
        for (Integer pk : pks) {
            E element = primaryKeyMap.get(pk);
            if (!equals(element, o)) {
                continue;
            }
            primaryKeyMap.remove(pk);
            notifyDelete(element, pk);
            return true;
        }
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return primaryKeyMap.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        for (E e : c) {
            boolean add = add(e);
            flag = flag || add;
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return primaryKeyMap.values().retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object e : c) {
            boolean remove = remove(e);
            flag = flag || remove;
        }
        return flag;
    }

    /**
     * 这个方法会连同索引一起清除，但是不会清除主键自增序列
     */
    @Override
    public void clear() {
        primaryKeyMap.clear();
        indexMappers.clear();
    }

    /**
     * 通过主键获取值
     *
     * @param primaryKey 主键
     */
    private E selectByPK(Integer primaryKey) {
        return primaryKeyMap.get(primaryKey);
    }

    /**
     * 创建索引映射，默认物理结构是HashMap
     */
    private Map<Object, LinkedList<Integer>> createIndexMapper() {
        return new HashMap<>();
    }

    /**
     * 获取pojo的属性（索引值）
     *
     * @param indexFieldName 索引名(POJO的属性名)
     * @param element        需要获取属性的POJO
     * @return 获得的属性值
     */
    public static Object getElementIndex(String indexFieldName, Object element) {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(indexFieldName, element.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            return readMethod.invoke(element);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e.getClass().getName() + "不存在名为" + indexFieldName + "的属性", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通知所有索引某一元素被添加
     *
     * @param e 被添加的元素
     */
    private void notifyAdd(E e) {
        for (Map.Entry<String, Map<Object, LinkedList<Integer>>> indexMapperEntry : indexMappers.entrySet()) {
            String indexFieldName = indexMapperEntry.getKey();
            Map<Object, LinkedList<Integer>> indexMapper = indexMapperEntry.getValue();
            Object index = getElementIndex(indexFieldName, e);
            LinkedList<Integer> pks = indexMapper.get(index);
            if (pks == null) {
                pks = new LinkedList<>();
                indexMapper.put(index, pks);
            }
            pks.push(pkIndex);
        }
    }

    /**
     * 通知所有索引某一元素被删除
     *
     * @param e  被删除的元素
     * @param pk :被删除元素的主键
     */
    private void notifyDelete(E e, Integer pk) {
        for (Map.Entry<String, Map<Object, LinkedList<Integer>>> indexMapperEntry : indexMappers.entrySet()) {
            String indexFieldName = indexMapperEntry.getKey();
            Map<Object, LinkedList<Integer>> indexMapper = indexMapperEntry.getValue();
            Object index = getElementIndex(indexFieldName, e);
            LinkedList<Integer> pks = indexMapper.get(index);
            pks.remove((Object) pk);//这个强转不要删
        }
    }

    private boolean equals(Object o1, Object o2) {
//        if (o1 == null) {
//            return o2 == null;
//        } else {
//            return o1.equals(o2);
//        }
        return CommonUtil.equals(o1, o2);
    }

    @Override
    public String toString() {
        return primaryKeyMap.toString();
    }

    @Override
    public Set<String> showIndexs(){
        return indexMappers.keySet();
    }
}
