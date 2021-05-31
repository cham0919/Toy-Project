package com.wcp.board;

import com.wcp.WCPTable.BoardTable;
import com.wcp.WCPTable.UserTable;
import com.wcp.WCPTable.BoardCategoryTable;
import com.wcp.commant.BoardCommant;
import com.wcp.user.User;
import com.wcp.category.BoardCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = BoardTable.TABLE_NAME)
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BoardTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = BoardCategoryTable.PK)
    private BoardCategory boardCategory;

    @Column(name = BoardTable.TITLE)
    private String title;

    @Column(name = BoardTable.CONTENT)
    private String content;

    @CreatedDate
    @Column(name = BoardTable.UPLOAD_AT)
    private LocalDateTime uploadAt;

    @CreatedDate
    @Column(name = BoardTable.UPDATED_AT)
    private LocalDateTime updatedAt;

    @Column(name = BoardTable.HIT)
    private Long hit;

    @Column(name = BoardTable.LIKE)
    private Long likeCnt;

    @Column(name = BoardTable.DISLIKE)
    private Long disLikeCnt;

    @Column(name = BoardTable.DELETE)
    private String delete;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardCommant> boardCommants = new ArrayList<>();

}
