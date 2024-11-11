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

@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UserService {
    private GameManager gm;

    public UserService() {
        this.gm = GameManagerImpl.getInstance();

        if(gm.size()==0) {
            this.gm.addUser("U1", "John", "Doe", "john@example.com",
                    "1990, 1, 1");
            this.gm.addUser("U2", "Jane", "Smith", "jane@example.com",
                    "1992, 2, 2");
        }
    }

    @POST
    @ApiOperation(value = "create a new user", notes = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        if (user.getName() == null || user.getSurname() == null)
            return Response.status(500).build();

        User u = this.gm.addUser(user.getId(), user.getName(), user.getSurname(),
                user.getEmail(), user.getBirthDate());
        return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "get all users", notes = "Gets all users ordered by surname and name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = this.gm.getAllUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get a User", notes = "Gets a user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User u = this.gm.getUserById(id);
        if (u == null) return Response.status(404).build();
        return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "get user points", notes = "Gets all points a user has passed through")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Point.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{userId}/points")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPoints(@PathParam("userId") String userId) {
        List<Point> points = this.gm.getUserPoints(userId);
        GenericEntity<List<Point>> entity = new GenericEntity<List<Point>>(points) {};
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "register user point", notes = "Records a user passing through a point")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User or Point not found"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/{userId}/points")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUserPoint(@PathParam("userId") String userId, Point point) {
        try {
            this.gm.registerUserPoint(userId, point.getX(), point.getY());
            return Response.status(201).build();
        } catch (Exception e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }
}