package com.wcp.coding.room;

import com.querydsl.core.annotations.QueryProjection;
import com.wcp.coding.test.CodingTestDto;
import com.wcp.coding.join.CodingJoinUserDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class CodingRoomDto {

    private String key;
    private String title;
    private String intro;
    private boolean secret;
    private String password;
    private Long maxUser;
    private String ramdomKey;
    private LocalDateTime createdAt;
    private Long joinUsersCount;
    private Long codingTestCount;


    public CodingRoomDto() {
    }

    @QueryProjection
    public CodingRoomDto(Long key, String title, Long maxUser, Long joinUsersCount, Long codingTestCount) {
        this.key = String.valueOf(key);
        this.title = title;
        this.maxUser = maxUser;
        this.joinUsersCount = joinUsersCount;
        this.codingTestCount = codingTestCount;
    }
}
