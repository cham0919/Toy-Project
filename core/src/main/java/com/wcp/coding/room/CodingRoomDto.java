package com.wcp.coding.room;

import com.wcp.coding.test.CodingTestDto;
import com.wcp.coding.join.CodingJoinUserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CodingRoomDto {

    private String key;
    private String title;
    private String intro;
    private String password;
    private Long maxUser;
    private String ramdomKey;
    private LocalDateTime createdAt;
    private List<CodingJoinUserDto> codingJoinUsers = new ArrayList<>();
    private List<CodingTestDto> codingTests = new ArrayList<>();

}
