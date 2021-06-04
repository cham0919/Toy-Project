package com.wcp.coding.submit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.coding.CodingRoomService;
import com.wcp.judge.JudegeResponseDto;
import com.wcp.judge.JudgeRequestDto;
import com.wcp.judge.JudgeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/wcp/coding/submit")
@RequiredArgsConstructor
public class SubmitController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    private final SubmitHistoryService submitHistoryService;
    private final JudgeService judgeService;

    @RequestMapping(value = "/final/{postId:[0-9]+}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> registerResult(HttpServletRequest req,
                                                HttpServletResponse res,
                                                 @PathVariable("postId") String postId,
                                                 @RequestBody SubmitHistoryDto dto)
    {
        try{
            String roomId = submitHistoryService.registerSubmitHistory(dto, postId);
            return new ResponseEntity<String>(roomId, HttpStatus.OK);
        }catch (Throwable t){
            log.error("submission error",t);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{postId:[0-9]+}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> createBatchedSubmission(HttpServletRequest req,
                                             HttpServletResponse res,
                                             @PathVariable("postId") String postId,
                                             @RequestBody JudgeRequestDto dto)
    {
        try{
            List<JudegeResponseDto> resps = judgeService.createBatchedSubmission(dto, postId);
            return new ResponseEntity<String>(gson.toJson(resps), HttpStatus.OK);
        }catch (Throwable t){
            log.error("submission error",t);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{token}/{postId:[0-9]+}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> getSubmission(HttpServletRequest req,
                                                   HttpServletResponse res,
                                                   @PathVariable("token") String token)
    {
        try{
            JudegeResponseDto resp = judgeService.getSubmission(token);
            return new ResponseEntity<String>(gson.toJson(resp), HttpStatus.OK);
        }catch (Throwable t){
            log.error("submission error",t);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
