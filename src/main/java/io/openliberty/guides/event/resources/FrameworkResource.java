package io.openliberty.guides.event.resources;


import io.openliberty.guides.event.dao.FrameworkDao;
import io.openliberty.guides.event.models.Framework;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.util.List;

@Stateless
@Path("frameworks")
public class FrameworkResource {

    private static Logger logger = LogManager.getLogger(FrameworkResource.class);

    @Inject
    private FrameworkDao frameworkDao;

    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFramework(Framework framework) {
        logger.debug("start createFramework(framework) " + framework);

        List<Framework> frameworks = null;
        frameworks = frameworkDao.getFrameworkByName(framework.getName());
        if (!frameworks.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).entity("Entity already exists").build();

        frameworkDao.save(framework);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(framework.getId())).build();
        logger.debug("finish createFramework called with " + framework);
        return Response.ok().location(uri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFrameworks() {
        return Response.ok().entity(frameworkDao.getFrameworks()).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFramework(@PathParam("id") int id) {
        return Response.ok().entity(frameworkDao.getById(id)).build();
    }

}
