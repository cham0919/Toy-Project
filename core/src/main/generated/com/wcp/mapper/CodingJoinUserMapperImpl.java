package com.wcp.mapper;

import com.wcp.board.Board;
import com.wcp.coding.join.CodingJoinUser;
import com.wcp.coding.join.CodingJoinUserDto;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomDto;
import com.wcp.coding.submit.SubmitHistory;
import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestDto;
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
public class CodingJoinUserMapperImpl implements CodingJoinUserMapper {

    @Override
    public CodingJoinUserDto toDto(CodingJoinUser e) {
        if ( e == null ) {
            return null;
        }

        CodingJoinUserDto codingJoinUserDto = new CodingJoinUserDto();

        if ( e.getKey() != null ) {
            codingJoinUserDto.setKey( String.valueOf( e.getKey() ) );
        }
        codingJoinUserDto.setCodingRoom( codingRoomToCodingRoomDto( e.getCodingRoom() ) );
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
        codingJoinUser.setCodingRoom( codingRoomDtoToCodingRoom( d.getCodingRoom() ) );
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
        if ( dto.getCodingRoom() != null ) {
            if ( entity.getCodingRoom() == null ) {
                entity.setCodingRoom( new CodingRoom() );
            }
            codingRoomDtoToCodingRoom1( dto.getCodingRoom(), entity.getCodingRoom() );
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

    protected List<CodingJoinUserDto> codingJoinUserListToCodingJoinUserDtoList(List<CodingJoinUser> list) {
        if ( list == null ) {
            return null;
        }

        List<CodingJoinUserDto> list1 = new ArrayList<CodingJoinUserDto>( list.size() );
        for ( CodingJoinUser codingJoinUser : list ) {
            list1.add( toDto( codingJoinUser ) );
        }

        return list1;
    }

    protected CodingTestDto codingTestToCodingTestDto(CodingTest codingTest) {
        if ( codingTest == null ) {
            return null;
        }

        CodingTestDto codingTestDto = new CodingTestDto();

        if ( codingTest.getKey() != null ) {
            codingTestDto.setKey( String.valueOf( codingTest.getKey() ) );
        }
        codingTestDto.setTitle( codingTest.getTitle() );
        codingTestDto.setContent( codingTest.getContent() );
        codingTestDto.setLanguage( codingTest.getLanguage() );
        codingTestDto.setAuth( codingTest.getAuth() );
        codingTestDto.setSubmitAt( codingTest.getSubmitAt() );

        return codingTestDto;
    }

    protected List<CodingTestDto> codingTestListToCodingTestDtoList(List<CodingTest> list) {
        if ( list == null ) {
            return null;
        }

        List<CodingTestDto> list1 = new ArrayList<CodingTestDto>( list.size() );
        for ( CodingTest codingTest : list ) {
            list1.add( codingTestToCodingTestDto( codingTest ) );
        }

        return list1;
    }

    protected CodingRoomDto codingRoomToCodingRoomDto(CodingRoom codingRoom) {
        if ( codingRoom == null ) {
            return null;
        }

        CodingRoomDto codingRoomDto = new CodingRoomDto();

        if ( codingRoom.getKey() != null ) {
            codingRoomDto.setKey( String.valueOf( codingRoom.getKey() ) );
        }
        codingRoomDto.setTitle( codingRoom.getTitle() );
        codingRoomDto.setIntro( codingRoom.getIntro() );
        codingRoomDto.setSecret( codingRoom.isSecret() );
        codingRoomDto.setPassword( codingRoom.getPassword() );
        codingRoomDto.setMaxUser( codingRoom.getMaxUser() );
        codingRoomDto.setRamdomKey( codingRoom.getRamdomKey() );
        codingRoomDto.setCreatedAt( codingRoom.getCreatedAt() );
        codingRoomDto.setCodingJoinUsers( codingJoinUserListToCodingJoinUserDtoList( codingRoom.getCodingJoinUsers() ) );
        codingRoomDto.setCodingTests( codingTestListToCodingTestDtoList( codingRoom.getCodingTests() ) );

        return codingRoomDto;
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

    protected List<CodingJoinUser> codingJoinUserDtoListToCodingJoinUserList(List<CodingJoinUserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CodingJoinUser> list1 = new ArrayList<CodingJoinUser>( list.size() );
        for ( CodingJoinUserDto codingJoinUserDto : list ) {
            list1.add( toEntity( codingJoinUserDto ) );
        }

        return list1;
    }

    protected CodingTest codingTestDtoToCodingTest(CodingTestDto codingTestDto) {
        if ( codingTestDto == null ) {
            return null;
        }

        CodingTest codingTest = new CodingTest();

        if ( codingTestDto.getKey() != null ) {
            codingTest.setKey( Long.parseLong( codingTestDto.getKey() ) );
        }
        codingTest.setTitle( codingTestDto.getTitle() );
        codingTest.setContent( codingTestDto.getContent() );
        codingTest.setLanguage( codingTestDto.getLanguage() );
        codingTest.setAuth( codingTestDto.getAuth() );
        codingTest.setSubmitAt( codingTestDto.getSubmitAt() );

        return codingTest;
    }

    protected List<CodingTest> codingTestDtoListToCodingTestList(List<CodingTestDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CodingTest> list1 = new ArrayList<CodingTest>( list.size() );
        for ( CodingTestDto codingTestDto : list ) {
            list1.add( codingTestDtoToCodingTest( codingTestDto ) );
        }

        return list1;
    }

    protected CodingRoom codingRoomDtoToCodingRoom(CodingRoomDto codingRoomDto) {
        if ( codingRoomDto == null ) {
            return null;
        }

        CodingRoom codingRoom = new CodingRoom();

        if ( codingRoomDto.getKey() != null ) {
            codingRoom.setKey( Long.parseLong( codingRoomDto.getKey() ) );
        }
        codingRoom.setTitle( codingRoomDto.getTitle() );
        codingRoom.setIntro( codingRoomDto.getIntro() );
        codingRoom.setSecret( codingRoomDto.isSecret() );
        codingRoom.setPassword( codingRoomDto.getPassword() );
        codingRoom.setMaxUser( codingRoomDto.getMaxUser() );
        codingRoom.setRamdomKey( codingRoomDto.getRamdomKey() );
        codingRoom.setCreatedAt( codingRoomDto.getCreatedAt() );
        codingRoom.setCodingJoinUsers( codingJoinUserDtoListToCodingJoinUserList( codingRoomDto.getCodingJoinUsers() ) );
        codingRoom.setCodingTests( codingTestDtoListToCodingTestList( codingRoomDto.getCodingTests() ) );

        return codingRoom;
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

    protected void codingRoomDtoToCodingRoom1(CodingRoomDto codingRoomDto, CodingRoom mappingTarget) {
        if ( codingRoomDto == null ) {
            return;
        }

        if ( codingRoomDto.getKey() != null ) {
            mappingTarget.setKey( Long.parseLong( codingRoomDto.getKey() ) );
        }
        if ( codingRoomDto.getTitle() != null ) {
            mappingTarget.setTitle( codingRoomDto.getTitle() );
        }
        if ( codingRoomDto.getIntro() != null ) {
            mappingTarget.setIntro( codingRoomDto.getIntro() );
        }
        mappingTarget.setSecret( codingRoomDto.isSecret() );
        if ( codingRoomDto.getPassword() != null ) {
            mappingTarget.setPassword( codingRoomDto.getPassword() );
        }
        if ( codingRoomDto.getMaxUser() != null ) {
            mappingTarget.setMaxUser( codingRoomDto.getMaxUser() );
        }
        if ( codingRoomDto.getRamdomKey() != null ) {
            mappingTarget.setRamdomKey( codingRoomDto.getRamdomKey() );
        }
        if ( codingRoomDto.getCreatedAt() != null ) {
            mappingTarget.setCreatedAt( codingRoomDto.getCreatedAt() );
        }
        if ( mappingTarget.getCodingJoinUsers() != null ) {
            List<CodingJoinUser> list = codingJoinUserDtoListToCodingJoinUserList( codingRoomDto.getCodingJoinUsers() );
            if ( list != null ) {
                mappingTarget.getCodingJoinUsers().clear();
                mappingTarget.getCodingJoinUsers().addAll( list );
            }
        }
        else {
            List<CodingJoinUser> list = codingJoinUserDtoListToCodingJoinUserList( codingRoomDto.getCodingJoinUsers() );
            if ( list != null ) {
                mappingTarget.setCodingJoinUsers( list );
            }
        }
        if ( mappingTarget.getCodingTests() != null ) {
            List<CodingTest> list1 = codingTestDtoListToCodingTestList( codingRoomDto.getCodingTests() );
            if ( list1 != null ) {
                mappingTarget.getCodingTests().clear();
                mappingTarget.getCodingTests().addAll( list1 );
            }
        }
        else {
            List<CodingTest> list1 = codingTestDtoListToCodingTestList( codingRoomDto.getCodingTests() );
            if ( list1 != null ) {
                mappingTarget.setCodingTests( list1 );
            }
        }
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
