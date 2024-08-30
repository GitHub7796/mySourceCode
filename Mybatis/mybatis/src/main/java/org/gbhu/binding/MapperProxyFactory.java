package org.gbhu.binding;

import org.gbhu.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;
    private final Map<Method, MapperRegistry> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(MapperProxy mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy mapperProxy = new MapperProxy(sqlSession, mapperInterface, methodCache);
        return newInstance(mapperProxy);
    }
}
