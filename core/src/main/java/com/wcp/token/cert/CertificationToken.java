package com.wcp.token.cert;

import com.wcp.WCPTable.*;
import com.wcp.user.User;
import com.wcp.WCPTable.CertificationTokenTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = CertificationTokenTable.TABLE_NAME)
public class CertificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CertificationTokenTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = CertificationTokenTable.TOKEN)
    private String token;

    @Column(name = CertificationTokenTable.TYPE)
    private String type;

    @Column(name = CertificationTokenTable.EXPIRED)
    private String expired;

    @CreatedDate
    @Column(name = CertificationTokenTable.CREATED_AT)
    private LocalDateTime createdAt;

    @Column(name = CertificationTokenTable.USED_AT)
    private LocalDateTime usedAt;

}
