package org.gbhu.session;

import org.gbhu.executor.Executor;
import org.gbhu.session.defaults.DefaultSqlSession;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return openSessionFromDataSource(configuration.defaultExecutorType);
    }

    private SqlSession openSessionFromDataSource(ExecutorType executorType) {
        Executor executor = configuration.newExecutor(executorType);
        return new DefaultSqlSession(configuration, executor);
    }
}
