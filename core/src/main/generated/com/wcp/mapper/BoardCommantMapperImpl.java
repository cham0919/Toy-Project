package com.wcp.mapper;

import com.wcp.commant.BoardCommant;
import com.wcp.commant.BoardCommantDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-02T00:41:50+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class BoardCommantMapperImpl extends BoardCommantMapper {

    @Override
    public BoardCommantDto toDto(BoardCommant e) {
        disconnectProxy( e );

        if ( e == null ) {
            return null;
        }

        BoardCommantDto boardCommantDto = new BoardCommantDto();

        return boardCommantDto;
    }

    @Override
    public BoardCommant toEntity(BoardCommantDto d) {
        if ( d == null ) {
            return null;
        }

        BoardCommant boardCommant = new BoardCommant();

        return boardCommant;
    }

    @Override
    public void updateFromDto(BoardCommantDto dto, BoardCommant entity) {
        if ( dto == null ) {
            return;
        }
    }
}
