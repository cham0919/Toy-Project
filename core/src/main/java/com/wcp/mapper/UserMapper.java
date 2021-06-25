package com.wcp.mapper;

import com.wcp.coding.join.CodingJoinUser;
import com.wcp.user.User;
import com.wcp.user.UserDto;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements GenericMapper<UserDto, User>{

    public static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @BeforeMapping
    public void disconnectProxy(User user) {
        if (!Hibernate.isInitialized(user.getBoardCommants())) {
            user.setBoardCommants(new ArrayList<>());
        }
        if (!Hibernate.isInitialized(user.getBoardLikes())) {
            user.setBoardLikes(new ArrayList<>());
        }
        if (!Hibernate.isInitialized(user.getBoards())) {
            user.setBoards(new ArrayList<>());
        }
        if (!Hibernate.isInitialized(user.getCodingRooms())) {
            user.setCodingRooms(new ArrayList<>());
        }
        if (!Hibernate.isInitialized(user.getCodingTests())) {
            user.setCodingTests(new ArrayList<>());
        }
        if (!Hibernate.isInitialized(user.getSubmitHistories())) {
            user.setSubmitHistories(new ArrayList<>());
        }
    }
}
