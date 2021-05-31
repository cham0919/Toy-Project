package com.wcp.security;


import com.wcp.WCPTable;
import lombok.Getter;
import lombok.Setter;
import com.wcp.WCPTable.PersistentLoginsTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = PersistentLoginsTable.TABLE_NAME)
@Getter
@Setter
public class PersistentLogins {

    @Id
    @Column(name = PersistentLoginsTable.PK, length = 64)
    private String series;

    @Column(name = PersistentLoginsTable.USERNAME, length = 64, nullable = false)
    private String username;

    @Column(name = PersistentLoginsTable.TOKEN, length = 64, nullable = false)
    private String token;

    @Column(name = PersistentLoginsTable.LASTUSED, length = 64, nullable = false)
    private LocalDateTime lastUsed;

}