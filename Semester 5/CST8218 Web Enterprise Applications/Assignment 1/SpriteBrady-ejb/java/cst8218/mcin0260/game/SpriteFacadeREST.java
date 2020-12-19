/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.mcin0260.game;

import cst8218.mcin0260.game.Sprite;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Typed REST Facade provides REST functionality for CRUD operations
 * on a Sprite entity, including POST, PUT and GET.
 * 
 * @author Brady
 */
@Stateless
@RolesAllowed({"Admin", "RestGroup"})
@Path("cst8218.mcin0260.game.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "SpriteBradyPU")
    private EntityManager em;

    public SpriteFacadeREST() {
        super(Sprite.class);
    }

    /**
     * Prints the POSTed object fields to console.
     * 
     * @param entity Posted object.
     */
    @POST
    @Path("test")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void test(Sprite entity) {
        System.out.println(
                "id:"+entity.getId());
        System.out.println(
                "pw:"+entity.getPanelWidth());
        System.out.println(
                "ph:"+entity.getPanelHeight());
        System.out.println(
                "x:"+entity.getX());
        System.out.println(
                "y:"+entity.getY());
        System.out.println(
                "dx:"+entity.getDx());
        System.out.println(
                "dy:"+entity.getDy());
        System.out.println(
                "co:"+entity.getColor());
        
    }
    
    /**
     * Handles POSTing on the root resource.
     * 
     * If posted id is null, a new Sprite is created.
     * If posted id is not null and owned by an extant Sprite entity, 
     *  that Sprite is updated with non-null field values.
     * If posted id is not null and not owned by any extant Sprite entities,
     *  a Forbidden response is returned.
     * 
     * @param entity De-serialized Sprite object
     * @return HTTP Response message
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postRoot(Sprite entity) {

        Status rsp;
        String msg;

        if(entity.getId() != null) {
            Sprite s = super.find(entity.getId());
            if(s != null) {
                s.update(entity);
                msg = "Successfully updated Sprite.";
                rsp = Response.Status.OK;
            }
            else {
                msg = "Cannot create Sprite with custom id.";
                rsp = Response.Status.FORBIDDEN;
            }
        }
        else {
            super.create(entity);
            msg = "Successfully created Sprite.";
            rsp = Response.Status.CREATED;
        }

        return Response.status(rsp).entity(msg).build();
    }
    
    /**
     * Handles POSTing on the id resource.
     * 
     * If the POST id does not exist, a Forbidden response is returned.
     * If the POST id exists and the parameter Sprite has an id field that 
     *  does not match the POST id, a Bad Request response is returned.
     * If the POST id exists and matches the parameter Sprite id field,
     *  the extant Sprite owning this id is updated with non-null values.
     * 
     * @param id POST id
     * @param entity De-serialized Sprite object
     * @return HTTP Response message
     */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postId(@PathParam("id") Long id, Sprite entity) {
        
        Status rsp;
        String msg;
        
        Sprite s = super.find(id);
        if(s != null) {
            if(s.getId() != id) {
                msg = "Path id and Entity id do not match.";
                rsp = Response.Status.BAD_REQUEST;
            }
            else {
                s.update(entity);
                msg = "Successfully updated Sprite.";
                rsp = Response.Status.OK;
            }
        }
        else {
            msg = "Cannot create Sprite with custom id.";
            rsp = Response.Status.FORBIDDEN;
        }
        
        return Response.status(rsp).entity(msg).build();
    }

    /**
     * Handles PUTing on the id resource.
     * 
     * If the PUT id does not exist, a Not Found response is returned.
     * If the PUT id exists and the parameter Sprite has an id field that 
     *  does not match the PUT id, a Bad Request response is returned.
     * If the PUT id exists and matches the parameter Sprite id field,
     *  the extant Sprite owning this id is replaced with the new Sprite.
     * 
     * @param id POST id
     * @param entity De-serialized Sprite object
     * @return HTTP Response message
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putId(@PathParam("id") Long id, Sprite entity) {
        
        Status rsp;
        String msg;
        
        Sprite s = super.find(id);
        if(s != null) {
            if(s.getId() != id) {
                msg = "Path id and Entity id do not match.";
                rsp = Response.Status.BAD_REQUEST;
            }
            else {
                super.edit(entity);
                msg = "Successfully replaced Sprite.";
                rsp = Response.Status.OK;
            }
        }
        else {
            msg = "No Sprite exists at that id.";
            rsp = Response.Status.NOT_FOUND;
        }
        
        return Response.status(rsp).entity(msg).build();
    }

//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Long id) {
//        super.remove(super.find(id));
//    }

//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Sprite find(@PathParam("id") Long id) {
//        return super.find(id);
//    }

//    @GET
//    @Override
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Sprite> findAll() {
//        return super.findAll();
//    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }

    /**
     * Handles GETing on the count resource.
     * 
     * Returns the number of Sprites in the database.
     * 
     * @return Number of Sprites.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCount() {
        return Response
                .status(Response.Status.OK)
                .entity(String.valueOf(super.count()))
                .build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
