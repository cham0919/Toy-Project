package com.wcp.board.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.board.main.MainBoardService;
import com.wcp.board.main.MainBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/mainboard")
public class MainController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson uploadResultBuilder = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    @Autowired
    private MainBoardService mainBoardService;

    /**
     * 글 등록
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> insert(HttpServletRequest req,
                                         HttpServletResponse res){

        MainBoard test = new MainBoard();
        test.setSeq(1L);
        test.setReply("Y");
        test.setTitle("Y");
        test.setContent("Y");
        test.setCategory("fefe");
        test.setCount(1L);

        mainBoardService.savePost(test);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    /**
     * 글 수정
     */
    @RequestMapping(value = "/modify", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> modify(HttpServletRequest req,
                                         HttpServletResponse res){

        MainBoard test = new MainBoard();
        test.setSeq(1L);
        test.setReply("Y");
        test.setTitle("Y");
        test.setContent("Y");
        test.setCategory("fefe");
        test.setCount(1L);

        mainBoardService.savePost(test);


        return new ResponseEntity<String>("success", HttpStatus.OK);
    }


    /**
     * 글 삭제
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> delete(HttpServletRequest req,
                                         HttpServletResponse res){

        MainBoard test = new MainBoard();
        test.setSeq(1L);
        test.setReply("Y");
        test.setTitle("Y");
        test.setContent("Y");
        test.setCategory("fefe");
        test.setCount(1L);

        mainBoardService.savePost(test);


        return  new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
