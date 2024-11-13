package com.sicario.hunters_league.web.vm.responseVM;

import com.sicario.hunters_league.domain.Enum.Difficulty;
import com.sicario.hunters_league.domain.Enum.SpeciesType;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public class SpeciesVM {

    private UUID id;

    private String name;

    private SpeciesType category;

    private Double minimumWeight;

    private Difficulty difficulty;

    private Integer points;
}
