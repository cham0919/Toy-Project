package com.wcp.coding.room;

import java.util.List;
import java.util.Optional;

public interface CodingRoomManager {

    /**
     * 글 등록
     * @param codingRoom
     * @return CodingRoom
     */
    CodingRoom save(CodingRoom codingRoom);

    /**
     * 한 페이지 리스트 조회
     * @param currentPage
     * @return List<CodingRoom>
     */
    List<CodingRoom> fetchByPage(String currentPage);
    List<CodingRoom> fetchByPage(int currentPage);

    /**
     * 글 id로 조회
     * @param id
     * @return Optional<CodingRoom>
     */
    Optional<CodingRoom> fetchById(String id);
    Optional<CodingRoom> fetchById(Long id);


    /**
     * 글 전체 조회
     * @return List<CodingRoom>
     */
    List<CodingRoom> fetchAll();

    /**
     * 글 수정
     * @param codingRoom
     * @return CodingRoom
     */
    CodingRoom update(CodingRoom codingRoom);

    /**
     * 글 삭제
     * @param codingRoom
     * @return CodingRoom
     */
    CodingRoom delete(CodingRoom codingRoom);

    /**
     * 글 id로 삭제
     * @param id
     * @return CodingRoom
     */
    void deleteById(String id);
    void deleteById(Long id);

    /**
     * 글 갯수 조회
     * @return Long
     */
    Long count();
}
