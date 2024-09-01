package org.gbhu.session;

import java.io.Closeable;
import java.util.List;

/**
 * Cloneable 可以实现
 * try (SqlSeesion session = sqlSessionFactory.openSession()) {
 * <p>
 * }
 */
public interface SqlSession extends Closeable {
    <T> List<T> selectList(String statement);

    <T> List<T> selectList(String statement, Object param);

    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();
}
