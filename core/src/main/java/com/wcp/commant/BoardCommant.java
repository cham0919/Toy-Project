package com.wcp.commant;

import com.wcp.WCPTable.*;
import com.wcp.WCPTable.BoardCommantTable;
import com.wcp.board.Board;
import com.wcp.user.User;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BoardCommantTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = BoardTable.PK)
    private Board board;

    @Column(name = BoardCommantTable.CONTENT)
    private String content;

    @CreatedDate
    @Column(name = BoardCommantTable.UPLOAD_AT)
    private LocalDateTime uploadAt;

    @CreatedDate
    @Column(name = BoardCommantTable.UPDATED_AT)
    private LocalDateTime updatedAt;

    @Column(name = BoardCommantTable.LIKE)
    private Long likeCnt;

    @Column(name = BoardCommantTable.DISLIKE)
    private Long disLikeCnt;

    @Column(name = BoardCommantTable.DELETE)
    private String delete;
}

