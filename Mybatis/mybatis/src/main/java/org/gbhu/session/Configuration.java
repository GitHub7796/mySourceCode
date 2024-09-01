package org.gbhu.session;

import org.gbhu.binding.MapperRegistry;
import org.gbhu.executor.Executor;
import org.gbhu.executor.SimpleExecutor;
import org.gbhu.mapping.MappedStatement;
import org.gbhu.scripting.LanguageDriver;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    protected ExecutorType defaultExecutorType = ExecutorType.SIMPLE;
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public Executor newExecutor(ExecutorType executorType) {
        executorType = executorType == null ? this.defaultExecutorType : executorType;
        if (executorType == ExecutorType.SIMPLE) {
            return new SimpleExecutor(this);
        }
        return null;
    }

    public <T> T getMapper(Class<T> type, SqlSession session) {
        return mapperRegistry.getMapper(type, session);
    }


    public void addMapper(Class<?> boundType) {
        mapperRegistry.addMapper(boundType);
    }


    public void addMappedStatement(MappedStatement ms) {
        this.mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }
}
