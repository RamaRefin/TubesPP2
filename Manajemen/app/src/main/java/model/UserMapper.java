package model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

public interface UserMapper {

    @Insert("INSERT INTO users (username, email, password) VALUES (#{username}, #{email}, #{password})")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectUserById(int id);

    @Update("UPDATE users SET username = #{username}, email = #{email}, password = #{password} WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(int id);
}
