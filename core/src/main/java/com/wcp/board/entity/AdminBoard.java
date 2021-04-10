package com.wcp.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AdminBoard extends Board{

    @Column(nullable = false)
    private String header;
}
