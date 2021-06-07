package com.wcp.common.base;

import com.wcp.page.PageInfo;

import java.util.List;

public interface PageService<T> {


    List<T> fetchByPage(int currentPage);
    List<T> fetchByPage(String currentPage);
    PageInfo fetchPageList(String currentPage);


}
