package com.sicario.hunters_league.web.error;

public class ParticipationNotFoundException extends RuntimeException {
    public ParticipationNotFoundException() {
        super("Participation not found");
    }
}
