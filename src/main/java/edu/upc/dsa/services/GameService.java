package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {

    private GameManager gm;

    public GameService() {
        this.gm = GameManagerImpl.getInstance();

            this.gm.addPoint(0,0,ElementType.DOOR);
            this.gm.addPoint(1,0,ElementType.WALL);
            this.gm.addPoint(2,0,ElementType.WALL);



    }

    @GET
    @ApiOperation(value = "get all points", notes = "Gets all points in the game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Point.class, responseContainer="List"),
    })
    @Path("/points")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPoints() {
        List<Point> points = this.gm.getAllPoints();
        GenericEntity<List<Point>> entity = new GenericEntity<List<Point>>(points) {};
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "add a point of interest", notes = "Adds a new point of interest")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Point.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/points")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPoint(Point point) {
        if (point.getType() == null)
            return Response.status(500).build();

        Point p = this.gm.addPoint(point.getX(), point.getY(), point.getType());
        return Response.status(201).entity(p).build();
    }

    @GET
    @ApiOperation(value = "get users by point", notes = "Gets all users who passed through a point")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Point not found")
    })
    @Path("/points/{x}/{y}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByPoint(@PathParam("x") int x, @PathParam("y") int y) {
        List<User> users = this.gm.getUsersByPoint(x, y);
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get points by type", notes = "Gets all points of a specific type")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Point.class, responseContainer="List")
    })
    @Path("/points/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPointsByType(@PathParam("type") ElementType type) {
        List<Point> points = this.gm.getPointsByType(type);
        GenericEntity<List<Point>> entity = new GenericEntity<List<Point>>(points) {};
        return Response.status(201).entity(entity).build();
    }
}