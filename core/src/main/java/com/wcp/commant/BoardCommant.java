package com.wcp.commant;

import com.wcp.WCPTable.*;
import com.wcp.WCPTable.BoardCommantTable;
import com.wcp.board.Board;
import com.wcp.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = BoardCommantTable.TABLE_NAME)
public class BoardCommant {

    @Id
    @GeneratedValue
    @Column(name = BoardCommantTable.PK)
    private Long key;

    @ManyToOne
    @JoinColumn(name = UserTable.PK)
    private User user;

    @ManyToOne
    @JoinColumn(name = BoardTable.PK)
    private Board board;

    @Column(name = BoardCommantTable.CONTENT)
    private String content;

    @CreatedDate
    @Column(name = BoardCommantTable.UPLOAD_DATETIME)
    private LocalDateTime uploadDatetime;

    @CreatedDate
    @Column(name = BoardCommantTable.UPDATE_DATETIME)
    private LocalDateTime updateDatetime;

    @Column(name = BoardCommantTable.LIKE)
    private Long like;

    @Column(name = BoardCommantTable.DISLIKE)
    private Long disLike;

    @Column(name = BoardCommantTable.DELETE)
    private String delete;
}

