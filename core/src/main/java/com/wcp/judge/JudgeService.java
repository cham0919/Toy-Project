package com.wcp.judge;

import java.io.File;
import java.net.URI;
import java.util.List;

public interface JudgeService {

    List<JudegeResponseDto> createBatchedSubmission(JudgeRequestDto dto, String postId) throws Throwable;
    List<JudgeRequestDto> toJudgeRequestDtos(File[] files);

    String createBatchedSubmissionJson(List<JudgeRequestDto> dtos, JudgeRequestDto dto);

    URI getSubmissionUri(String token);
    URI createBatchedSubmissionUri();

    JudegeResponseDto getSubmission(String token) throws Throwable;

    List<JudegeResponseDto> createBatchedSubmission(String param) throws Throwable;
    void updateFromDto(JudgeRequestDto fromDto, JudgeRequestDto toDto);

}
