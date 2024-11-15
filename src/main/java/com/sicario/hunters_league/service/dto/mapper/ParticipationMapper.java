package com.sicario.hunters_league.service.dto.mapper;

import com.sicario.hunters_league.domain.Participation;
import com.sicario.hunters_league.web.vm.responseVM.ParticipationVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationMapper INSTANCE = Mappers.getMapper(ParticipationMapper.class);

    ParticipationVM participationToParticipationVM(Participation participation);
}
