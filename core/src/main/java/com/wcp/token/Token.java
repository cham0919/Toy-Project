package com.wcp.token;

import com.wcp.WCPTable.*;
import com.wcp.user.User;
import lombok.Data;
import com.wcp.WCPTable.TokenTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = TokenTable.TABLE_NAME)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TokenTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = TokenTable.VALUE)
    private String value;

    @Column(name = TokenTable.TYPE)
    private String type;

    @Column(name = TokenTable.EXPIRED)
    private String expired;

    @CreatedDate
    @Column(name = TokenTable.CREATE_DATETIME)
    private LocalDateTime createDatetime;

    @Column(name = TokenTable.USE_DATETIME)
    private LocalDateTime use_datetime;

}
