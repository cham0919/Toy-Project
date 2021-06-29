package com.wcp.mapper;

import com.wcp.board.Board;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.submit.SubmitHistory;
import com.wcp.coding.submit.SubmitHistoryDto;
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
    date = "2021-06-26T22:49:40+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class SubmitHistoryMapperImpl extends SubmitHistoryMapper {

    @Override
    public void updateFromDto(SubmitHistoryDto dto, SubmitHistory entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getKey() != null ) {
            entity.setKey( Long.parseLong( dto.getKey() ) );
        }
        if ( dto.getCodingTest() != null ) {
            if ( entity.getCodingTest() == null ) {
                entity.setCodingTest( new CodingTest() );
            }
            codingTestDtoToCodingTest( dto.getCodingTest(), entity.getCodingTest() );
        }
        if ( dto.getUser() != null ) {
            if ( entity.getUser() == null ) {
                entity.setUser( new User() );
            }
            userDtoToUser( dto.getUser(), entity.getUser() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( dto.getSubmitAt() != null ) {
            entity.setSubmitAt( dto.getSubmitAt() );
        }
        if ( dto.getRunTime() != null ) {
            entity.setRunTime( dto.getRunTime() );
        }
    }

    @Override
    public SubmitHistoryDto toDto(SubmitHistory submitHistory) {
        disconnectProxy( submitHistory );

        if ( submitHistory == null ) {
            return null;
        }

        SubmitHistoryDto submitHistoryDto = new SubmitHistoryDto();

        submitHistoryDto.setLanguage_id( submitHistory.getLanguage() );
        submitHistoryDto.setSource_code( submitHistory.getCode() );
        if ( submitHistory.getKey() != null ) {
            submitHistoryDto.setKey( String.valueOf( submitHistory.getKey() ) );
        }
        submitHistoryDto.setCodingTest( codingTestToCodingTestDto( submitHistory.getCodingTest() ) );
        submitHistoryDto.setUser( userToUserDto( submitHistory.getUser() ) );
        submitHistoryDto.setStatus( submitHistory.getStatus() );
        submitHistoryDto.setSubmitAt( submitHistory.getSubmitAt() );
        submitHistoryDto.setRunTime( submitHistory.getRunTime() );

        return submitHistoryDto;
    }

    @Override
    public SubmitHistory toEntity(SubmitHistoryDto submitHistoryDto) {
        if ( submitHistoryDto == null ) {
            return null;
        }

        SubmitHistory submitHistory = new SubmitHistory();

        submitHistory.setLanguage( submitHistoryDto.getLanguage_id() );
        submitHistory.setCode( submitHistoryDto.getSource_code() );
        if ( submitHistoryDto.getKey() != null ) {
            submitHistory.setKey( Long.parseLong( submitHistoryDto.getKey() ) );
        }
        submitHistory.setCodingTest( codingTestDtoToCodingTest1( submitHistoryDto.getCodingTest() ) );
        submitHistory.setUser( userDtoToUser1( submitHistoryDto.getUser() ) );
        submitHistory.setStatus( submitHistoryDto.getStatus() );
        submitHistory.setSubmitAt( submitHistoryDto.getSubmitAt() );
        submitHistory.setRunTime( submitHistoryDto.getRunTime() );

        return submitHistory;
    }

    protected void codingTestDtoToCodingTest(CodingTestDto codingTestDto, CodingTest mappingTarget) {
        if ( codingTestDto == null ) {
            return;
        }

        if ( codingTestDto.getKey() != null ) {
            mappingTarget.setKey( Long.parseLong( codingTestDto.getKey() ) );
        }
        if ( codingTestDto.getTitle() != null ) {
            mappingTarget.setTitle( codingTestDto.getTitle() );
        }
        if ( codingTestDto.getContent() != null ) {
            mappingTarget.setContent( codingTestDto.getContent() );
        }
        if ( codingTestDto.getLanguage() != null ) {
            mappingTarget.setLanguage( codingTestDto.getLanguage() );
        }
        if ( codingTestDto.getAuth() != null ) {
            mappingTarget.setAuth( codingTestDto.getAuth() );
        }
        if ( codingTestDto.getSubmitAt() != null ) {
            mappingTarget.setSubmitAt( codingTestDto.getSubmitAt() );
        }
    }

    protected void userDtoToUser(UserDto userDto, User mappingTarget) {
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

    protected CodingTest codingTestDtoToCodingTest1(CodingTestDto codingTestDto) {
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

    protected User userDtoToUser1(UserDto userDto) {
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
}
