package org.nightschool.controller;

import org.nightschool.dao.UserDao;
import org.nightschool.model.Disk;
import org.nightschool.model.RequestResult;
import org.nightschool.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by Thoughtworks on 3/4/15.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    private final UserDao userDao = new UserDao();

    @GET
    public ArrayList<User> list() {
        return userDao.getAllUser();
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public RequestResult getUser(User user) {

        User dbUser = userDao.getUserByName(user.getUser_name());

        if (null == dbUser) {
            return new RequestResult(RequestResult.FAIL, user.getUser_name(), null, "用户不存在!");
        } else {
            if (user.getPassword().equals(dbUser.getPassword())) {
                return new RequestResult(RequestResult.OK, user.getUser_name(), dbUser.getType(), null);
            } else {
                return new RequestResult(RequestResult.FAIL, user.getUser_name(), null, "密码错误!");
            }
        }
    }

    @Path("/register")
    @POST
    public void addUser(User user) {
        System.out.println(user.getUser_name());
        userDao.addUser(user);}
}
