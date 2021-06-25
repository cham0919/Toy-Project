package com.wcp.mapper;

import com.wcp.commant.BoardCommant;
import com.wcp.like.BoardLike;
import com.wcp.like.BoardLikeDto;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class BoardLikeMapper implements GenericMapper<BoardLikeDto, BoardLike>{

    public final static BoardLikeMapper BOARD_LIKE_MAPPER = Mappers.getMapper(BoardLikeMapper.class);

    @BeforeMapping
    public void disconnectProxy(BoardLike boardLike) {
        if (!Hibernate.isInitialized(boardLike.getUser())) {
            boardLike.setUser(null);
        }
    }
}
