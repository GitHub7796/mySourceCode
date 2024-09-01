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

    public Class<?> classForName(String className) {
        try {
            return Class.forName(className, true, systemClassLoader);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
