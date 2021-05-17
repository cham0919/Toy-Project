package com.wcp.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface BoardPersistenceManager {

    /**
     * 글 등록
     * @param Board
     * @return Board
     */
    Board save(Board Board);

    /**
     * 한 페이지 리스트 조회
     * @param currentPage
     * @return List<Board>
     */
    List<Board> fetchByPage(int currentPage);

    /**
     * 글 id로 조회
     * @param id
     * @return Optional<Board>
     */
    Optional<Board> fetchById(Long id);

    /**
     * 글 전체 조회
     * @return List<Board>
     */
    List<Board> fetchAll();

    /**
     * 글 수정
     * @param board
     * @return Board
     */
    Board update(Board board);

    /**
     * 글 삭제
     * @param board
     * @return Board
     */
    public Board delete(Board board);

    /**
     * 글 id로 삭제
     * @param id
     * @return Board
     */
    void deleteById(Long id);

    /**
     * 글 갯수 조회
     * @return Long
     */
    Long count();
}
