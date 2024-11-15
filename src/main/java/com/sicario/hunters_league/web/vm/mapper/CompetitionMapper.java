package com.sicario.hunters_league.web.vm.mapper;

import com.sicario.hunters_league.domain.Competition;
import com.sicario.hunters_league.web.vm.requestVM.CompetitionSaveVM;
import com.sicario.hunters_league.web.vm.requestVM.CompetitionUpdateVM;
import com.sicario.hunters_league.web.vm.responseVM.CompetitionVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    CompetitionMapper Instance = Mappers.getMapper(CompetitionMapper.class);

    CompetitionVM competitionToCompetitionVM(Competition competition);

    Competition competitionVMToCompetition(CompetitionVM competitionVM);

    CompetitionSaveVM competitionToCompetitionSaveVM(Competition competition);

    Competition competitionSaveVMToCompetition(CompetitionSaveVM competitionSaveVM);

    CompetitionUpdateVM competitionToCompetitionUpdateVM(Competition competition);

    Competition competitionUpdateVMToCompetition(CompetitionUpdateVM competitionUpdateVM);
}
