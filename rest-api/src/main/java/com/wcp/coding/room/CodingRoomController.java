package com.wcp.coding.room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.page.PageInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/wcp/coding/room")
@RequiredArgsConstructor
public class CodingRoomController {

    private final Logger log = LoggerFactory.getLogger(CodingRoomController.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    private final CodingRoomService codingRoomService;


    @PostMapping({"", "/"})
    public ResponseEntity<String> save(HttpServletRequest req,
                                       HttpServletResponse res,
                                       @RequestBody CodingRoomDto codingRoomDto) {
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        codingRoomDto = codingRoomService.save(codingRoomDto, userKey);
        return new ResponseEntity<String>(gson.toJson(codingRoomDto), HttpStatus.OK);
    }


    @GetMapping("/{postId:[0-9]+}")
    public ResponseEntity<String> fetchById(HttpServletRequest req,
                                            HttpServletResponse res,
                                            @PathVariable("postId") String postId) {
        CodingRoomDto codingRoomDto = codingRoomService.fetchById(postId);
        return new ResponseEntity<String>(gson.toJson(codingRoomDto), HttpStatus.OK);
    }


    @GetMapping("/page/{pageNm:[0-9]+}")
    public ResponseEntity<String> fetchByPage(HttpServletRequest req,
                                              HttpServletResponse res,
                                              @PathVariable("pageNm") String pageNm) {
        List<CodingRoomDto> dtos = codingRoomService.fetchByPage(pageNm);
        PageInfo pageInfo = codingRoomService.fetchPageList(pageNm);
        Map<String, Object> stringObjectMap = pageInfo.parsePageRangeToMap();
        stringObjectMap.put("post", dtos);
        return new ResponseEntity<String>(gson.toJson(stringObjectMap), HttpStatus.OK);
    }


    @GetMapping("/range/{pageNm:[0-9]+}")
    public ResponseEntity<String> count(HttpServletRequest req,
                                        HttpServletResponse res,
                                        @PathVariable("pageNm") String pageNm) {
        PageInfo pageInfo = codingRoomService.fetchPageList(pageNm);
        Map<String, Object> stringObjectMap = pageInfo.parsePageRangeToMap();
        return new ResponseEntity<String>(gson.toJson(stringObjectMap),HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<String> fetchAll(HttpServletRequest req,
                                           HttpServletResponse res) {
        List<CodingRoomDto> dtos = codingRoomService.fetchAll();
        return new ResponseEntity<String>(gson.toJson(dtos), HttpStatus.OK);
    }


    @PutMapping("/{postId:[0-9]+}")
    public ResponseEntity<String> update(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody CodingRoomDto dto) {
        dto = codingRoomService.update(dto);
        return new ResponseEntity<String>(gson.toJson(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> delete(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody CodingRoomDto codingRoomDto) {
        codingRoomService.delete(codingRoomDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId:[0-9]+}")
    public ResponseEntity<String> delete(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @PathVariable("postId") String postId) {
        codingRoomService.deleteById(postId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/cnt")
    public ResponseEntity<Long> count(HttpServletRequest req,
                                        HttpServletResponse res) {
        log.info("");
        Long postCnt = codingRoomService.count();
        return new ResponseEntity<Long>(postCnt,HttpStatus.OK);
    }
}
