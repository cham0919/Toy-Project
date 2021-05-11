package com.wcp.coding.submit;


import com.wcp.WCPTable.CodingContentTable;
import com.wcp.WCPTable.SubmitHistoryTable;
import com.wcp.WCPTable.UserTable;
import com.wcp.coding.content.CodingContent;
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
@Table(name = SubmitHistoryTable.TABLE_NAME)
public class SubmitHistory {

    @Id
    @GeneratedValue
    @Column(name = SubmitHistoryTable.PK)
    private Long key;

    @ManyToOne
    @JoinColumn(name = CodingContentTable.PK)
    private CodingContent codingContent;

    @ManyToOne
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = SubmitHistoryTable.STATUS)
    private String status;

    @Column(name = SubmitHistoryTable.LANGUAGE)
    private String language;

    @Column(name = SubmitHistoryTable.CODE)
    private String code;

    @CreatedDate
    @Column(name = SubmitHistoryTable.SUBMIT_DATETIME)
    private LocalDateTime submitDateTime;

    @Column(name = SubmitHistoryTable.RUN_TIME)
    private String runTime;
}
