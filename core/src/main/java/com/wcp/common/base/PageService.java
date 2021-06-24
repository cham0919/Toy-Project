package com.wcp.common.base;

import com.wcp.page.PageInfo;

import java.util.List;

public interface PageService<T, D> {


    List<D> fetchByPage(int currentPage);
    List<D> fetchByPage(String currentPage);
    PageInfo fetchPageList(String currentPage);


}
