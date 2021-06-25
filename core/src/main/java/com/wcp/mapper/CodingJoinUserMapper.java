package com.wcp.mapper;

import com.wcp.coding.join.CodingJoinUser;
import com.wcp.coding.join.CodingJoinUserDto;
import com.wcp.coding.test.CodingTest;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class CodingJoinUserMapper implements GenericMapper<CodingJoinUserDto, CodingJoinUser>{

    public static final CodingJoinUserMapper CODING_JOIN_USER_MAPPER = Mappers.getMapper(CodingJoinUserMapper.class);

    @BeforeMapping
    public void disconnectProxy(CodingJoinUser codingJoinUser) {
        if (!Hibernate.isInitialized(codingJoinUser.getCodingRoom())) {
            codingJoinUser.setCodingRoom(null);
        }
        if (!Hibernate.isInitialized(codingJoinUser.getUser())) {
            codingJoinUser.setUser(null);
        }
    }
}
