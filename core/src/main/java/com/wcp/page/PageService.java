package com.wcp.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public PageInfo getPageList(PageInfo pageInfo){
        pageInfo = calcEndPage(pageInfo);
        log.info(pageInfo.toString());
        pageInfo = calcStartPage(pageInfo);
        log.info(pageInfo.toString());
        return pageInfo;
    }

    private PageInfo calcEndPage(PageInfo pageInfo){
        int endPage = (int) ((Math.ceil(pageInfo.getCurrentPage() / (double)pageInfo.getPageCount()) * pageInfo.getPageCount()));
        int tmpEndPage = (int)(Math.ceil(pageInfo.getTotalPostCount()/ (double) pageInfo.getPostCount()));

        if( endPage > tmpEndPage ){ endPage =  tmpEndPage; }

        return pageInfo.setEndPage(endPage);
    }

    private PageInfo calcStartPage(PageInfo pageInfo){
        int startPage = (pageInfo.getEndPage() - pageInfo.getPageCount()) + 1;
        startPage = startPage <= 0 ? 1 : startPage;
        pageInfo.setStartPage(startPage);

        if( startPage > pageInfo.getEndPage() ){ pageInfo.setEndPage(startPage); }
        return pageInfo;
    }
}
