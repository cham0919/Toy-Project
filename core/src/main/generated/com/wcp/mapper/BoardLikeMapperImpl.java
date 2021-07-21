package com.wcp.mapper;

import com.wcp.like.BoardLike;
import com.wcp.like.BoardLikeDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-02T00:41:50+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
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
