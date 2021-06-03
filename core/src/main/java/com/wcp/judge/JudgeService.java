package com.wcp.judge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wcp.coding.inputFile.CodeInputFileService;
import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestDto;
import com.wcp.coding.test.CodingTestManager;
import com.wcp.common.Base64Utils;
import com.wcp.common.http.HttpRequest;
import lombok.RequiredArgsConstructor;
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

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JudgeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Gson gson = new GsonBuilder().create();
    private final CodingTestManager codingTestManager;
    private final CodeInputFileService codeInputFileService;


    public List<JudegeResponseDto> createBatchedSubmission(JudgeRequestDto dto, String postId) throws Throwable {
        CodingTest codingTest =  codingTestManager.fetchById(postId).get();
        List<JudgeRequestDto> dtos =  codeInputFileService.fetchCodingTestIOData(codingTest.getCodeInputFile().getKey());
        String params = createBatchedSubmissionJson(dtos, dto);
        List<JudegeResponseDto> responseDtos = createBatchedSubmission(params);
        return responseDtos;
    }

    private String createBatchedSubmissionJson(List<JudgeRequestDto> dtos, JudgeRequestDto dto) {
        for (JudgeRequestDto ioDto : dtos) {
            log.info(ioDto.toString());
            updateFromDto(dto, ioDto);
        }
        Map<String, List> map = new HashMap<>();
        map.put("submissions", dtos);
        return gson.toJson(map);
    }


    public URI getSubmissionUri(String token){
        return UriComponentsBuilder.fromHttpUrl(Judge.SUBMISSION)
                .path(token)
                .queryParam("base64_encoded","true")
                .queryParam("fields","*")
                .build()
                .toUri();
    }

    public URI createBatchedSubmissionUri(){
        return UriComponentsBuilder.fromHttpUrl(Judge.BATCHSUBMISSION)
                .queryParam("base64_encoded","true")
                .queryParam("fields","*")
                .build()
                .toUri();
    }


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


    private List<JudegeResponseDto> createBatchedSubmission(String param) throws Throwable {
        HttpResponse resp =HttpRequest.of()
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
