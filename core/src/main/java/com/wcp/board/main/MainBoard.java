package com.wcp.board.main;

import com.wcp.board.Board;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class MainBoard extends Board {

    @Column(nullable = false)
    private String reply;

    public MainBoard() {
    }

    public MainBoard(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
