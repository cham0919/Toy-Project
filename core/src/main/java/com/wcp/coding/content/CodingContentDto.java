package com.wcp.coding.content;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class CodingContentDto{

    private String key;
    private String postId;
    private String title;
    private String content;
    private String language;
    private String auth;
    private LocalDateTime submitDatetime;


}
