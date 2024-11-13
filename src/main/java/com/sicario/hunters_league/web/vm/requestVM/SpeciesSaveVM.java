package com.sicario.hunters_league.web.vm.requestVM;

import com.sicario.hunters_league.domain.Enum.Difficulty;
import com.sicario.hunters_league.domain.Enum.SpeciesType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SpeciesSaveVM {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category cannot be null.")
    private SpeciesType category;

    @NotNull(message = "Minimum weight cannot be null.")
    @Positive(message = "Minimum weight must be a positive value.")
    private Double minimumWeight;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Difficulty cannot be null.")
    private Difficulty difficulty;

    @NotNull(message = "Points cannot be null.")
    @Positive(message = "Points must be a positive value.")
    private Integer points;
}
