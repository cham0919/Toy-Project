package com.wcp.mapper;

import com.wcp.category.BoardCategory;
import com.wcp.category.BoardCategoryDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-26T23:02:46+0900",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.2.jar, environment: Java 11.0.9 (Oracle Corporation)"
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
