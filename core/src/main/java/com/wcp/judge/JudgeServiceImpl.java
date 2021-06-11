package com.wcp.judge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wcp.coding.inputFile.CodeInputFileService;
import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestRepository;
import com.wcp.common.Base64Utils;
import com.wcp.common.file.FileUtils;
import com.wcp.common.http.HttpRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wcp.common.file.FileExtension.*;
import static com.wcp.coding.inputFile.InputFileExtension.*;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {

    private final Logger log = LoggerFactory.getLogger(JudgeServiceImpl.class);
    private Gson gson = new GsonBuilder().create();
    private final CodingTestRepository codingTestRepository;
    private final CodeInputFileService codeInputFileService;


    @Override
    public List<JudegeResponseDto> createBatchedSubmission(JudgeRequestDto dto, String postId) throws Throwable {
        if (StringUtils.isEmpty(postId) || !StringUtils.isNumeric(postId)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check userKey : "+ postId);
        }
        CodingTest codingTest =  codingTestRepository.findById(Long.valueOf(postId)).get();
        File[] files =  codeInputFileService.fetchIOFilesById(codingTest.getCodeInputFile().getKey());
        String params = createBatchedSubmissionJson(toJudgeRequestDtos(files), dto);
        List<JudegeResponseDto> responseDtos = createBatchedSubmission(params);
        return responseDtos;
    }

    @Override
    public List<JudgeRequestDto> toJudgeRequestDtos(File[] files){
        List<JudgeRequestDto> judgeRequestDtos = new ArrayList<>();
        JudgeRequestDto dto = null;

        for (File file : files) {
            String extenstion = FilenameUtils.getExtension(file.getName());
            if(ZIP.equalsIgnoreValue(extenstion)){ continue; }

            String content = FileUtils.readFileToString(file);
            if(IN.equalsIgnoreValue(extenstion)) {
                dto = new JudgeRequestDto().setStdin(
                        Base64Utils.encode(content));
                judgeRequestDtos.add(dto);
            }else if(OUT.equalsIgnoreValue(extenstion)){
                dto.setExpected_output(
                        Base64Utils.encode(content));
            }
        }

        return judgeRequestDtos;
    }

    @Override
    public String createBatchedSubmissionJson(List<JudgeRequestDto> dtos, JudgeRequestDto dto) {
        for (JudgeRequestDto ioDto : dtos) {
            log.info(ioDto.toString());
            updateFromDto(dto, ioDto);
        }
        Map<String, List> map = new HashMap<>();
        map.put("submissions", dtos);
        return gson.toJson(map);
    }

    @Override
    public URI getSubmissionUri(String token){
        return UriComponentsBuilder.fromHttpUrl(Judge.SUBMISSION)
                .path(token)
                .queryParam("base64_encoded","true")
                .queryParam("fields","*")
                .build()
                .toUri();
    }
    @Override
    public URI createBatchedSubmissionUri(){
        return UriComponentsBuilder.fromHttpUrl(Judge.BATCHSUBMISSION)
                .queryParam("base64_encoded","true")
                .queryParam("fields","*")
                .build()
                .toUri();
    }

    @Override
    public JudegeResponseDto getSubmission(String token) throws Throwable {
        HttpResponse resp =HttpRequest.of()
                .post(getSubmissionUri(token))
                .addHeader(Judge.TOKEN_KEY, Judge.TOKEN_VALUE)
                .addHeader(Judge.HOST_KEY,  Judge.HOST_VALUE)
                .execute();

//        if(resp.getStatusLine().getStatusCode())
        //TODO. 상태코드에 따른 에러 분기 처리
        ResponseHandler<String> handler = new BasicResponseHandler();
        String result =  handler.handleResponse(resp);

        return gson.fromJson(result, JudegeResponseDto.class);
    }

    @Override
    public List<JudegeResponseDto> createBatchedSubmission(String param) throws Throwable {
        HttpResponse resp = HttpRequest.of()
                .post(createBatchedSubmissionUri())
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .addHeader(Judge.TOKEN_KEY, Judge.TOKEN_VALUE)
                .addHeader(Judge.HOST_KEY,  Judge.HOST_VALUE)
                .setEntity(new StringEntity(param))
                .execute();

//        if(resp.getStatusLine().getStatusCode())
        //TODO. 상태코드에 따른 에러 분기 처리
        ResponseHandler<String> handler = new BasicResponseHandler();
        String result =  handler.handleResponse(resp);

        return gson.fromJson(result, new TypeToken<List<JudegeResponseDto>>() {}.getType());
    }

    @Override
    public void updateFromDto(JudgeRequestDto fromDto, JudgeRequestDto toDto) {
        if ( fromDto == null ) {
            return;
        }

        if ( fromDto.getUri() != null ) {
            toDto.setUri( fromDto.getUri() );
        }
        if ( fromDto.getLanguage_id() != 0 ) {
            toDto.setLanguage_id( fromDto.getLanguage_id() );
        }
        if ( fromDto.getExpected_output() != null ) {
            toDto.setExpected_output(fromDto.getExpected_output());
        }
        if ( fromDto.getSource_code() != null ) {
            toDto.setSource_code(fromDto.getSource_code());
        }
        if ( fromDto.getStdin() != null ) {
            toDto.setStdin(fromDto.getStdin());
        }
    }

}
