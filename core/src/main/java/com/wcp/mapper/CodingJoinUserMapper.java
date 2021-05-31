package com.wcp.mapper;

import com.wcp.coding.join.CodingJoinUser;
import com.wcp.coding.join.CodingJoinUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CodingJoinUserMapper extends GenericMapper<CodingJoinUserDto, CodingJoinUser>{

    CodingJoinUserMapper INSTANCE = Mappers.getMapper(CodingJoinUserMapper.class);
}
