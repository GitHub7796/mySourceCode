package org.gbhu.builder;

import org.gbhu.mapping.MappedStatement;
import org.gbhu.mapping.SqlCommandType;
import org.gbhu.mapping.StatementType;
import org.gbhu.session.Configuration;

public class MapperBuilderAssistant extends BaseBuilder {
    private final String resource;
    private String currentNamespace;

    public MapperBuilderAssistant(Configuration configuration, String resource) {
        super(configuration);
        this.resource = resource;
    }

    public String getCurrentNamespace() {
        return currentNamespace;
    }

    public void setCurrentNamespace(String namespace) {
        this.currentNamespace = namespace;
    }

    public MappedStatement addMappedStatement(String id, SqlCommandType sqlCommandType, StatementType statementType, String sqlSource) {
        MappedStatement.Builder statementBuilder=new MappedStatement.Builder(configuration,id, sqlCommandType, statementType, sqlSource);
        MappedStatement statement = statementBuilder.build();
        configuration.addMappedStatement(statement);
        return statement;
    }

}
