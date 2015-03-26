package org.nightschool.controller;

import org.nightschool.dao.CartDao;
import org.nightschool.model.Disk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 12/26/14.
 */
@Path("/cart")
public class CartController {
    CartDao cartDao = new CartDao();

    @GET
    @Path("{user_name}")
    public ArrayList<Disk> list(@PathParam("user_name") String user_name){
        return cartDao.cartList(user_name);
    }

    @POST
    @Path("add/{user_name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(@PathParam("user_name") String user_name, Disk disk) {
        cartDao.add(disk, user_name);
    }

    @DELETE
    @Path("remove/{id}")
    public void removeDisk(@PathParam("id") int id) {
        cartDao.removeDisk(id);
    }
}
