package org.gbhu.executor;

import org.gbhu.mapping.MappedStatement;
import org.gbhu.session.ResultHandler;

import java.util.List;

public interface Executor {
    ResultHandler NO_RESULT_HANDLER = null;

    <T> List<T> query(MappedStatement ms, Object param);
}
