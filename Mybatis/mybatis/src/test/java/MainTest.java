import domain.User;
import mapper.UserMapper;
import org.gbhu.io.Resources;
import org.gbhu.session.SqlSession;
import org.gbhu.session.SqlSessionFactory;
import org.gbhu.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws IOException {
//    1、解析XML配置
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
//    2、获取session
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
//      3、mysql映射；sql语句执行
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> user = mapper.listUser();
            System.out.println(user);
        }
    }
}
