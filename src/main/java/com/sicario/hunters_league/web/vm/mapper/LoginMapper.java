package com.sicario.hunters_league.web.vm.mapper;

import com.sicario.hunters_league.domain.User;
import com.sicario.hunters_league.web.vm.AuthResponseVm;
import com.sicario.hunters_league.web.vm.LoginVm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    User toEntity(LoginVm loginVm);
    AuthResponseVm toVM(User user);
}
