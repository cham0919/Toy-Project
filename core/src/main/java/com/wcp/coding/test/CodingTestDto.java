package com.wcp.coding.test;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class CodingTestDto {

    private String key;
    private String postId;
    private String title;
    private String content;
    private String language;
    private String auth;
    private LocalDateTime submitAt;


}
