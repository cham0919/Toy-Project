package com.wcp.mapper;

import com.wcp.board.Board;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.submit.SubmitHistory;
import com.wcp.commant.BoardCommant;
import com.wcp.like.BoardLike;
import com.wcp.user.User;
import com.wcp.user.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-20T07:28:05+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User e) {
        if ( e == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( e.getKey() != null ) {
            userDto.setKey( String.valueOf( e.getKey() ) );
        }
        userDto.setId( e.getId() );
        userDto.setPassword( e.getPassword() );
        userDto.setName( e.getName() );
        userDto.setNickname( e.getNickname() );
        userDto.setPhone( e.getPhone() );
        userDto.setRegisterAt( e.getRegisterAt() );
        userDto.setRole( e.getRole() );
        userDto.setStatus( e.getStatus() );
        List<Board> list = e.getBoards();
        if ( list != null ) {
            userDto.setBoards( new ArrayList<Board>( list ) );
        }
        List<BoardCommant> list1 = e.getBoardCommants();
        if ( list1 != null ) {
            userDto.setBoardCommants( new ArrayList<BoardCommant>( list1 ) );
        }
        List<BoardLike> list2 = e.getBoardLikes();
        if ( list2 != null ) {
            userDto.setBoardLikes( new ArrayList<BoardLike>( list2 ) );
        }
        List<CodingRoom> list3 = e.getCodingRooms();
        if ( list3 != null ) {
            userDto.setCodingRooms( new ArrayList<CodingRoom>( list3 ) );
        }
        List<SubmitHistory> list4 = e.getSubmitHistories();
        if ( list4 != null ) {
            userDto.setSubmitHistories( new ArrayList<SubmitHistory>( list4 ) );
        }

        return userDto;
    }

    @Override
    public User toEntity(UserDto d) {
        if ( d == null ) {
            return null;
        }

        User user = new User();

        if ( d.getKey() != null ) {
            user.setKey( Long.parseLong( d.getKey() ) );
        }
        user.setId( d.getId() );
        user.setPassword( d.getPassword() );
        user.setName( d.getName() );
        user.setNickname( d.getNickname() );
        user.setPhone( d.getPhone() );
        user.setRegisterAt( d.getRegisterAt() );
        user.setRole( d.getRole() );
        user.setStatus( d.getStatus() );
        List<Board> list = d.getBoards();
        if ( list != null ) {
            user.setBoards( new ArrayList<Board>( list ) );
        }
        List<BoardCommant> list1 = d.getBoardCommants();
        if ( list1 != null ) {
            user.setBoardCommants( new ArrayList<BoardCommant>( list1 ) );
        }
        List<BoardLike> list2 = d.getBoardLikes();
        if ( list2 != null ) {
            user.setBoardLikes( new ArrayList<BoardLike>( list2 ) );
        }
        List<CodingRoom> list3 = d.getCodingRooms();
        if ( list3 != null ) {
            user.setCodingRooms( new ArrayList<CodingRoom>( list3 ) );
        }
        List<SubmitHistory> list4 = d.getSubmitHistories();
        if ( list4 != null ) {
            user.setSubmitHistories( new ArrayList<SubmitHistory>( list4 ) );
        }

        return user;
    }

    @Override
    public void updateFromDto(UserDto dto, User entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getKey() != null ) {
            entity.setKey( Long.parseLong( dto.getKey() ) );
        }
        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getNickname() != null ) {
            entity.setNickname( dto.getNickname() );
        }
        if ( dto.getPhone() != null ) {
            entity.setPhone( dto.getPhone() );
        }
        if ( dto.getRegisterAt() != null ) {
            entity.setRegisterAt( dto.getRegisterAt() );
        }
        if ( dto.getRole() != null ) {
            entity.setRole( dto.getRole() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( entity.getBoards() != null ) {
            List<Board> list = dto.getBoards();
            if ( list != null ) {
                entity.getBoards().clear();
                entity.getBoards().addAll( list );
            }
        }
        else {
            List<Board> list = dto.getBoards();
            if ( list != null ) {
                entity.setBoards( new ArrayList<Board>( list ) );
            }
        }
        if ( entity.getBoardCommants() != null ) {
            List<BoardCommant> list1 = dto.getBoardCommants();
            if ( list1 != null ) {
                entity.getBoardCommants().clear();
                entity.getBoardCommants().addAll( list1 );
            }
        }
        else {
            List<BoardCommant> list1 = dto.getBoardCommants();
            if ( list1 != null ) {
                entity.setBoardCommants( new ArrayList<BoardCommant>( list1 ) );
            }
        }
        if ( entity.getBoardLikes() != null ) {
            List<BoardLike> list2 = dto.getBoardLikes();
            if ( list2 != null ) {
                entity.getBoardLikes().clear();
                entity.getBoardLikes().addAll( list2 );
            }
        }
        else {
            List<BoardLike> list2 = dto.getBoardLikes();
            if ( list2 != null ) {
                entity.setBoardLikes( new ArrayList<BoardLike>( list2 ) );
            }
        }
        if ( entity.getCodingRooms() != null ) {
            List<CodingRoom> list3 = dto.getCodingRooms();
            if ( list3 != null ) {
                entity.getCodingRooms().clear();
                entity.getCodingRooms().addAll( list3 );
            }
        }
        else {
            List<CodingRoom> list3 = dto.getCodingRooms();
            if ( list3 != null ) {
                entity.setCodingRooms( new ArrayList<CodingRoom>( list3 ) );
            }
        }
        if ( entity.getSubmitHistories() != null ) {
            List<SubmitHistory> list4 = dto.getSubmitHistories();
            if ( list4 != null ) {
                entity.getSubmitHistories().clear();
                entity.getSubmitHistories().addAll( list4 );
            }
        }
        else {
            List<SubmitHistory> list4 = dto.getSubmitHistories();
            if ( list4 != null ) {
                entity.setSubmitHistories( new ArrayList<SubmitHistory>( list4 ) );
            }
        }
    }
}
