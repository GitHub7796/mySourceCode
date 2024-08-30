package org.gbhu.session;

import org.gbhu.binding.MapperRegistry;
import org.gbhu.executor.Executor;
import org.gbhu.executor.SimpleExecutor;
import org.gbhu.session.defaults.DefaultSqlSession;

public class Configuration {
    protected ExecutorType defaultExecutorType = ExecutorType.SIMPLE;
    protected MapperRegistry mapperRegistry=new MapperRegistry(this);
    public Executor newExecutor(ExecutorType executorType) {
        executorType = executorType == null?this.defaultExecutorType:executorType;
        if (executorType == ExecutorType.SIMPLE) {
            return new SimpleExecutor();
        }
        return null;
    }

    public <T> T getMapper(Class<T> type, SqlSession session) {
        return mapperRegistry.getMapper(type, session);
    }
}
