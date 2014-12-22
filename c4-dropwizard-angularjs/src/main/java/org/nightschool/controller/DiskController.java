package org.nightschool.controller;

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

    private final ArrayList<Disk> disks = new ArrayList<>();

    @GET
    public List<Disk> list() {
        return disks;
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Disk add(Disk disk) {
        disks.add(disk);
        return disk;
    }
}
