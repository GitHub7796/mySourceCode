package org.gbhu.mapping;

import org.gbhu.session.Configuration;

public final class MappedStatement {
    private Configuration configuration;
    private String id;
    private String source;
    private StatementType statementType;
    private ResultSetType resultSetType;
    private SqlCommandType sqlCommandType;
    private String sqlSource;

    public static class Builder{
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, StatementType statementType, String sqlSource) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.statementType = statementType;
            mappedStatement.sqlSource = sqlSource;
        }

        public MappedStatement build() {
            return mappedStatement;
        }

    }


    public String getId() {
        return id;
    }
}
