package com.wcp.coding.room;


import com.wcp.WCPTable.CodingRoomTable;
import com.wcp.WCPTable.UserTable;
import com.wcp.coding.test.CodingTest;
import com.wcp.coding.join.CodingJoinUser;
import com.wcp.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = CodingRoomTable.TABLE_NAME)
public class CodingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CodingRoomTable.PK)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserTable.PK, nullable = false)
    private User user;

    @Column(name = CodingRoomTable.TITLE, nullable = false)
    private String title;

    @Column(name = CodingRoomTable.INTRO)
    private String intro;

    @Column(name = CodingRoomTable.SECRET)
    private boolean secret;

    @Column(name = CodingRoomTable.PASSWORD)
    private String password;

    @Column(name = CodingRoomTable.MAX_USER, nullable = false)
    private Long maxUser;

    @Column(name = CodingRoomTable.RAMDOM_KEY)
    private String ramdomKey;

    @CreationTimestamp
    @Column(name = CodingRoomTable.CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "codingRoom", fetch = FetchType.LAZY)
    private List<CodingJoinUser> codingJoinUsers = new ArrayList<>();

    @OneToMany(mappedBy = "codingRoom", fetch = FetchType.LAZY)
    private List<CodingTest> codingTests = new ArrayList<>();

    public CodingRoom addUser(User user){
        setUser(user);
        getUser().getCodingRooms().add(this);
        return this;
    }

    @Override
    public String toString() {
        return "CodingRoom{" +
                "key=" + key +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", password='" + password + '\'' +
                ", maxUser=" + maxUser +
                ", ramdomKey='" + ramdomKey + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
