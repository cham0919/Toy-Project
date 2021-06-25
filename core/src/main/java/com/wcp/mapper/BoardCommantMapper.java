package com.wcp.mapper;

import com.wcp.coding.submit.SubmitHistory;
import com.wcp.commant.BoardCommant;
import com.wcp.commant.BoardCommantDto;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public abstract class BoardCommantMapper implements GenericMapper<BoardCommantDto, BoardCommant>{

    public static final BoardCommantMapper BOARD_COMMANT_MAPPER = Mappers.getMapper(BoardCommantMapper.class);

    @BeforeMapping
    public void disconnectProxy(BoardCommant boardCommant) {
        if (!Hibernate.isInitialized(boardCommant.getBoard())) {
            boardCommant.setBoard(null);
        }
        if (!Hibernate.isInitialized(boardCommant.getUser())) {
            boardCommant.setUser(null);
        }
    }
}
