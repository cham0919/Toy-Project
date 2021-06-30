package com.wcp.judge;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public interface JudgeService {

    List<JudegeResponseDto> createBatchedSubmission(JudgeRequestDto dto, String postId) throws IOException;
    List<JudgeRequestDto> toJudgeRequestDtos(File[] files);

    String createBatchedSubmissionJson(List<JudgeRequestDto> dtos, JudgeRequestDto dto);

    URI getSubmissionUri(String token);
    URI createBatchedSubmissionUri();

    JudegeResponseDto getSubmission(String token) throws IOException;

    List<JudegeResponseDto> createBatchedSubmission(String param) throws IOException;
    void updateFromDto(JudgeRequestDto fromDto, JudgeRequestDto toDto);

}
