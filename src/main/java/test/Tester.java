package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import test.dao.UserMapper;
import test.model.User;
import test.model.UserExample;

import java.io.Reader;
import java.util.List;

/**
 * Created by Administrator on 5/16/2016.
 */
public class Tester {

    public static void main(String[] args) throws Exception {
        String resource = "config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlMapper.openSession();

        UserExample user = new UserExample();
        user.or().andGenderIsNull();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> allRecords = mapper.selectByExample(user);
            for (User s : allRecords)
                System.out.println(s);
        } finally {
            sqlSession.close();
        }
    }
}
