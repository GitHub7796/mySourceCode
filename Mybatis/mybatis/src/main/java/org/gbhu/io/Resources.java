package org.gbhu.io;

import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.io.IOException;
import java.io.InputStream;

public class Resources {
    private static ClassLoaderWrapper classLoaderWrapper = new ClassLoaderWrapper();

    public static InputStream getResourceAsStream(String resource) throws IOException {
        InputStream is = classLoaderWrapper.getResourceAsStream(resource);
        if (is == null) {
            throw new IOException("Could not find resource " + resource);
        }
        return is;
    }
}
