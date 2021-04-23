package com.wcp.board.main;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 게시물 수
    private static final int PAGE_POST_COUNT = 10; // 한 페이지에 존재하는 게시물 수


    @Autowired
    private MainBoardManager mainBoardManager;

    public void save(MainBoard mainBoard){
        mainBoardManager.save(mainBoard);
    }

    public void remove(MainBoard mainBoard){
        mainBoardManager.remove(mainBoard);
    }

    public Optional<MainBoard> fetchById(String id){
        if(!StringUtils.isNumeric(id)){
            throw new IllegalArgumentException();
        }
        return mainBoardManager.fetchById(Long.valueOf(id));
    }

    public List<MainBoard> fetchAll(){
        return mainBoardManager.findAll();
    }

    public void update(MainBoard mainBoard) {
        mainBoardManager.update(mainBoard);
    }

    public Long count(){
        return mainBoardManager.count();
    }

    public List<MainBoard> findByPage(int currentPage){
        return mainBoardManager.findByPage(currentPage);
    }

    public int[] getPageList(int currentPage){
        int[] pageList = new int[BLOCK_PAGE_NUM_COUNT];
        log.info("pageList size :: [{}]", BLOCK_PAGE_NUM_COUNT);

        // 총 게시글 수
        Double totalPostCount = Double.valueOf(this.count());
        log.info("totalPostCount :: [{}]", totalPostCount);

        int endPage = (int) ((Math.ceil(currentPage / (double) BLOCK_PAGE_NUM_COUNT) * BLOCK_PAGE_NUM_COUNT));
        log.info("endPage :: [{}]", endPage);

        // 마지막 페이지 계산
        int tmpEndPage = (int)(Math.ceil(totalPostCount/ (double) PAGE_POST_COUNT));
        log.info("tmpEndPage :: [{}]", tmpEndPage);

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        endPage = endPage > tmpEndPage ? tmpEndPage : endPage;

        log.info("blockLastPage :: [{}]", endPage);
        // 페이지 시작번호 조정

        currentPage = (endPage - BLOCK_PAGE_NUM_COUNT) + 1;
        currentPage = currentPage <= 0 ? 1 : currentPage;

        log.info("currentPage :: [{}]", currentPage);

        for (int val =currentPage, i =0;
             val <= endPage;
             val++, i++) {
            pageList[i] = val;
        }

        return pageList;
    }
}
