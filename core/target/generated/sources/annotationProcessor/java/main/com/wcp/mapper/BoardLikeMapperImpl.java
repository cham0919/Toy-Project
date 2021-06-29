package com.wcp.mapper;

import com.wcp.like.BoardLike;
import com.wcp.like.BoardLikeDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-26T23:02:46+0900",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.2.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class BoardLikeMapperImpl extends BoardLikeMapper {

    @Override
    public BoardLikeDto toDto(BoardLike e) {
        disconnectProxy( e );

        if ( e == null ) {
            return null;
        }

        BoardLikeDto boardLikeDto = new BoardLikeDto();

        return boardLikeDto;
    }

    @Override
    public BoardLike toEntity(BoardLikeDto d) {
        if ( d == null ) {
            return null;
        }

        BoardLike boardLike = new BoardLike();

        return boardLike;
    }

    @Override
    public void updateFromDto(BoardLikeDto dto, BoardLike entity) {
        if ( dto == null ) {
            return;
        }
    }
}
