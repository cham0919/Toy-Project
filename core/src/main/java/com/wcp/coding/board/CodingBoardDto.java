package com.wcp.coding.board;

import com.wcp.coding.content.CodingContent;
import com.wcp.coding.content.CodingContentDto;
import com.wcp.coding.join.CodingJoinUser;
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
public class CodingBoardDto {

    private String key;
    private String title;
    private String intro;
    private String password;
    private Long maxUser;
    private String ramdomKey;
    private LocalDateTime createDatetime;
    private List<CodingJoinUserDto> codingJoinUsers = new ArrayList<>();
    private List<CodingContentDto> codingContents = new ArrayList<>();

}
