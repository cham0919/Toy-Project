package com.wcp.coding.content;

import com.wcp.WCPTable.CodingContentTable;
import com.wcp.WCPTable.CodingJoinUserTable;
import com.wcp.coding.check.file.CheckFile;
import com.wcp.coding.join.CodingJoinUser;
import com.wcp.coding.submit.SubmitHistory;
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
@Table(name = CodingContentTable.TABLE_NAME)
public class CodingContent {

    @Id
    @GeneratedValue
    @Column(name = CodingContentTable.PK)
    private Long key;

    @ManyToOne
    @JoinColumn(name = CodingJoinUserTable.PK)
    private CodingJoinUser codingJoinUser;

    @Column(name = CodingContentTable.TITLE)
    private String title;

    @Column(name = CodingContentTable.CONTENT)
    private String content;

    @Column(name = CodingContentTable.AUTH)
    private String auth;

    @CreatedDate
    @Column(name = CodingContentTable.SUBMIT_DATETIME)
    private LocalDateTime submitDatetime;

    @OneToMany(mappedBy = "codingContent")
    private List<SubmitHistory> sumitHistories = new ArrayList<>();

    @OneToOne(mappedBy = "codingContent")
    private CheckFile checkFile;

}
