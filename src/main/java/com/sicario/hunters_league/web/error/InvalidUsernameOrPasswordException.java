package com.sicario.hunters_league.web.error;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password.");
    }
}
