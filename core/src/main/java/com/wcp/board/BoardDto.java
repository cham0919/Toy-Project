package com.wcp.board;

import com.wcp.category.BoardCategoryDto;
import com.wcp.commant.BoardCommantDto;
import com.wcp.user.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private String key;
    private UserDto user;
    private BoardCategoryDto boardCategory;
    private String title;
    private String content;
    private LocalDateTime uploadAt;
    private LocalDateTime updatedAt;
    private Long hit;
    private Long likeCnt;
    private Long disLikeCnt;
    private String delete;
    private List<BoardCommantDto> boardCommants = new ArrayList<>();
}
