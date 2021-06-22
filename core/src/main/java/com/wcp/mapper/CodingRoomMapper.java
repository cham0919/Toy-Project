package com.wcp.mapper;

import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CodingRoomMapper extends GenericMapper<CodingRoomDto, CodingRoom> {

    CodingRoomMapper CODING_ROOM_MAPPER = Mappers.getMapper(CodingRoomMapper.class);

}

