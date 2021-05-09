package com.wcp.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void getPageList(PageInfo pageInfo){
        calcEndPage(pageInfo);
        log.info(pageInfo.toString());
        calcStartPage(pageInfo);
        log.info(pageInfo.toString());
    }

    private void calcEndPage(PageInfo pageInfo){
        int endPage = (int) ((Math.ceil(pageInfo.currentPage() / (double)pageInfo.pageCount()) * pageInfo.pageCount()));
        int tmpEndPage = (int)(Math.ceil(pageInfo.totalPostCount()/ (double) pageInfo.postCount()));

        if( endPage > tmpEndPage ){ endPage =  tmpEndPage; }

        pageInfo.endPage(endPage);
    }

    private void calcStartPage(PageInfo pageInfo){
        int startPage = (pageInfo.endPage() - pageInfo.pageCount()) + 1;
        startPage = startPage <= 0 ? 1 : startPage;
        pageInfo.startPage(startPage);

        if( startPage > pageInfo.endPage() ){ pageInfo.endPage(startPage); }
    }
}
