package com.wcp.mapper;

import com.wcp.board.Board;
import com.wcp.board.BoardDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-24T02:11:18+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public BoardDto toDto(Board e) {
        if ( e == null ) {
            return null;
        }

        BoardDto boardDto = new BoardDto();

        return boardDto;
    }

    @Override
    public Board toEntity(BoardDto d) {
        if ( d == null ) {
            return null;
        }

        Board board = new Board();

        return board;
    }

    @Override
    public void updateFromDto(BoardDto dto, Board entity) {
        if ( dto == null ) {
            return;
        }
    }
}
