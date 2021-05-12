package com.wcp.coding.board;


import com.wcp.WCPTable.*;
import com.wcp.WCPTable.CodingBoardTable;
import com.wcp.coding.join.CodingJoinUser;
import com.wcp.user.User;
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
@Table(name = CodingBoardTable.TABLE_NAME)
public class CodingBoard {

    @Id
    @GeneratedValue
    @Column(name = CodingBoardTable.PK)
    private Long key;

    @ManyToOne
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = CodingBoardTable.TITLE)
    private String title;

    @Column(name = CodingBoardTable.INTRO)
    private String intro;

    @Column(name = CodingBoardTable.SECRET)
    private String secret;

    @Column(name = CodingBoardTable.PASSWORD)
    private String password;

    @Column(name = CodingBoardTable.MAX_USER)
    private Long maxUser;

    @Column(name = CodingBoardTable.RAMDOM_KEY)
    private String ramdomKey;

    @Column(name = CodingBoardTable.LANGUAGE)
    private String language;

    @CreatedDate
    @Column(name = CodingBoardTable.CREATE_DATETIME)
    private LocalDateTime createDatetime;

    @OneToMany(mappedBy = "codingBoard")
    private List<CodingJoinUser> codingJoinUsers = new ArrayList<>();
}
