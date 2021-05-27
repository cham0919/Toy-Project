package com.wcp.coding.content;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.coding.CodingBoardService;
import com.wcp.page.PageInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wcp/coding/content")
@RequiredArgsConstructor
public class CodingContentController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    private final CodingBoardService codingBoardService;

    @RequestMapping(value = "/{postId:[0-9]+}", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> save(HttpServletRequest req,
                                       HttpServletResponse res,
                                       @PathVariable("postId") String postId,
                                       @ModelAttribute("formData") MultiPartCodingContentDto multiPartCodingContentDto)
    {
        try{
            codingBoardService.registerContent(multiPartCodingContentDto,postId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{postId:[0-9]+}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> fetchById(HttpServletRequest req,
                                            HttpServletResponse res,
                                            @PathVariable("contentId") String contentId)
    {
        try{
            CodingContent codingContent = codingBoardService.fetchCodingContentById(contentId);
            return new ResponseEntity<String>(gson.toJson(codingContent), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/page/{pageNm:[0-9]+}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> fetchByPage(HttpServletRequest req,
                                              HttpServletResponse res,
                                              @PathVariable("pageNm") String pageNm)
    {
        try{
            List<CodingContent> codingContents = codingBoardService.fetchByCodingContentPage(pageNm);
            return new ResponseEntity<String>(gson.toJson(codingContents), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/page/range/{pageNm:[0-9]+}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> count(HttpServletRequest req,
                                        HttpServletResponse res,
                                        @PathVariable("pageNm") String pageNm)
    {
        try{
            PageInfo pageInfo = codingBoardService.fetchCodingContentPageList(pageNm);
            Map<String, Object> stringObjectMap = pageInfo.parsePageRangeToMap();
            return new ResponseEntity<String>(gson.toJson(stringObjectMap),HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> fetchAll(HttpServletRequest req,
                                           HttpServletResponse res)
    {
        try{
            List<CodingContent> codingContents = codingBoardService.fetchAllCodingContent();
            return new ResponseEntity<String>(gson.toJson(codingContents), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> update(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody CodingContent codingContent)
    {
        try{
            codingContent = codingBoardService.updateCodingContent(codingContent);
            return new ResponseEntity<String>(gson.toJson(codingContent), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> delete(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody CodingContent codingContent)
    {
        try{
            codingContent = codingBoardService.deleteCodingContent(codingContent);
            return new ResponseEntity<String>(gson.toJson(codingContent), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/del/{postId:[0-9]+}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> delete(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @PathVariable("postId") String postId)
    {
        try{
            codingBoardService.deleteCodingContentById(postId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cnt", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> count(HttpServletRequest req,
                                        HttpServletResponse res)
    {
        try{
            Long postCnt = codingBoardService.codingContentCount();
            return new ResponseEntity<String>(postCnt.toString(),HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
