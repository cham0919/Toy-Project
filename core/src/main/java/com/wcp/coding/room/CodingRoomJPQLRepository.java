package com.wcp.coding.room;

import java.util.List;

public interface CodingRoomRepositoryCustom {

    List<CodingRoomDto> fetchByCurrentPage(int currentPage);


}
