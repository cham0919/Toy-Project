package com.wcp.coding.board;

import java.util.List;
import java.util.Optional;

public interface CodingBoardPersistenceManager {

    /**
     * 글 등록
     * @param codingBoard
     * @return codingBoard
     */
    CodingBoard save(CodingBoard codingBoard);

    /**
     * 한 페이지 리스트 조회
     * @param currentPage
     * @return List<CodingBoard>
     */
    List<CodingBoard> fetchByPage(String currentPage);
    List<CodingBoard> fetchByPage(int currentPage);

    /**
     * 글 id로 조회
     * @param id
     * @return Optional<codingBoard>
     */
    Optional<CodingBoard> fetchById(String id);
    Optional<CodingBoard> fetchById(Long id);


    /**
     * 글 전체 조회
     * @return List<codingBoard>
     */
    List<CodingBoard> fetchAll();

    /**
     * 글 수정
     * @param codingBoard
     * @return codingBoard
     */
    CodingBoard update(CodingBoard codingBoard);

    /**
     * 글 삭제
     * @param codingBoard
     * @return codingBoard
     */
    CodingBoard delete(CodingBoard codingBoard);

    /**
     * 글 id로 삭제
     * @param id
     * @return codingBoard
     */
    void deleteById(String id);
    void deleteById(Long id);

    /**
     * 글 갯수 조회
     * @return Long
     */
    Long count();
}
