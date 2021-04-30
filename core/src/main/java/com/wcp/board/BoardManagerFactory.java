package com.wcp.board;

public interface BoardManagerFactory {
    public BoardManager create(String boardType);
}
