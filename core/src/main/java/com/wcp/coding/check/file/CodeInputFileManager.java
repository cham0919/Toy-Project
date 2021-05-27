package com.wcp.coding.check.file;

import java.util.List;
import java.util.Optional;

public interface CodeInputFileManager {

    /**
     * 코딩 답변 파일 등록
     * @param codeInputFile
     * @return CodeInputFile
     */
    CodeInputFile save(CodeInputFile codeInputFile);

    /**
     * 코딩 답변 파일 id로 조회
     * @param id
     * @return Optional<CodeInputFile>
     */
    Optional<CodeInputFile> fetchById(String id);
    Optional<CodeInputFile> fetchById(Long id);

    /**
     * 코딩 답변 파일 전체 조회
     * @return List<CodeInputFile>
     */
    List<CodeInputFile> fetchAll();

    /**
     * 코딩 답변 파일 수정
     * @param codeInputFile
     * @return CodeInputFile
     */
    CodeInputFile update(CodeInputFile codeInputFile);

    /**
     * 코딩 답변 파일 삭제
     * @param codeInputFile
     * @return CodeInputFile
     */
    CodeInputFile delete(CodeInputFile codeInputFile);

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
