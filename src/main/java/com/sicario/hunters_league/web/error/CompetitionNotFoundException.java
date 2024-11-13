package com.sicario.hunters_league.web.error;

public class CompetitionNotFoundException extends RuntimeException {
    public CompetitionNotFoundException() {
        super("Competition not found");
    }
}
