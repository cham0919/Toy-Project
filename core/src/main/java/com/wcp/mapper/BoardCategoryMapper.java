package com.wcp.mapper;

import com.wcp.category.BoardCategory;
import com.wcp.category.BoardCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardCategoryMapper extends GenericMapper<BoardCategoryDto, BoardCategory>{

    BoardCategoryMapper INSTANCE = Mappers.getMapper(BoardCategoryMapper.class);
}
