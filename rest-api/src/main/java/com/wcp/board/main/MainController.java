package com.wcp.board.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.board.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/wcp/board/main")
public class MainController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    @Autowired
    private BoardService boardService;
/*
    *//**
     * 글 한건 조회
     *//*
    @RequestMapping(value = "/{postId:[0-9]+}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> fetchById(HttpServletRequest req,
                                               HttpServletResponse res,
                                               @PathVariable("postId") String postId)
    {
        try{
            MainBoard mainBoard = mainBoardService.fetchById(postId);
            return new ResponseEntity<String>(gson.toJson(mainBoard), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    *//**
     * 페이징
     *//*
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> fetchByRange(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestParam(defaultValue = "1")
                                                           String currentPage)
    {
        try{
            PageInfo pageInfo = mainBoardService.getPageList(currentPage);
            Map<String, Object> resultMap = pageInfo.parsePageRangeToMap();
            return new ResponseEntity<String>(gson.toJson(resultMap), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    *//**
     * 글 등록
     *//*
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> insert(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody String body)
    {
        try{
            MainBoard mainBoard = gson.fromJson(body, MainBoard.class);
            mainBoardService.save(mainBoard);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    *//**
     * 글 수정
     *//*
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> modify(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody String body){
        try{
            MainBoard mainBoard = gson.fromJson(body, MainBoard.class);
            mainBoardService.update(mainBoard);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    *//**
     * id로 글 삭제
     *//*
    @RequestMapping(value = "/{postId:[0-9]+}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> deleteById(HttpServletRequest req,
                                         HttpServletResponse res,
                                             @PathVariable("postId") String postId){

        try{
            mainBoardService.deleteById(postId);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
