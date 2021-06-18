package com.wcp.mapper;

import com.wcp.board.Board;
import com.wcp.board.BoardDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper extends GenericMapper<BoardDto, Board>{

    BoardMapper BOARD_MAPPER = Mappers.getMapper(BoardMapper.class);
}
