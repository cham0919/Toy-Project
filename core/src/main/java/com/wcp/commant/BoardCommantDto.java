package com.wcp.commant;

import com.wcp.WCPTable;
import com.wcp.board.Board;
import com.wcp.board.BoardDto;
import com.wcp.user.User;
import com.wcp.user.UserDto;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

public class BoardCommantDto {

    private String key;
    private UserDto user;
    private BoardDto board;
    private String content;
    private LocalDateTime uploadAt;
    private LocalDateTime updatedAt;
    private String likeCnt;
    private String disLikeCnt;
    private String delete;
}
