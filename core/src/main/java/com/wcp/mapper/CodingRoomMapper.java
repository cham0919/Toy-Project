package com.wcp.coding.board;

import com.wcp.common.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodingRoomMapper extends GenericMapper<CodingRoomDto, CodingRoom> {

}

