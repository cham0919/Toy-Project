package com.wcp.coding.join;


import com.wcp.WCPTable.*;
import com.wcp.WCPTable.CodingJoinUserTable;
import com.wcp.coding.board.CodingBoard;
import com.wcp.coding.content.CodingContent;
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
@Table(name = CodingJoinUserTable.TABLE_NAME)
public class CodingJoinUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CodingJoinUserTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CodingBoardTable.PK)
    private CodingBoard codingBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = CodingJoinUserTable.STATUS)
    private String status;

    @Column(name = CodingJoinUserTable.ROLE)
    private String role;

    @CreatedDate
    @Column(name = CodingJoinUserTable.JOIN_DATETIME)
    private LocalDateTime joinDatetime;

    @OneToMany(mappedBy = "codingJoinUser", fetch = FetchType.LAZY)
    private List<CodingContent> codingContents = new ArrayList<>();
}
