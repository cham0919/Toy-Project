package com.wcp.coding.test;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.page.PageInfo;
import lombok.RequiredArgsConstructor;
import org.apache.tika.mime.MimeTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/wcp/coding/room/{roomId:[0-9]+}/test")
@RequiredArgsConstructor
public class CodingTestController {


    private final Logger log = LoggerFactory.getLogger(CodingTestController.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    private final CodingTestService codingTestService;


    @GetMapping({"", "/"})
    public ResponseEntity<String> fetchCodingTestByPage(HttpServletRequest req,
                                                        HttpServletResponse res,
                                                        @RequestParam String pageNm,
                                                        @PathVariable("roomId") String roomId) {
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CodingTestDto> dtos = codingTestService.fetchByCurrentPage(pageNm, roomId, userKey);
        PageInfo pageInfo = codingTestService.fetchPageList(pageNm, roomId);
        Map<String, Object> respMap = pageInfo.parsePageRangeToMap();
        respMap.put("post", dtos);
        return new ResponseEntity<String>(gson.toJson(respMap), HttpStatus.OK);
    }


    @PostMapping(value = {"/", ""}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCodingTest(HttpServletRequest req,
                                                 HttpServletResponse res,
                                                 @PathVariable("roomId") String roomId,
                                                 @ModelAttribute("formData") MultiPartDto multiPartDto) throws IOException, MimeTypeException {
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        multiPartDto.setUserKey(userKey)
                .setPostId(roomId);
        codingTestService.registerContent(multiPartDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/{testId:[0-9]+}")
    public ResponseEntity<String> fetchCodingTestById(HttpServletRequest req,
                                                      HttpServletResponse res,
                                                      @PathVariable("testId") String testId) {
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        CodingTestDto dto = codingTestService.fetchById(testId);
        return new ResponseEntity<String>(gson.toJson(dto), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<String> fetchCodingTestAll(HttpServletRequest req,
                                                     HttpServletResponse res) {
        List<CodingTestDto> codingTestDtos = codingTestService.fetchAll();
        return new ResponseEntity<String>(gson.toJson(codingTestDtos), HttpStatus.OK);
    }

    @PutMapping("/{postId:[0-9]+}")
    public ResponseEntity<String> updateCodingTest(HttpServletRequest req,
                                                   HttpServletResponse res,
                                                   @RequestBody CodingTestDto codingTestDto) {
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        codingTestDto.setUserKey(userKey);
        codingTestDto = codingTestService.update(codingTestDto);
        return new ResponseEntity<String>(gson.toJson(codingTestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{postId:[0-9]+}")
    public ResponseEntity<String> deleteCodingTest(HttpServletRequest req,
                                                   HttpServletResponse res,
                                                   @PathVariable("postId") String postId) {
        codingTestService.deleteById(postId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/cnt")
    public ResponseEntity<String> codingTestCount(HttpServletRequest req,
                                                  HttpServletResponse res) {
        Long postCnt = codingTestService.count();
        return new ResponseEntity<String>(String.valueOf(postCnt),HttpStatus.OK);
    }
}
