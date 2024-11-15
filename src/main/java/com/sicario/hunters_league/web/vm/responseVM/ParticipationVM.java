package com.sicario.hunters_league.web.vm.responseVM;

import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.UUID;
@Setter
@Getter
public class ParticipationVM {
    private UUID id;

    private ProfileVM user;

    private CompetitionVM competition;

    private Double score;
}
