package org.gbhu.mapping;

public interface SqlSource {
    BoundSql getBoundSql(Object parameterObject);
}
