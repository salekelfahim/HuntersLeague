package com.sicario.hunters_league.web.vm.requestVM;

import com.sicario.hunters_league.domain.Enum.Difficulty;
import com.sicario.hunters_league.domain.Enum.SpeciesType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public class SpeciesUpdateVM {

    private UUID id;

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SpeciesType category;

    @NotNull
    @Positive
    private Double minimumWeight;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Difficulty difficulty;

    @NotNull
    @Positive
    private Integer points;
}
