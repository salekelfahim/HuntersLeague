package com.sicario.hunters_league.web.vm.mapper;

import com.sicario.hunters_league.domain.User;
import com.sicario.hunters_league.service.dto.UserDTO;
import com.sicario.hunters_league.web.vm.AuthResponseVm;
import com.sicario.hunters_league.web.vm.RegisterVm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    UserDTO toDTO(RegisterVm registerVm);
    User toEntity(RegisterVm registerVm);
    AuthResponseVm toVM(User user);
}
