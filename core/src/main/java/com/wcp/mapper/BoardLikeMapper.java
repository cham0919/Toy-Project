package com.wcp.mapper;

import com.wcp.like.BoardLike;
import com.wcp.like.BoardLikeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardLikeMapper extends GenericMapper<BoardLikeDto, BoardLike>{

    BoardLikeMapper INSTANCE = Mappers.getMapper(BoardLikeMapper.class);
}
