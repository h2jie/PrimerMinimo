// GameManagerTest.java
package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class GameManagerTest {
    GameManager gm;

    @Before
    public void setUp() {
        this.gm = GameManagerImpl.getInstance();

        // Add test users
        this.gm.addUser("U1","John", "Doe", "john@example.com",
                "1990, 1, 1");
        this.gm.addUser("U2", "Jane", "Smith", "jane@example.com",
                "1992, 2, 2");

        // Add test points
        this.gm.addPoint(1, 1, ElementType.DOOR);
        this.gm.addPoint(2, 2, ElementType.WALL);
        this.gm.addPoint(3, 3, ElementType.BRIDGE);
    }

    @After
    public void tearDown() {
        this.gm.clear();
    }

    @Test
    public void testAddUser() {
        Assert.assertEquals(2, gm.size());

        this.gm.addUser( "U1","Bob", "Brown", "bob@example.com",
                "1995, 5, 5");

        Assert.assertEquals(3, gm.size());

        User u = this.gm.getUserById("U3");
        Assert.assertEquals("Bob", u.getName());
        Assert.assertEquals("Brown", u.getSurname());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = this.gm.getAllUsers();
        Assert.assertEquals(2, users.size());

        // Check if sorted by surname
        Assert.assertEquals("Doe", users.get(0).getSurname());
        Assert.assertEquals("Smith", users.get(1).getSurname());
    }

    @Test
    public void testRegisterUserPoint() throws Exception {
        this.gm.registerUserPoint("U1", 1, 1);
        List<Point> points = this.gm.getUserPoints("U1");
        Assert.assertEquals(1, points.size());
        Assert.assertEquals(ElementType.DOOR, points.get(0).getType());
    }

    @Test
    public void testGetUsersByPoint() throws Exception {
        this.gm.registerUserPoint("U1", 1, 1);
        this.gm.registerUserPoint("U2", 1, 1);

        List<User> users = this.gm.getUsersByPoint(1, 1);
        Assert.assertEquals(2, users.size());
    }

    @Test
    public void testGetPointsByType() {
        List<Point> walls = this.gm.getPointsByType(ElementType.WALL);
        Assert.assertEquals(1, walls.size());
        Assert.assertEquals(2, walls.get(0).getX());
        Assert.assertEquals(2, walls.get(0).getY());
    }

    @Test(expected = Exception.class)
    public void testRegisterUserPointInvalidUser() throws Exception {
        this.gm.registerUserPoint("INVALID", 1, 1);
    }

    @Test(expected = Exception.class)
    public void testRegisterUserPointInvalidPoint() throws Exception {
        this.gm.registerUserPoint("U1", 99, 99);
    }
}