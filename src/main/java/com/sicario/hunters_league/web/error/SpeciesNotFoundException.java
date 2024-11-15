package com.sicario.hunters_league.web.error;

public class SpeciesNotFoundException extends RuntimeException {
    public SpeciesNotFoundException() {
        super("Species not found");
    }
}
