package com.wcp.user;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "wcp_user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_key")
    private Long key;

    @Column(name = "user_id", length = 2000, nullable = false)
    private String id;

    @Column(name = "user_pw",length = 2000, nullable = false)
    private String password;

    @Column(name = "user_name", length = 100, nullable = false)
    private String name;

    @Column(length = 1000)
    private String email;

    @Column(length = 100)
    private String phone;

    @Column(length = 100)
    private String available;

    @Column(length = 100)
    private LocalDateTime lastLoginDate;



}
