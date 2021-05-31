package com.wcp.coding.test;

import java.util.List;
import java.util.Optional;

public interface CodingTestManager {

    /**
     * 코딩 문제 등록
     * @param codingTest
     * @return CodingTest
     */
    CodingTest save(CodingTest codingTest);

    /**
     * 한 페이지 리스트 조회
     * @param currentPage
     * @return List<CodingTest>
     */
    List<CodingTest> fetchByPage(String currentPage);
    List<CodingTest> fetchByPage(int currentPage);

    /**
     * 코딩 문제 id로 조회
     * @param id
     * @return Optional<CodingTest>
     */
    Optional<CodingTest> fetchById(String id);
    Optional<CodingTest> fetchById(Long id);

    /**
     * 코딩 문제 전체 조회
     * @return List<CodingTest>
     */
    List<CodingTest> fetchAll();

    /**
     * 코딩 문제 수정
     * @param codingTest
     * @return CodingTest
     */
    CodingTest update(CodingTest codingTest);

    /**
     * 코딩 문제 삭제
     * @param codingTest
     * @return CodingTest
     */
    CodingTest delete(CodingTest codingTest);

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
