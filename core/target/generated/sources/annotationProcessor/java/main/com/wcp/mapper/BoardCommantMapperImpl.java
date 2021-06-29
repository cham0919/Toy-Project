package com.wcp.mapper;

import com.wcp.commant.BoardCommant;
import com.wcp.commant.BoardCommantDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-26T23:02:46+0900",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.2.jar, environment: Java 11.0.9 (Oracle Corporation)"
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
