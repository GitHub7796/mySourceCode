package org.gbhu.executor;

import org.gbhu.mapping.MappedStatement;
import org.gbhu.session.Configuration;

import java.util.Collections;
import java.util.List;

//TODO BaseExecutor 源码中是抽象的，还体会不到
public abstract class BaseExecutor implements Executor {

    protected Configuration configuration;
    protected Executor executor;

    public BaseExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> List<T> query(MappedStatement ms, Object param) {
        return Collections.emptyList();
    }
}
