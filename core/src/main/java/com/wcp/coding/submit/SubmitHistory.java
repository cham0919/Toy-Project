package com.wcp.coding.submit;


import com.wcp.WCPTable.CodingTestTable;
import com.wcp.WCPTable.SubmitHistoryTable;
import com.wcp.WCPTable.UserTable;
import com.wcp.coding.test.CodingTest;
import com.wcp.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = SubmitHistoryTable.TABLE_NAME)
public class SubmitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SubmitHistoryTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CodingTestTable.PK)
    private CodingTest codingTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = SubmitHistoryTable.STATUS)
    private String status;

    @Column(name = SubmitHistoryTable.LANGUAGE)
    private String language;

    @Column(name = SubmitHistoryTable.CODE)
    private String code;

    @CreatedDate
    @Column(name = SubmitHistoryTable.SUBMIT_AT)
    private LocalDateTime submitAt;

    @Column(name = SubmitHistoryTable.RUN_TIME)
    private String runTime;
}
