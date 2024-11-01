package com.sicario.hunters_league.web.vm.mapper;

import com.sicario.hunters_league.domain.User;
import com.sicario.hunters_league.service.dto.UserDTO;
import com.sicario.hunters_league.web.vm.UserSearchResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SearchResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "nationality", source = "nationality")
    @Mapping(target = "joinDate", source = "joinDate")
    @Mapping(target = "licenseExpirationDate", source = "licenseExpirationDate")
    UserSearchResponseVm toVM(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "nationality", source = "nationality")
    @Mapping(target = "joinDate", source = "joinDate")
    @Mapping(target = "licenseExpirationDate", source = "licenseExpirationDate")
    UserSearchResponseVm fromDTO(UserDTO userDTO);
}