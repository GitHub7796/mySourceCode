package org.gbhu.session.defaults;

import org.gbhu.executor.Executor;
import org.gbhu.session.Configuration;
import org.gbhu.session.SqlSession;

import java.io.IOException;
import java.lang.reflect.Type;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectList(String statement) {
        return selectList(statement, null);
    }

    @Override
    public <T> T selectList(String statement, Object param) {
        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }

    private <T> T getMapper(Class<T> type, SqlSession session) {
        return configuration.getMapper(type, this);
    }
    @Override
    public void close() throws IOException {

    }
}
