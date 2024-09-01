package org.gbhu.binding;

import org.gbhu.mapping.SqlCommandType;
import org.gbhu.session.Configuration;
import org.gbhu.session.SqlSession;

import java.lang.reflect.Method;

public class MapperMethod {
    // sql相关信息
    private final SqlCommand command;
    // 方法签名
    private final MethodSignature method;
    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(mapperInterface, method, configuration);
        this.method = new MethodSignature(mapperInterface, method, configuration);
    }

    public Object execute(SqlSession sqlSession) {
        return sqlSession.selectList(command.name);
    }

    public static class SqlCommand {
        /**
         * EG:mapper.UserMapper.listUser
         */
        private final String name;
        /**
         * 查询类型 SELECT
         */
        private final SqlCommandType type;

        public SqlCommand(Class<?> mapperInterface, Method method, Configuration configuration) {
            final String methodName = method.getName();
            final Class<?> declaringClass = method.getDeclaringClass();
            this.name = mapperInterface.getName() +"."+ methodName;
            //TODO 有点复杂，先省略
            this.type = SqlCommandType.SELECT;
        }
    }

    public static class MethodSignature {
        //返回类型
        private final Class<?> returnType;

        public MethodSignature(Class<?> mapperInterface, Method method, Configuration configuration) {
            this.returnType = method.getReturnType();
        }
    }
}

