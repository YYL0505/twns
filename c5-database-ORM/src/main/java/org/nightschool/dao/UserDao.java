package org.nightschool.dao;

import org.apache.ibatis.session.SqlSession;
import org.nightschool.demo.MybatisUtils;
import org.nightschool.mapper.UserMapper;
import org.nightschool.model.User;

import java.util.ArrayList;

/**
 * Created by Thoughtworks on 3/4/15.
 */
public class UserDao {
    private SqlSession session;
    private UserMapper userMapper;


    public UserDao() {
        session = MybatisUtils.getFactory().openSession();
        userMapper = session.getMapper(UserMapper.class);
    }
    public User getUserByName(String user_name) {
        User user = new User();

        try {
            user = userMapper.getUserByName(user_name);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }

        return user;
    }

    public void addUser(User user) {
        try {
            userMapper.insertUser(user);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();

        try {
            users = (ArrayList)userMapper.getAllUer();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
        }
        return users;
    }
}
