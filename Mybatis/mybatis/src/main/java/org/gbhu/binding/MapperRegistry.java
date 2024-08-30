package org.gbhu.binding;

import org.gbhu.session.Configuration;
import org.gbhu.session.SqlSession;
import org.gbhu.session.defaults.DefaultSqlSession;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
    private final Configuration configuration;
    private final Map<Class<?>,MapperProxyFactory<?>> knownMappers = new HashMap<>();
    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        return mapperProxyFactory.newInstance(sqlSession);
    }


}
