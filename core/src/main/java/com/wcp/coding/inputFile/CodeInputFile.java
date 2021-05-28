package com.wcp.coding.inputFile;


import com.wcp.WCPTable.*;
import com.wcp.WCPTable.CodeInputFileTable;
import com.wcp.coding.test.CodingTest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = CodeInputFileTable.TABLE_NAME)
public class CodeInputFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CodeInputFileTable.PK)
    private Long key;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CodingTestTable.PK)
    private CodingTest codingTest;

    @Column(name = CodeInputFileTable.PATH)
    private String path;

    @Column(name = CodeInputFileTable.GIVEN_NAME)
    private String givenName;

    @Column(name = CodeInputFileTable.FILE_NAME)
    private String fileName;

    @Column(name = CodeInputFileTable.FILE_SIZE)
    private Long fileSize;

    @CreatedDate
    @Column(name = CodeInputFileTable.UPLOAD_AT)
    private LocalDateTime uploadAt;

    public CodeInputFile addCodingTest(CodingTest codingTest){
        this.codingTest = codingTest;
        codingTest.setCodeInputFile(this);
        return this;
    }


}
