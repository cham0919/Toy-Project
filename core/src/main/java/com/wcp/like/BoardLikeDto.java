package com.wcp.like;

import com.wcp.user.UserDto;

import java.time.LocalDateTime;

public class BoardLikeDto {

    private String key;
    private UserDto user;
    private String targetId;
    private String targetType;
    private String type;
    private LocalDateTime likeAt;
}
