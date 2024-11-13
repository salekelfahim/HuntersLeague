package com.sicario.hunters_league.web.vm.requestVM;

import com.sicario.hunters_league.domain.Enum.SpeciesType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class CompetitionSaveVM {

    @NotBlank(message = "Location cannot be blank.")
    private String location;

    @Future(message = "Date must be in the future.")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Species type cannot be null.")
    private SpeciesType speciesType;

    @NotNull(message = "Minimum participants cannot be null.")
    @Min(value = 1, message = "Minimum participants must be at least 1.")
    private Integer minParticipants;

    @NotNull(message = "Maximum participants cannot be null.")
    @Min(value = 1, message = "Maximum participants must be at least 1.")
    private Integer maxParticipants;

    @NotNull(message = "Open registration status cannot be null.")
    private Boolean openRegistration;
}
