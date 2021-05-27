package com.wcp.coding.check.file;

import java.util.List;
import java.util.Optional;

public interface CheckFilePersistenceManager {

    /**
     * 코딩 답변 파일 등록
     * @param checkFile
     * @return CheckFile
     */
    CheckFile save(CheckFile checkFile);

    /**
     * 코딩 답변 파일 id로 조회
     * @param id
     * @return Optional<CheckFile>
     */
    Optional<CheckFile> fetchById(String id);
    Optional<CheckFile> fetchById(Long id);

    /**
     * 코딩 답변 파일 전체 조회
     * @return List<CheckFile>
     */
    List<CheckFile> fetchAll();

    /**
     * 코딩 답변 파일 수정
     * @param checkFile
     * @return CheckFile
     */
    CheckFile update(CheckFile checkFile);

    /**
     * 코딩 답변 파일 삭제
     * @param checkFile
     * @return CheckFile
     */
    CheckFile delete(CheckFile checkFile);

    /**
     * 파일 id로 삭제
     * @param id
     */
    void deleteById(String id);
    void deleteById(Long id);

    /**
     * 답변 파일 갯수 조회
     * @return Long
     */
    Long count();
}
