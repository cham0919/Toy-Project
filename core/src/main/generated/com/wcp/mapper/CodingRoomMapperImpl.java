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
    date = "2021-05-31T13:25:46+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class CodingRoomMapperImpl implements CodingRoomMapper {

    @Override
    public CodingRoomDto toDto(CodingRoom e) {
        if ( e == null ) {
            return null;
        }

        CodingRoomDto codingRoomDto = new CodingRoomDto();

        if ( e.getKey() != null ) {
            codingRoomDto.setKey( String.valueOf( e.getKey() ) );
        }
        codingRoomDto.setTitle( e.getTitle() );
        codingRoomDto.setIntro( e.getIntro() );
        codingRoomDto.setPassword( e.getPassword() );
        codingRoomDto.setMaxUser( e.getMaxUser() );
        codingRoomDto.setRamdomKey( e.getRamdomKey() );
        codingRoomDto.setCreatedAt( e.getCreatedAt() );
        codingRoomDto.setCodingJoinUsers( codingJoinUserListToCodingJoinUserDtoList( e.getCodingJoinUsers() ) );
        codingRoomDto.setCodingTests( codingTestListToCodingTestDtoList( e.getCodingTests() ) );

        return codingRoomDto;
    }

    @Override
    public CodingRoom toEntity(CodingRoomDto d) {
        if ( d == null ) {
            return null;
        }

        CodingRoom codingRoom = new CodingRoom();

        if ( d.getKey() != null ) {
            codingRoom.setKey( Long.parseLong( d.getKey() ) );
        }
        codingRoom.setTitle( d.getTitle() );
        codingRoom.setIntro( d.getIntro() );
        codingRoom.setPassword( d.getPassword() );
        codingRoom.setMaxUser( d.getMaxUser() );
        codingRoom.setRamdomKey( d.getRamdomKey() );
        codingRoom.setCreatedAt( d.getCreatedAt() );
        codingRoom.setCodingJoinUsers( codingJoinUserDtoListToCodingJoinUserList( d.getCodingJoinUsers() ) );
        codingRoom.setCodingTests( codingTestDtoListToCodingTestList( d.getCodingTests() ) );

        return codingRoom;
    }

    @Override
    public void updateFromDto(CodingRoomDto dto, CodingRoom entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getKey() != null ) {
            entity.setKey( Long.parseLong( dto.getKey() ) );
        }
        if ( dto.getTitle() != null ) {
            entity.setTitle( dto.getTitle() );
        }
        if ( dto.getIntro() != null ) {
            entity.setIntro( dto.getIntro() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getMaxUser() != null ) {
            entity.setMaxUser( dto.getMaxUser() );
        }
        if ( dto.getRamdomKey() != null ) {
            entity.setRamdomKey( dto.getRamdomKey() );
        }
        if ( dto.getCreatedAt() != null ) {
            entity.setCreatedAt( dto.getCreatedAt() );
        }
        if ( entity.getCodingJoinUsers() != null ) {
            List<CodingJoinUser> list = codingJoinUserDtoListToCodingJoinUserList( dto.getCodingJoinUsers() );
            if ( list != null ) {
                entity.getCodingJoinUsers().clear();
                entity.getCodingJoinUsers().addAll( list );
            }
        }
        else {
            List<CodingJoinUser> list = codingJoinUserDtoListToCodingJoinUserList( dto.getCodingJoinUsers() );
            if ( list != null ) {
                entity.setCodingJoinUsers( list );
            }
        }
        if ( entity.getCodingTests() != null ) {
            List<CodingTest> list1 = codingTestDtoListToCodingTestList( dto.getCodingTests() );
            if ( list1 != null ) {
                entity.getCodingTests().clear();
                entity.getCodingTests().addAll( list1 );
            }
        }
        else {
            List<CodingTest> list1 = codingTestDtoListToCodingTestList( dto.getCodingTests() );
            if ( list1 != null ) {
                entity.setCodingTests( list1 );
            }
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

    protected CodingJoinUserDto codingJoinUserToCodingJoinUserDto(CodingJoinUser codingJoinUser) {
        if ( codingJoinUser == null ) {
            return null;
        }

        CodingJoinUserDto codingJoinUserDto = new CodingJoinUserDto();

        if ( codingJoinUser.getKey() != null ) {
            codingJoinUserDto.setKey( String.valueOf( codingJoinUser.getKey() ) );
        }
        codingJoinUserDto.setCodingRoom( toDto( codingJoinUser.getCodingRoom() ) );
        codingJoinUserDto.setUser( userToUserDto( codingJoinUser.getUser() ) );
        codingJoinUserDto.setStatus( codingJoinUser.getStatus() );
        codingJoinUserDto.setRole( codingJoinUser.getRole() );
        codingJoinUserDto.setJoinAt( codingJoinUser.getJoinAt() );

        return codingJoinUserDto;
    }

    protected List<CodingJoinUserDto> codingJoinUserListToCodingJoinUserDtoList(List<CodingJoinUser> list) {
        if ( list == null ) {
            return null;
        }

        List<CodingJoinUserDto> list1 = new ArrayList<CodingJoinUserDto>( list.size() );
        for ( CodingJoinUser codingJoinUser : list ) {
            list1.add( codingJoinUserToCodingJoinUserDto( codingJoinUser ) );
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

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( userDto.getPassword() );
        if ( userDto.getKey() != null ) {
            user.setKey( Long.parseLong( userDto.getKey() ) );
        }
        user.setId( userDto.getId() );
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

    protected CodingJoinUser codingJoinUserDtoToCodingJoinUser(CodingJoinUserDto codingJoinUserDto) {
        if ( codingJoinUserDto == null ) {
            return null;
        }

        CodingJoinUser codingJoinUser = new CodingJoinUser();

        if ( codingJoinUserDto.getKey() != null ) {
            codingJoinUser.setKey( Long.parseLong( codingJoinUserDto.getKey() ) );
        }
        codingJoinUser.setCodingRoom( toEntity( codingJoinUserDto.getCodingRoom() ) );
        codingJoinUser.setUser( userDtoToUser( codingJoinUserDto.getUser() ) );
        codingJoinUser.setStatus( codingJoinUserDto.getStatus() );
        codingJoinUser.setRole( codingJoinUserDto.getRole() );
        codingJoinUser.setJoinAt( codingJoinUserDto.getJoinAt() );

        return codingJoinUser;
    }

    protected List<CodingJoinUser> codingJoinUserDtoListToCodingJoinUserList(List<CodingJoinUserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CodingJoinUser> list1 = new ArrayList<CodingJoinUser>( list.size() );
        for ( CodingJoinUserDto codingJoinUserDto : list ) {
            list1.add( codingJoinUserDtoToCodingJoinUser( codingJoinUserDto ) );
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
}
