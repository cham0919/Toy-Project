package com.wcp.board.admin;

import com.wcp.board.Board;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AdminBoard extends Board {

    @Column(nullable = false)
    private String header;
}
