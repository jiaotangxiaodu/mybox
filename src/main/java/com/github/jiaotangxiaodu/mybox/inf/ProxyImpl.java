package com.github.jiaotangxiaodu.mybox.inf;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 * 当容器需要使用动态代理实现时,让容器实现类实现这个接口
 * 此时myBox工厂create方不会返回实现类本身,而是返回newProxy方法返回的动态代理生成的对象
 * 泛型T为需要使用的容器的接口
 */
public interface ProxyImpl<T> {
    /**
     * 返回被代理对象
     * 此方法应当返回代理方法的实际执行者
     *
     * @return
     */
    Object getProxy();

    /**
     * 需要被代理的接口,其中必须包含本类的泛型
     */
    Class<?>[] getInterfaces();

    /**
     * 创建动态代理
     *
     * @return
     */
    default T newProxy() {
        Object proxy = getProxy();
        return (T) Proxy.newProxyInstance(ProxyImpl.class.getClassLoader(), getInterfaces(), (proxy1, method, args) -> {
            try {
                Method proxyMethod = proxy.getClass().getMethod(method.getName(), method.getParameterTypes());
                return proxyMethod.invoke(proxy, args);
            } catch (NoSuchMethodException e) {
                return selfDefineMethod(proxy, method, args);
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * 调用被代理对象中不存在的方法时,此方法会被调用
     *
     * @param proxy 被代理对象
     */
    default Object selfDefineMethod(Object proxy, Method method, Object[] args) throws NoSuchMethodException {
        //代理对象中找不到需要执行的方法
        //如果需要执行代理对象中不存在的方法,需要重写此方法
        throw new RuntimeException("代理对象中没有方法" + method.getName() + ",args = " + Arrays.toString(args) + ",代理对象:" + proxy);
    }
}
