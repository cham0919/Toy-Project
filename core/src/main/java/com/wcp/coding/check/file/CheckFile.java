package com.wcp.coding.check.file;


import com.wcp.WCPTable.*;
import com.wcp.WCPTable.CheckFileTable;
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

    @CreatedDate
    @Column(name = CheckFileTable.UPLOAD_DATETIME)
    private LocalDateTime uploadDatetime;

}
