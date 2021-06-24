package com.wcp.mapper;

import com.wcp.category.BoardCategory;
import com.wcp.category.BoardCategoryDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-24T02:11:18+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class BoardCategoryMapperImpl implements BoardCategoryMapper {

    @Override
    public BoardCategoryDto toDto(BoardCategory e) {
        if ( e == null ) {
            return null;
        }

        BoardCategoryDto boardCategoryDto = new BoardCategoryDto();

        return boardCategoryDto;
    }

    @Override
    public BoardCategory toEntity(BoardCategoryDto d) {
        if ( d == null ) {
            return null;
        }

        BoardCategory boardCategory = new BoardCategory();

        return boardCategory;
    }

    @Override
    public void updateFromDto(BoardCategoryDto dto, BoardCategory entity) {
        if ( dto == null ) {
            return;
        }
    }
}
