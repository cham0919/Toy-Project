package com.wcp.like;


import com.wcp.WCPTable.*;
import com.wcp.WCPTable.BoardLikeTable;
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
@Table(name = BoardLikeTable.TABLE_NAME)
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BoardLikeTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = BoardLikeTable.TARGET_ID)
    private String id;

    @Column(name = BoardLikeTable.TARGET_TYPE)
    private String targetType;

    @Column(name = BoardLikeTable.TYPE)
    private String type;

    @CreatedDate
    @Column(name = BoardLikeTable.DATETIME)
    private LocalDateTime dateTime;


}
