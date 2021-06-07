package com.wcp.coding.test;

import com.wcp.common.base.CRUDService;
import com.wcp.common.base.PageService;

import java.util.List;

public interface CodingTestService extends CRUDService<CodingTest>, PageService<CodingTest> {


    CodingTestDto fetchDtoById(String id);

    void registerContent(MultiPartDto multiPartDto, String postId) throws Exception;

    List<CodingTest> fetchByPage(String currentPage);

    List<CodingTest> fetchByPage(int currentPage);


}
