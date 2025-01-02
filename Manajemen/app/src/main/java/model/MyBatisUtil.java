package model;

import model.UserMapper;
import model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // Memperbaiki path untuk mybatis-config.xml
            InputStream inputStream = MyBatisUtil.class.getResourceAsStream("/mybatis-config.xml");
            if (inputStream == null) {
                throw new RuntimeException("mybatis-config.xml not found in classpath");
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing MyBatis: " + e.getMessage());
        }
    }

    public static void insertUser(User user) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insertUser(user); // Memanggil metode insertUser dari mapper
            session.commit();
            System.out.println("User successfully inserted!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    public static SqlSession getSession() {
        // Menambahkan implementasi getSession untuk mengembalikan SqlSession
        return sqlSessionFactory.openSession();
    }
}
