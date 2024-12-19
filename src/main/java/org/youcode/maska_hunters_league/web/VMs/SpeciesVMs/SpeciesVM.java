package org.youcode.maska_hunters_league.web.VMs.SpeciesVMs;

import jakarta.validation.constraints.*;
import lombok.*;
import org.youcode.maska_hunters_league.domain.enums.Difficulty;
import org.youcode.maska_hunters_league.domain.enums.SpeciesType;
import org.youcode.maska_hunters_league.validation.EnumValue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpeciesVM {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @EnumValue(enumClass = SpeciesType.class, message = "Invalid category")
    private String category;

    @NotNull
    @Positive
    private Double minimumWeight;

    @NotBlank
    @EnumValue(enumClass = Difficulty.class, message = "Invalid difficulty")
    private String difficulty;

    @NotNull
    @Min(value = 0, message = "Points must be at least 0")
    private Integer points;

}

