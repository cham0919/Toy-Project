package com.wcp.coding.board;


import com.wcp.WCPTable.*;
import com.wcp.WCPTable.CodingBoardTable;
import com.wcp.coding.content.CodingContent;
import com.wcp.coding.join.CodingJoinUser;
import com.wcp.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = CodingBoardTable.TABLE_NAME)
public class CodingBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CodingBoardTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = UserTable.PK, nullable = false)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = CodingBoardTable.TITLE, nullable = false)
    private String title;

    @Column(name = CodingBoardTable.INTRO)
    private String intro;

    @Column(name = CodingBoardTable.PASSWORD)
    private String password;

    @Column(name = CodingBoardTable.MAX_USER, nullable = false)
    private Long maxUser;

    @Column(name = CodingBoardTable.RAMDOM_KEY)
    private String ramdomKey;



    @CreationTimestamp
    @Column(name = CodingBoardTable.CREATE_DATETIME, nullable = false)
    private LocalDateTime createDatetime;

    @OneToMany(mappedBy = "codingBoard", fetch = FetchType.LAZY)
    private List<CodingJoinUser> codingJoinUsers = new ArrayList<>();

    @OneToMany(mappedBy = "codingBoard", fetch = FetchType.LAZY)
    private List<CodingContent> codingContents = new ArrayList<>();


}
