package org.gbhu.session;

import java.io.Closeable;

/**
 *  Cloneable 可以实现
 *         try (SqlSeesion session = sqlSessionFactory.openSession()) {
 *
 *         }
 */
public interface SqlSession extends Closeable {
    <T> T selectList(String statement);
    <T> T selectList(String statement,Object param);

    <T> T getMapper(Class <T> type);
}
