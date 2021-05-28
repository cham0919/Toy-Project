package com.wcp.coding.join;

import com.wcp.coding.room.CodingRoomDto;
import com.wcp.user.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CodingJoinUserDto {

    private String key;
    private CodingRoomDto codingRoom;
    private UserDto user;
    private String status;
    private String role;
    private LocalDateTime joinAt;
}
