package com.wcp.page;

public interface PageCalculator {

    PageInfo fetchPageList(PageInfo pageInfo, PageCount pageCount);

    PageInfo fetchPageList(PageInfo pageInfo);

}
