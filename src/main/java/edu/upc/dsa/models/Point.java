package edu.upc.dsa.models;

import java.util.Objects;

public class Point {
    private int x;
    private int y;
    private ElementType type;

    public Point() {}

    public Point(int x, int y, ElementType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // Getters and Setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public ElementType getType() { return type; }
    public void setType(ElementType type) { this.type = type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + ", type=" + type + "]";
    }

}