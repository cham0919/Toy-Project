package com.wcp.mapper;

import com.wcp.board.Board;
import com.wcp.coding.join.CodingJoinUser;
import com.wcp.coding.join.CodingJoinUserDto;
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
    date = "2021-06-26T23:02:46+0900",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.2.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class CodingJoinUserMapperImpl extends CodingJoinUserMapper {

    @Override
    public CodingJoinUserDto toDto(CodingJoinUser e) {
        disconnectProxy( e );

        if ( e == null ) {
            return null;
        }

        CodingJoinUserDto codingJoinUserDto = new CodingJoinUserDto();

        if ( e.getKey() != null ) {
            codingJoinUserDto.setKey( String.valueOf( e.getKey() ) );
        }
        codingJoinUserDto.setUser( userToUserDto( e.getUser() ) );
        codingJoinUserDto.setStatus( e.getStatus() );
        codingJoinUserDto.setRole( e.getRole() );
        codingJoinUserDto.setJoinAt( e.getJoinAt() );

        return codingJoinUserDto;
    }

    @Override
    public CodingJoinUser toEntity(CodingJoinUserDto d) {
        if ( d == null ) {
            return null;
        }

        CodingJoinUser codingJoinUser = new CodingJoinUser();

        if ( d.getKey() != null ) {
            codingJoinUser.setKey( Long.parseLong( d.getKey() ) );
        }
        codingJoinUser.setUser( userDtoToUser( d.getUser() ) );
        codingJoinUser.setStatus( d.getStatus() );
        codingJoinUser.setRole( d.getRole() );
        codingJoinUser.setJoinAt( d.getJoinAt() );

        return codingJoinUser;
    }

    @Override
    public void updateFromDto(CodingJoinUserDto dto, CodingJoinUser entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getKey() != null ) {
            entity.setKey( Long.parseLong( dto.getKey() ) );
        }
        if ( dto.getUser() != null ) {
            if ( entity.getUser() == null ) {
                entity.setUser( new User() );
            }
            userDtoToUser1( dto.getUser(), entity.getUser() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( dto.getRole() != null ) {
            entity.setRole( dto.getRole() );
        }
        if ( dto.getJoinAt() != null ) {
            entity.setJoinAt( dto.getJoinAt() );
        }
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( user.getKey() != null ) {
            userDto.setKey( String.valueOf( user.getKey() ) );
        }
        userDto.setId( user.getId() );
        userDto.setPassword( user.getPassword() );
        userDto.setName( user.getName() );
        userDto.setNickname( user.getNickname() );
        userDto.setPhone( user.getPhone() );
        userDto.setRegisterAt( user.getRegisterAt() );
        userDto.setRole( user.getRole() );
        userDto.setStatus( user.getStatus() );
        List<Board> list = user.getBoards();
        if ( list != null ) {
            userDto.setBoards( new ArrayList<Board>( list ) );
        }
        List<BoardCommant> list1 = user.getBoardCommants();
        if ( list1 != null ) {
            userDto.setBoardCommants( new ArrayList<BoardCommant>( list1 ) );
        }
        List<BoardLike> list2 = user.getBoardLikes();
        if ( list2 != null ) {
            userDto.setBoardLikes( new ArrayList<BoardLike>( list2 ) );
        }
        List<CodingRoom> list3 = user.getCodingRooms();
        if ( list3 != null ) {
            userDto.setCodingRooms( new ArrayList<CodingRoom>( list3 ) );
        }
        List<SubmitHistory> list4 = user.getSubmitHistories();
        if ( list4 != null ) {
            userDto.setSubmitHistories( new ArrayList<SubmitHistory>( list4 ) );
        }

        return userDto;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        if ( userDto.getKey() != null ) {
            user.setKey( Long.parseLong( userDto.getKey() ) );
        }
        user.setId( userDto.getId() );
        user.setPassword( userDto.getPassword() );
        user.setName( userDto.getName() );
        user.setNickname( userDto.getNickname() );
        user.setPhone( userDto.getPhone() );
        user.setRegisterAt( userDto.getRegisterAt() );
        user.setRole( userDto.getRole() );
        user.setStatus( userDto.getStatus() );
        List<Board> list = userDto.getBoards();
        if ( list != null ) {
            user.setBoards( new ArrayList<Board>( list ) );
        }
        List<BoardCommant> list1 = userDto.getBoardCommants();
        if ( list1 != null ) {
            user.setBoardCommants( new ArrayList<BoardCommant>( list1 ) );
        }
        List<BoardLike> list2 = userDto.getBoardLikes();
        if ( list2 != null ) {
            user.setBoardLikes( new ArrayList<BoardLike>( list2 ) );
        }
        List<CodingRoom> list3 = userDto.getCodingRooms();
        if ( list3 != null ) {
            user.setCodingRooms( new ArrayList<CodingRoom>( list3 ) );
        }
        List<SubmitHistory> list4 = userDto.getSubmitHistories();
        if ( list4 != null ) {
            user.setSubmitHistories( new ArrayList<SubmitHistory>( list4 ) );
        }

        return user;
    }

    protected void userDtoToUser1(UserDto userDto, User mappingTarget) {
        if ( userDto == null ) {
            return;
        }

        if ( userDto.getKey() != null ) {
            mappingTarget.setKey( Long.parseLong( userDto.getKey() ) );
        }
        if ( userDto.getId() != null ) {
            mappingTarget.setId( userDto.getId() );
        }
        if ( userDto.getPassword() != null ) {
            mappingTarget.setPassword( userDto.getPassword() );
        }
        if ( userDto.getName() != null ) {
            mappingTarget.setName( userDto.getName() );
        }
        if ( userDto.getNickname() != null ) {
            mappingTarget.setNickname( userDto.getNickname() );
        }
        if ( userDto.getPhone() != null ) {
            mappingTarget.setPhone( userDto.getPhone() );
        }
        if ( userDto.getRegisterAt() != null ) {
            mappingTarget.setRegisterAt( userDto.getRegisterAt() );
        }
        if ( userDto.getRole() != null ) {
            mappingTarget.setRole( userDto.getRole() );
        }
        if ( userDto.getStatus() != null ) {
            mappingTarget.setStatus( userDto.getStatus() );
        }
        if ( mappingTarget.getBoards() != null ) {
            List<Board> list = userDto.getBoards();
            if ( list != null ) {
                mappingTarget.getBoards().clear();
                mappingTarget.getBoards().addAll( list );
            }
        }
        else {
            List<Board> list = userDto.getBoards();
            if ( list != null ) {
                mappingTarget.setBoards( new ArrayList<Board>( list ) );
            }
        }
        if ( mappingTarget.getBoardCommants() != null ) {
            List<BoardCommant> list1 = userDto.getBoardCommants();
            if ( list1 != null ) {
                mappingTarget.getBoardCommants().clear();
                mappingTarget.getBoardCommants().addAll( list1 );
            }
        }
        else {
            List<BoardCommant> list1 = userDto.getBoardCommants();
            if ( list1 != null ) {
                mappingTarget.setBoardCommants( new ArrayList<BoardCommant>( list1 ) );
            }
        }
        if ( mappingTarget.getBoardLikes() != null ) {
            List<BoardLike> list2 = userDto.getBoardLikes();
            if ( list2 != null ) {
                mappingTarget.getBoardLikes().clear();
                mappingTarget.getBoardLikes().addAll( list2 );
            }
        }
        else {
            List<BoardLike> list2 = userDto.getBoardLikes();
            if ( list2 != null ) {
                mappingTarget.setBoardLikes( new ArrayList<BoardLike>( list2 ) );
            }
        }
        if ( mappingTarget.getCodingRooms() != null ) {
            List<CodingRoom> list3 = userDto.getCodingRooms();
            if ( list3 != null ) {
                mappingTarget.getCodingRooms().clear();
                mappingTarget.getCodingRooms().addAll( list3 );
            }
        }
        else {
            List<CodingRoom> list3 = userDto.getCodingRooms();
            if ( list3 != null ) {
                mappingTarget.setCodingRooms( new ArrayList<CodingRoom>( list3 ) );
            }
        }
        if ( mappingTarget.getSubmitHistories() != null ) {
            List<SubmitHistory> list4 = userDto.getSubmitHistories();
            if ( list4 != null ) {
                mappingTarget.getSubmitHistories().clear();
                mappingTarget.getSubmitHistories().addAll( list4 );
            }
        }
        else {
            List<SubmitHistory> list4 = userDto.getSubmitHistories();
            if ( list4 != null ) {
                mappingTarget.setSubmitHistories( new ArrayList<SubmitHistory>( list4 ) );
            }
        }
    }
}
