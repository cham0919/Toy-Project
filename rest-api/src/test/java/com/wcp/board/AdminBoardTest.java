package com.wcp.board;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.board.admin.AdminBoard;
import com.wcp.board.admin.AdminBoardService;
import com.wcp.board.page.PageInfo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class AdminBoardTest extends BoardTest {

    @Autowired
    AdminBoardService adminBoardService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    @Test
    public void fetchByIdTest(){
        AdminBoard adminBoard = adminBoardService.fetchById(postId);
    }

    @Test
    public void fetchByRangeTest(){
        PageInfo pageInfo = adminBoardService.getPageList(currentPage);
        Map<String, Object> resultMap = pageInfo.parsePageRangeToMap();
    }

    @Test
    public void insertTest(){
        AdminBoard adminBoard = new AdminBoard();
        adminBoardService.save(adminBoard);
    }

    @Test
    public void modifyTest(){
        AdminBoard adminBoard = new AdminBoard();
        adminBoardService.update(adminBoard);
    }

    @Test
    public void deleteByIdTest(){
        adminBoardService.deleteById(postId);
    }
}
