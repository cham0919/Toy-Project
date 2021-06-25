package com.wcp.mapper;

import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomDto;
import org.hibernate.Hibernate;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;


@Mapper(componentModel = "spring")
public abstract class CodingRoomMapper implements GenericMapper<CodingRoomDto, CodingRoom> {

    public static final CodingRoomMapper CODING_ROOM_MAPPER = Mappers.getMapper(CodingRoomMapper.class);

    @BeforeMapping
    public void disconnectProxy(CodingRoom codingRoom) {
        if (!Hibernate.isInitialized(codingRoom.getCodingJoinUsers())) {
            codingRoom.setCodingJoinUsers(new ArrayList<>());
        }
        if (!Hibernate.isInitialized(codingRoom.getCodingTests())) {
            codingRoom.setCodingTests(new ArrayList<>());
        }
        if (!Hibernate.isInitialized(codingRoom.getUser())) {
            codingRoom.setUser(null);
        }
    }

//    @Override
//    @Mappings(
//            @Mapping(source = "", target = "language"),
//    )
//    public CodingRoomDto toDto(CodingRoom codingRoom) {
//        return null;
//    }
}

