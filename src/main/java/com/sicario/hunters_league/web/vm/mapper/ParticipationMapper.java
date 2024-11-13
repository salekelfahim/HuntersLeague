package com.sicario.hunters_league.web.vm.mapper;

import com.sicario.hunters_league.domain.Participation;
import com.sicario.hunters_league.web.vm.responseVM.ParticipationVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {UserMapper.class, CompetitionMapper.class})
public interface ParticipationMapper {

    @Mapping(source = "user", target = "user")
    @Mapping(source = "competition", target = "competition")
    ParticipationVM participationToParticipationVM(Participation participation);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "competition", target = "competition")
    Participation participationVMToParticipation(ParticipationVM participationVM);
}
