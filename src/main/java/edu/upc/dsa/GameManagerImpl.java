package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.util.RandomUtils;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private List<User> users;
    private List<Point> points;
    private Map<String, List<Point>> userPoints;

    private GameManagerImpl() {
        this.users = new LinkedList<>();
        this.points = new LinkedList<>();
        this.userPoints = new HashMap<>();
    }

    public static GameManager getInstance() {
        if (instance == null) instance = new GameManagerImpl();
        return instance;
    }

    @Override
    public User addUser(User u) {
        logger.info("Adding new drone: " + u);
        this.users.add(u);
        logger.info("User added successfully");
        return u;

    }

    @Override
    public User addUser(String id, String name, String surname, String email, String birthDate) {

        return this.addUser(new User(id,name, surname, email, birthDate));
    }


    @Override
    public List<User> getAllUsers() {
        logger.info("Getting all users");
        users.sort((u1, u2) -> {
            int surnameCompare = u1.getSurname().compareTo(u2.getSurname());
            return surnameCompare != 0 ? surnameCompare : u1.getName().compareTo(u2.getName());
        });
        return users;
    }

    @Override
    public User getUserById(String id) {
        logger.info("Getting user by id: " + id);
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<Point> getAllPoints() {
        logger.info("Getting all points");
        return new ArrayList<>(points);
    }

    @Override
    public Point addPoint(int x, int y, ElementType type) {
        logger.info("Adding point: (" + x + "," + y + ") of type " + type);

        Point existingPoint = getPoint(x, y);
        if (existingPoint != null) {
            logger.info("Point already exists at coordinates (" + x + "," + y + ")");
            return existingPoint;
        }

        Point point = new Point(x, y, type);
        points.add(point);
        logger.info("Point added successfully");
        return point;
    }

    @Override
    public Point getPoint(int x, int y) {
        logger.info("Getting point at coordinates (" + x + "," + y + ")");
        return points.stream()
                .filter(p -> p.getX() == x && p.getY() == y)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Point> getPointsByType(ElementType type) {
        logger.info("Getting points by type: " + type);
        List<Point> result = new ArrayList<>();
        for (Point p : points) {
            if (p.getType() == type)
                result.add(p);
        }
        return result;
    }

    @Override
    public void registerUserPoint(String userId, int x, int y) throws Exception {
        logger.info("Registering point (" + x + "," + y + ") for user: " + userId);

        User user = getUserById(userId);
        if (user == null) {
            logger.error("User not found: " + userId);
            throw new Exception("User not found");
        }

        Point point = getPoint(x, y);
        if (point == null) {
            logger.error("Point not found at coordinates (" + x + "," + y + ")");
            throw new Exception("Point not found");
        }

        if (!userPoints.containsKey(userId)) {
            userPoints.put(userId, new ArrayList<>());
        }

        userPoints.get(userId).add(point);
        logger.info("Point registered successfully for user " + userId);
    }

    @Override
    public List<Point> getUserPoints(String userId) {
        logger.info("Getting points for user: " + userId);

        User user = getUserById(userId);
        if (user == null) {
            logger.warn("User not found: " + userId);
            return new ArrayList<>();
        }

        List<Point> points = userPoints.getOrDefault(userId, new ArrayList<>());
        logger.info("Found " + points.size() + " points for user " + userId);
        return points;
    }

    @Override
    public List<User> getUsersByPoint(int x, int y) {
        logger.info("Getting users for point: (" + x + "," + y + ")");

        Point targetPoint = getPoint(x, y);
        if (targetPoint == null) {
            logger.warn("Point not found at coordinates (" + x + "," + y + ")");
            return new ArrayList<>();
        }

        List<User> result = new ArrayList<>();
        for (Map.Entry<String, List<Point>> entry : userPoints.entrySet()) {
            if (entry.getValue().stream().anyMatch(p ->
                    p.getX() == targetPoint.getX() && p.getY() == targetPoint.getY())) {
                User user = getUserById(entry.getKey());
                if (user != null) {
                    result.add(user);
                }
            }
        }

        logger.info("Found " + result.size() + " users for point (" + x + "," + y + ")");
        return result;
    }

    @Override
    public void clear() {
        users.clear();
        points.clear();
        userPoints.clear();
    }

    @Override
    public int size() {
        return users.size();
    }
}