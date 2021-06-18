package com.wcp.mapper;

import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomDto;
import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestDto;
import com.wcp.user.User;
import com.wcp.user.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperTest {

    @Test
    public void codingRoomToDto_Success_Equals(){
        CodingRoom entity = new CodingRoom();

        CodingRoomDto dto = CodingRoomMapper.CODING_ROOM_MAPPER.toDto(entity);

        assertEquals(entity.getKey(), dto.getKey());
        assertEquals(entity.getPassword(), dto.getPassword());
        assertEquals(entity.getIntro(), dto.getIntro());
        assertEquals(entity.getMaxUser(), dto.getMaxUser());
        assertEquals(entity.getRamdomKey(), dto.getRamdomKey());
        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getCodingJoinUsers(), dto.getCodingJoinUsers());
        assertEquals(entity.getCodingTests(), dto.getCodingTests());
        assertEquals(entity.getCreatedAt(), dto.getCreatedAt());
    }

    @Test
    public void codingRoomToEntity_Success_Equals(){
        CodingRoomDto dto = new CodingRoomDto();

        CodingRoom entity = CodingRoomMapper.CODING_ROOM_MAPPER.toEntity(dto);

        assertEquals(entity.getKey(), dto.getKey());
        assertEquals(entity.getPassword(), dto.getPassword());
        assertEquals(entity.getIntro(), dto.getIntro());
        assertEquals(entity.getMaxUser(), dto.getMaxUser());
        assertEquals(entity.getRamdomKey(), dto.getRamdomKey());
        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getCodingJoinUsers(), dto.getCodingJoinUsers());
        assertEquals(entity.getCodingTests(), dto.getCodingTests());
        assertEquals(entity.getCreatedAt(), dto.getCreatedAt()); }

    @Test
    public void codingRoomUpdateFromDto_Success_Equals(){
        CodingTestDto dto = new CodingTestDto()
                .setContent("test");

        CodingTest entity = new CodingTest()
                .setKey(1L);

        CodingTestMapper.CODING_TEST_MAPPER.updateFromDto(dto, entity);

        assertEquals(entity.getKey(), 1L);
        assertEquals(entity.getContent(), dto.getContent());
    }

    @Test
    public void codingTestToDto_Success_Equals(){
        CodingTest entity = new CodingTest();

        CodingTestDto dto = CodingTestMapper.CODING_TEST_MAPPER.toDto(entity);

        assertEquals(entity.getKey(), dto.getKey());
        assertEquals(entity.getContent(), dto.getContent());
        assertEquals(entity.getAuth(), dto.getAuth());
        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getLanguage(), dto.getLanguage());
        assertEquals(entity.getSubmitAt(), dto.getSubmitAt());
    }

    @Test
    public void codingTestToEntity_Success_Equals(){
        CodingTestDto dto = new CodingTestDto();

        CodingTest entity = CodingTestMapper.CODING_TEST_MAPPER.toEntity(dto);

        assertEquals(entity.getKey(), dto.getKey());
        assertEquals(entity.getContent(), dto.getContent());
        assertEquals(entity.getAuth(), dto.getAuth());
        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getLanguage(), dto.getLanguage());
        assertEquals(entity.getSubmitAt(), dto.getSubmitAt());
    }

    @Test
    public void codingTestUpdateFromDto_Success_Equals(){
        CodingRoomDto dto = new CodingRoomDto()
                .setIntro("test");

        CodingRoom entity = new CodingRoom()
                .setKey(1L);

        CodingRoomMapper.CODING_ROOM_MAPPER.updateFromDto(dto, entity);

        assertEquals(entity.getKey(), 1L);
        assertEquals(entity.getIntro(), dto.getIntro());
    }

    @Test
    public void userToDto_Success_Equals(){
        User entity = new User();

        UserDto dto = UserMapper.USER_MAPPER.toDto(entity);

        assertEquals(entity.getKey(), dto.getKey());
        assertEquals(entity.getBoardCommants(), dto.getBoardCommants());
        assertEquals(entity.getBoardLikes(), dto.getBoardLikes());
        assertEquals(entity.getBoards(), dto.getBoards());
        assertEquals(entity.getCodingRooms(), dto.getCodingRooms());
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getNickname(), dto.getNickname());
        assertEquals(entity.getPassword(), dto.getPassword());
        assertEquals(entity.getPhone(), dto.getPhone());
        assertEquals(entity.getRegisterAt(), dto.getRegisterAt());
        assertEquals(entity.getRole(), dto.getRole());
        assertEquals(entity.getStatus(), dto.getStatus());
        assertEquals(entity.getSubmitHistories(), dto.getSubmitHistories());
    }

    @Test
    public void userToEntity_Success_Equals(){
        UserDto dto = new UserDto();

        User entity = UserMapper.USER_MAPPER.toEntity(dto);

        assertEquals(entity.getKey(), dto.getKey());
        assertEquals(entity.getBoardCommants(), dto.getBoardCommants());
        assertEquals(entity.getBoardLikes(), dto.getBoardLikes());
        assertEquals(entity.getBoards(), dto.getBoards());
        assertEquals(entity.getCodingRooms(), dto.getCodingRooms());
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getNickname(), dto.getNickname());
        assertEquals(entity.getPassword(), dto.getPassword());
        assertEquals(entity.getPhone(), dto.getPhone());
        assertEquals(entity.getRegisterAt(), dto.getRegisterAt());
        assertEquals(entity.getRole(), dto.getRole());
        assertEquals(entity.getStatus(), dto.getStatus());
        assertEquals(entity.getSubmitHistories(), dto.getSubmitHistories());
    }

    @Test
    public void userUpdateFromDto_Success_Equals(){
        UserDto dto = new UserDto()
                .setId("test");

        User entity = new User()
                .setKey(1L);

        UserMapper.USER_MAPPER.updateFromDto(dto, entity);

        assertEquals(entity.getKey(), 1L);
        assertEquals(entity.getId(), dto.getId());
    }
}
