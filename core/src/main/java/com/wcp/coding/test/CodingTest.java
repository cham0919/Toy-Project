package com.wcp.coding.test;

import com.wcp.WCPTable.CodingRoomTable;
import com.wcp.WCPTable.CodingTestTable;
import com.wcp.WCPTable.UserTable;
import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.submit.SubmitHistory;
import com.wcp.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = CodingTestTable.TABLE_NAME)
public class CodingTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CodingTestTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CodingRoomTable.PK)
    private CodingRoom codingRoom;

    @Column(name = CodingTestTable.TITLE)
    private String title;

    @Column(name = CodingTestTable.CONTENT, length = 10000)
    private String content;

    @Column(name = CodingTestTable.LANGUAGE, nullable = false)
    private String language;

    @Column(name = CodingTestTable.AUTH)
    private String auth;

    @CreationTimestamp
    @Column(name = CodingTestTable.SUBMIT_AT)
    private LocalDateTime submitAt;

    @OneToMany(mappedBy = "codingTest", fetch = FetchType.LAZY)
    private List<SubmitHistory> sumitHistories = new ArrayList<>();

    @OneToOne(mappedBy = "codingTest", fetch = FetchType.LAZY)
    private CodeInputFile codeInputFile;

    public CodingTest addCodingRoom(CodingRoom codingRoom){
        this.codingRoom = codingRoom;
        codingRoom.getCodingTests().add(this);
        return this;
    }

}
