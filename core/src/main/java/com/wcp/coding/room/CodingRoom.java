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
//    @JoinColumn(name = UserTable.PK, nullable = false)
    @JoinColumn(name = UserTable.PK)
    private User user;

    @Column(name = CodingRoomTable.TITLE, nullable = false)
    private String title;

    @Column(name = CodingRoomTable.INTRO)
    private String intro;

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

    public Map<String, Object> toMapForOpen() {
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("key", this.key);
        map.put("title", this.title);
        map.put("intro", this.intro);
        map.put("password", this.password);
        map.put("maxUser", this.maxUser);
        map.put("ramdomKey", this.ramdomKey);
        map.put("createDatetime", this.createdAt);
        map.put("codingJoinUsers", this.codingJoinUsers);
        map.put("codingTests", this.codingTests);
        return map;
    }

    @Override
    public String toString() {
        return toMapForOpen().toString();
    }
}
