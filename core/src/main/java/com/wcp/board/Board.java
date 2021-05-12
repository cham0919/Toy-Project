package com.wcp.board;

import com.wcp.WCPTable.BoardTable;
import com.wcp.WCPTable.UserTable;
import com.wcp.WCPTable.BoardCategoryTable;
import com.wcp.commant.BoardCommant;
import com.wcp.user.User;
import com.wcp.category.BoardCategory;
import lombok.Data;
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
    @GeneratedValue
    @Column(name = BoardTable.PK)
    private Long key;

    @ManyToOne
    @JoinColumn(name = UserTable.PK)
    private User user;

    @ManyToOne
    @JoinColumn(name = BoardCategoryTable.PK)
    private BoardCategory boardCategory;

    @Column(name = BoardTable.TITLE)
    private String title;

    @Column(name = BoardTable.CONTENT)
    private String content;

    @CreatedDate
    @Column(name = BoardTable.UPLOAD_DATETIME)
    private LocalDateTime uploadDatetime;

    @CreatedDate
    @Column(name = BoardTable.UPDATE_DATETIME)
    private LocalDateTime updateDatetime;

    @Column(name = BoardTable.HIT)
    private Long hit;

    @Column(name = BoardTable.LIKE)
    private Long like;

    @Column(name = BoardTable.DISLIKE)
    private Long disLike;

    @Column(name = BoardTable.DELETE)
    private String del;

    @OneToMany(mappedBy = "board")
    private List<BoardCommant> boardCommants = new ArrayList<>();

}
