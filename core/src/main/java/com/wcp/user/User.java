package com.wcp.user;

import com.wcp.WCPTable.UserTable;
import com.wcp.board.Board;
import com.wcp.coding.board.CodingBoard;
import com.wcp.commant.BoardCommant;
import com.wcp.coding.submit.SubmitHistory;
import com.wcp.like.BoardLike;
import com.wcp.security.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = UserTable.TABLE_NAME)
public class User {

    @Id
    @GeneratedValue
    @Column(name = UserTable.PK)
    private Long key;

    @Column(name = UserTable.ID, nullable = false)
    private String id;

    @Column(name = UserTable.PW, nullable = false)
    private String password;

    @Column(name = UserTable.EMAIL)
    private String email;

    @Column(name = UserTable.NAME, nullable = false)
    private String name;

    @Column(name = UserTable.NICKNAME, length = 100)
    private String nickname;

    @Column(name = UserTable.PHONE, length = 100)
    private String phone;

    @Column(name = UserTable.REGISTER_DATETIME)
    private LocalDateTime register_datetime;

    @Column(name = UserTable.ROLE)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = UserTable.STATUS)
    private String status;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardCommant> boardCommants = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardLike> boardLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CodingBoard> codingBoards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SubmitHistory> submitHistories = new ArrayList<>();

    public User setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "key=" + key +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", register_datetime=" + register_datetime +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
