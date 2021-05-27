package com.wcp.coding.content;

import com.wcp.coding.board.CodingBoard;

import java.util.List;
import java.util.Optional;

public interface CodingContentPersistenceManager {

    /**
     * 코딩 문제 등록
     * @param codingContent
     * @return CodingContent
     */
    CodingContent save(CodingContent codingContent);

    /**
     * 한 페이지 리스트 조회
     * @param currentPage
     * @return List<CodingContent>
     */
    List<CodingContent> fetchByPage(String currentPage);
    List<CodingContent> fetchByPage(int currentPage);

    /**
     * 코딩 문제 id로 조회
     * @param id
     * @return Optional<CodingContent>
     */
    Optional<CodingContent> fetchById(String id);
    Optional<CodingContent> fetchById(Long id);

    /**
     * 코딩 문제 전체 조회
     * @return List<CodingContent>
     */
    List<CodingContent> fetchAll();

    /**
     * 코딩 문제 수정
     * @param codingContent
     * @return codingBoard
     */
    CodingContent update(CodingContent codingContent);

    /**
     * 코딩 문제 삭제
     * @param codingContent
     * @return CodingContent
     */
    CodingContent delete(CodingContent codingContent);

    /**
     * 글 id로 삭제
     * @param id
     */
    void deleteById(String id);
    void deleteById(Long id);

    /**
     * 글 갯수 조회
     * @return Long
     */
    Long count();
}
