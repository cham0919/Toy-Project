package com.wcp.coding.content;

import com.wcp.WCPTable.CodingBoardTable;
import com.wcp.WCPTable.CodingContentTable;
import com.wcp.coding.board.CodingBoard;
import com.wcp.coding.check.file.CheckFile;
import com.wcp.coding.submit.SubmitHistory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = CodingContentTable.TABLE_NAME)
public class CodingContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CodingContentTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CodingBoardTable.PK)
    private CodingBoard codingBoard;

    @Column(name = CodingContentTable.TITLE)
    private String title;

    @Column(name = CodingContentTable.CONTENT)
    private String content;

    @Column(name = CodingBoardTable.LANGUAGE, nullable = false)
    private String language;

    @Column(name = CodingContentTable.AUTH)
    private String auth;

    @CreationTimestamp
    @Column(name = CodingContentTable.SUBMIT_DATETIME)
    private LocalDateTime submitDatetime;

    @OneToMany(mappedBy = "codingContent", fetch = FetchType.LAZY)
    private List<SubmitHistory> sumitHistories = new ArrayList<>();

    @OneToOne(mappedBy = "codingContent", fetch = FetchType.LAZY)
    private CheckFile checkFile;

    public CodingContent addCodingBoard(CodingBoard codingBoard){
        this.codingBoard = codingBoard;
        codingBoard.getCodingContents().add(this);
        return this;
    }

}
