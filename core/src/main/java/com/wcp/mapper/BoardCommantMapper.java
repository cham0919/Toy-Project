package com.wcp.mapper;

import com.wcp.commant.BoardCommant;
import com.wcp.commant.BoardCommantDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BoardCommantMapper extends GenericMapper<BoardCommantDto, BoardCommant>{

    BoardCommantMapper BOARD_COMMANT_MAPPER = Mappers.getMapper(BoardCommantMapper.class);
}
