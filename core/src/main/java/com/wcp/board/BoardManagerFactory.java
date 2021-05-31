package com.wcp.board;

public interface BoardManagerFactory {
    public BoardPersistenceManager create(String boardType);
}
