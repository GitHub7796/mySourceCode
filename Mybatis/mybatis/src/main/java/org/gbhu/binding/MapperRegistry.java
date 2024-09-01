package org.gbhu.binding;

import org.gbhu.session.Configuration;
import org.gbhu.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
    private final Configuration configuration;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public void addMapper(Class<?> type) {
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }
}
