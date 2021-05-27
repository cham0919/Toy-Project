package com.wcp.user;

import com.wcp.board.Board;
import com.wcp.coding.board.CodingBoard;
import com.wcp.coding.submit.SubmitHistory;
import com.wcp.commant.BoardCommant;
import com.wcp.like.BoardLike;
import com.wcp.security.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserDto {

    private String key;
    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;
    private String phone;
    private LocalDateTime register_datetime;
    private Role role;
    private UserSataus status;
    private List<Board> boards = new ArrayList<>();
    private List<BoardCommant> boardCommants = new ArrayList<>();
    private List<BoardLike> boardLikes = new ArrayList<>();
    private List<CodingBoard> codingBoards = new ArrayList<>();
    private List<SubmitHistory> submitHistories = new ArrayList<>();
}
