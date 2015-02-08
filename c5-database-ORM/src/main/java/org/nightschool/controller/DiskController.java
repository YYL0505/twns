package org.nightschool.controller;

import org.nightschool.dao.DiskDao;
import org.nightschool.model.Disk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 12/20/14.
 */
@Path("/disks")
@Produces(MediaType.APPLICATION_JSON)
public class DiskController {
    private final DiskDao diskDao = new DiskDao();

    @GET
    public ArrayList<Disk> list() {
        return diskDao.listDisks();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Disk disk) {
        diskDao.add(disk);
    }

    @DELETE
    @Path("remove/{name}")
    public void remove(@PathParam("name") String name) {
        diskDao.remove(name);
    }
}
