package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.time.LocalDate;
import java.util.List;

public interface GameManager {
    // User operations

    public User addUser(User d);
    public User addUser(String id,String name, String surname, String email, String birthDate);
    public List<User> getAllUsers();
    public User getUserById(String id);

    // Point of Interest operations
    public Point addPoint(int x, int y, ElementType type);
    public Point getPoint(int x, int y);
    public List<Point> getPointsByType(ElementType type);
    public List<Point> getAllPoints();

    // User-Point interaction
    public void registerUserPoint(String userId, int x, int y) throws Exception;
    public List<Point> getUserPoints(String userId);
    public List<User> getUsersByPoint(int x, int y);

    // Utility methods
    public void clear();
    public int size();
}
