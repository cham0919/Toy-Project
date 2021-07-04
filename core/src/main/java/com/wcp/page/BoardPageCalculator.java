package com.wcp.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BoardPageCalculator implements PageCalculator {

    private final Logger log = LoggerFactory.getLogger(BoardPageCalculator.class);


    @Override
    public PageInfo fetchPageList(PageInfo pageInfo, PageCount pageCount){
        pageInfo.setPageCount(pageCount.getPageCount())
                .setPostCount(pageCount.getPostCount());
        log.debug(pageInfo.toString());
        return fetchPageList(pageInfo);
    }

    @Override
    public PageInfo fetchPageList(PageInfo pageInfo){
        calcEndPage(pageInfo);
        calcStartPage(pageInfo);
        calcTotalEndPage(pageInfo);
        log.debug(pageInfo.toString());
        return pageInfo;
    }

    private void calcTotalEndPage(PageInfo pageInfo) {
        int totalEndPage = (int)Math.ceil((double) pageInfo.getTotalPostCount()/pageInfo.getPostCount());
        pageInfo.setTotalEndPage(totalEndPage);
    }

    private void calcEndPage(PageInfo pageInfo){
        int endPage = (int) ((Math.ceil(pageInfo.getCurrentPage() / (double)pageInfo.getPageCount()) * pageInfo.getPageCount()));
        int tmpEndPage = (int)(Math.ceil(pageInfo.getTotalPostCount()/ (double) pageInfo.getPostCount()));

        if( endPage > tmpEndPage ){ endPage =  tmpEndPage; }

        pageInfo.setEndPage(endPage);
    }

    private void calcStartPage(PageInfo pageInfo){
        int startPage = (pageInfo.getEndPage() - pageInfo.getPageCount()) + 1;
        startPage = startPage <= 0 ? 1 : startPage;
        pageInfo.setStartPage(startPage);

        if( startPage > pageInfo.getEndPage() ){ pageInfo.setEndPage(startPage); }
    }
}
