package com.wcp.judge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JudgeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Gson gson = new GsonBuilder().create();
    private final CodingTestManager codingTestManager;
    private final CodeInputFileService codeInputFileService;

    /**
     * 코드 제출
     * input format
     *"{\n" +
     *             "  \"language_id\": 52,\n" +
     *             "  \"source_code\": \"I2luY2x1ZGUgPHN0ZGlvLmg+CgppbnQgbWFpbih2b2lkKSB7CiAgY2hhciBuYW1lWzEwXTsKICBzY2FuZigiJXMiLCBuYW1lKTsKICBwcmludGYoImhlbGxvLCAlc1xuIiwgbmFtZSk7CiAgcmV0dXJuIDA7Cn0=\",\n" +
     *             "  \"stdin\": \"SnVkZ2Uw\"\n" +
     *             "}";
     * return format
     * {"token":"2cd6d536-db67-427a-b5e8-1a77d05e3ef9"}
     * @param dto
     * @throws Throwable
     */
    public List<JudegeResponseDto> getSubmission(JudgeRequestDto dto, String postId) throws Throwable {
        dto.setUri(createSubmissionUri());
        CodingTest codingTest =  codingTestManager.fetchById(postId).get();
        Map<Integer, JudgeRequestDto> map =  codeInputFileService.fetchCodingTestIOData(codingTest.getCodeInputFile().getKey());
        List<JudegeResponseDto> resps = new ArrayList<>();
        for (Integer i : map.keySet()) {
            log.info(map.get(i).toString());
            synchronizeDto(dto, map.get(i));
            resps.add(connectSubmit(dto));
        }
        return resps;
    }

    private void synchronizeDto(JudgeRequestDto baseDto, JudgeRequestDto dto){
        baseDto.setStdin(Base64Utils.encode(dto.getStdin()));
        baseDto.setExpected_output(Base64Utils.encode(dto.getExpected_output()));
    }


    public URI createSubmissionUri(){
        return UriComponentsBuilder.fromHttpUrl(Judge.SUBMISSION)
                .queryParam("base64_encoded","true")
                .queryParam("wait","true")
                .queryParam("fields","*")
                .build()
                .toUri();
    }

    private JudegeResponseDto connectSubmit(JudgeRequestDto dto) throws Throwable {

        HttpResponse resp =HttpRequest.of()
                .post(dto.getUri())
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .addHeader(Judge.TOKEN_KEY, Judge.TOKEN_VALUE)
                .addHeader(Judge.HOST_KEY,  Judge.HOST_VALUE)
                .setEntity(new StringEntity(gson.toJson(dto)))
                .execute();

//        if(resp.getStatusLine().getStatusCode())

        ResponseHandler<String> handler = new BasicResponseHandler();
        String result =  handler.handleResponse(resp);
        return gson.fromJson(result, JudegeResponseDto.class);

    }


}
