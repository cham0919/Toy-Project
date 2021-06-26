package com.wcp.coding.test;

import com.wcp.common.base.CRUDService;
import com.wcp.common.base.PageService;
import com.wcp.page.PageInfo;

import java.util.List;

public interface CodingTestService extends CRUDService<CodingTest,CodingTestDto>{

    void registerContent(MultiPartDto multiPartDto) throws Throwable;

    List<CodingTestDto> fetchByCurrentPage(String currentPage, String roomId, String userKey);

    PageInfo fetchPageList(String currentPage, String roomId);

}
