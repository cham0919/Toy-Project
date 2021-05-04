package com.wcp.board;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.board.faq.FAQBoard;
import com.wcp.board.faq.FAQBoardService;
import com.wcp.board.page.PageInfo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class FAQBoardTest extends BoardTest {

    @Autowired
    FAQBoardService faqBoardService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    @Test
    public void fetchByIdTest(){
        FAQBoard faqBoard = faqBoardService.fetchById(postId);
    }

    @Test
    public void fetchByRangeTest(){
        PageInfo pageInfo = faqBoardService.getPageList(currentPage);
        Map<String, Object> resultMap = pageInfo.parsePageRangeToMap();
    }

    @Test
    public void insertTest(){
        FAQBoard faqBoard = new FAQBoard();
        faqBoardService.save(faqBoard);
    }

    @Test
    public void modifyTest(){
        FAQBoard faqBoard = new FAQBoard();
        faqBoardService.update(faqBoard);
    }

    @Test
    public void deleteByIdTest(){
        faqBoardService.deleteById(postId);
    }
}
