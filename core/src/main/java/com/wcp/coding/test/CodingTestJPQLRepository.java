package com.wcp.coding.test;

import java.util.List;

public interface CodingTestJPQLRepository {

    List<CodingTestDto> fetchByCurrentPage(int currentPage, Long roomId, Long userKey);
}
