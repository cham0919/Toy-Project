package com.wcp.mapper;

import com.wcp.board.Board;
import com.wcp.board.BoardDto;
import com.wcp.like.BoardLike;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class BoardMapper implements GenericMapper<BoardDto, Board>{

    public static final BoardMapper BOARD_MAPPER = Mappers.getMapper(BoardMapper.class);

    @BeforeMapping
    public void disconnectProxy(Board board) {
        if (!Hibernate.isInitialized(board.getUser())) {
            board.setUser(null);
        }
        if (!Hibernate.isInitialized(board.getBoardCategory())) {
            board.setBoardCategory(null);
        }
        if (!Hibernate.isInitialized(board.getBoardCommants())) {
            board.setBoardCommants(null);
        }
    }
}
