package com.wcp.mapper;

import com.wcp.user.User;
import com.wcp.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User>{

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
}
