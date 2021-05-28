package com.wcp.mapper;

import com.wcp.user.User;
import com.wcp.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User>{

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
