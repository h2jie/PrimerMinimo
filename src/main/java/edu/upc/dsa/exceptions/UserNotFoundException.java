package edu.upc.dsa.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not found");
    }
}