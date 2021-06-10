package com.wcp.coding.test;

import com.wcp.common.base.CRUDService;
import com.wcp.common.base.PageService;

import java.util.List;

public interface CodingTestService extends CRUDService<CodingTest,CodingTestDto>, PageService<CodingTest, CodingTestDto> {

    void registerContent(MultiPartDto multiPartDto, String postId) throws Exception;

}
