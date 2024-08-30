package org.gbhu.builder;

import org.gbhu.session.Configuration;

public abstract class BaseBuilder {
    protected final Configuration configuration;

    protected BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
}
