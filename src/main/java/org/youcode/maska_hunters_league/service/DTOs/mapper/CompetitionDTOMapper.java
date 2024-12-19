package org.youcode.maska_hunters_league.service.DTOs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.service.DTOs.CompetitionDTO;

@Mapper(componentModel = "spring")
public interface CompetitionDTOMapper {
    CompetitionDTO toCompetitionDTO(Competition competition);
    Competition toCompetition (CompetitionDTO competitionDTO);
}
