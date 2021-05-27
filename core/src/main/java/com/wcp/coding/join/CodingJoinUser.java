package com.wcp.coding.join;


import com.wcp.WCPTable.CodingJoinUserTable;
import com.wcp.WCPTable.CodingRoomTable;
import com.wcp.WCPTable.UserTable;
import com.wcp.coding.board.CodingRoom;
import com.wcp.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name = CodingRoomTable.PK)
    private CodingRoom codingRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = CodingJoinUserTable.STATUS)
    private String status;

    @Column(name = CodingJoinUserTable.ROLE)
    private String role;

    @CreatedDate
    @Column(name = CodingJoinUserTable.JOIN_AT)
    private LocalDateTime joinDatetime;

}
