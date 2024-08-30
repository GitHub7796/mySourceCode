package org.gbhu.io;

import java.io.InputStream;

public class ClassLoaderWrapper {

    ClassLoader systemClassLoader;

    ClassLoaderWrapper() {
        this.systemClassLoader = ClassLoader.getSystemClassLoader();
    }
    InputStream getResourceAsStream(String resource) {
        return this.systemClassLoader.getResourceAsStream(resource);
    }
}
