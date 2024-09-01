package org.gbhu.session;

public interface ResultHandler<T> {
    void handleResult(ResultContext<T> resultContext);
//    void handleResult(ResultContext<? extends T> resultContext);
}
