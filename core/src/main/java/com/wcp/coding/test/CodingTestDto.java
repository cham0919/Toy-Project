package com.wcp.coding.test;

import com.querydsl.core.annotations.QueryProjection;
import com.wcp.coding.room.CodingRoomDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class CodingTestDto {

    private String key;
    private String userKey;
    private String postId;
    private String title;
    private String content;
    private String language;
    private String auth;
    private LocalDateTime submitAt;
    private boolean isPass;
    private CodingRoomDto codingRoomDto;

    public CodingTestDto() {}

    @QueryProjection

    public CodingTestDto(Long key, Long userKey, Long postId, String title, String language, String auth, Long isPass) {
        this.key = String.valueOf(key);
        this.userKey = String.valueOf(userKey);
        this.postId = String.valueOf(postId);
        this.title = title;
        this.language = language;
        this.auth = auth;
        this.isPass = isPass > 0 ? true : false;
    }
}
