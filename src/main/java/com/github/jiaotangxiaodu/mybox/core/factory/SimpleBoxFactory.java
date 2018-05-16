package com.github.jiaotangxiaodu.mybox.core.factory;

import com.github.jiaotangxiaodu.mybox.core.context.BoxContext;
import com.github.jiaotangxiaodu.mybox.core.context.XMLConfigurationContext;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * BoxFactory的简单实现
 */
public class SimpleBoxFactory implements BoxFactory {

    /**
     * 容器上下文,内部维护容器接口字节码和实现类的映射关系
     */
    private BoxContext boxContext;

    public void setBoxContext(BoxContext boxContext) {
        this.boxContext = boxContext;
    }

    public void initDefaultContext() {
        this.boxContext = new XMLConfigurationContext("boxApplication.xml");
    }

    @Override
    public <T> T create(Class<T> boxType, Object... args) {
        if (boxContext == null) {
            initDefaultContext();
        }
        Class<? extends T> targetClass = boxContext.get(boxType);

        if (targetClass == null) {
            throw new RuntimeException("class=" + boxType.getName() + ",args=" + Arrays.toString(args) + ",未注册到boxContext中");
        }

        try {
            if (args == null || args.length == 0) {
                return invokeNullArgsConstructor(targetClass);
            } else {
                return invokeArgsConstructor(targetClass, args);
            }
        } catch (Exception e) {
            throw new RuntimeException("构造容器失败", e);
        }
    }

    /**
     * 使用空参构造创建容器
     *
     * @param boxType
     * @param <T>
     * @return
     */
    private <T> T invokeNullArgsConstructor(Class<T> boxType) {
        try {
            Constructor<T> constructor = boxType.getConstructor();
            T t = constructor.newInstance();
            return t;
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("容器不存在可用的空参构造", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T invokeArgsConstructor(Class<T> boxType, Object[] args) {
        throw new UnsupportedOperationException("暂时不支持带参数的构造");
    }
}
