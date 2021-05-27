package com.wcp.coding.check.file;


import com.wcp.WCPTable.*;
import com.wcp.WCPTable.CheckFileTable;
import com.wcp.coding.board.CodingBoard;
import com.wcp.coding.content.CodingContent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = CheckFileTable.TABLE_NAME)
public class CheckFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CheckFileTable.PK)
    private Long key;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CodingContentTable.PK)
    private CodingContent codingContent;

    @Column(name = CheckFileTable.PATH)
    private String path;

    @Column(name = CheckFileTable.GIVEN_NAME)
    private String givenName;

    @Column(name = CheckFileTable.FILE_NAME)
    private String fileName;

    @Column(name = CheckFileTable.FILE_SIZE)
    private Long fileSize;

    @CreatedDate
    @Column(name = CheckFileTable.UPLOAD_DATETIME)
    private LocalDateTime uploadDatetime;

    public CheckFile addCodingContent(CodingContent codingContent){
        this.codingContent = codingContent;
        codingContent.setCheckFile(this);
        return this;
    }


}
