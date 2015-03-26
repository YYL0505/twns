package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.Disk;
import org.nightschool.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 12/30/14.
 */
public interface UserMapper {

    @Select("select user_name from users")
    ArrayList<User> getAllUer();

    @Select("select user_name, password, type from users where user_name = #{user_name};")
    User getUserByName(String user_name);

    @Insert("insert into users(user_name, type, password) values(#{user_name}, #{type}, #{password})")
    public void insertUser(User user);
}
