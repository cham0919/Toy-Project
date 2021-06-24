package com.wcp.coding.room;

import java.util.List;

public interface CodingRoomJPQLRepository {

    List<CodingRoomDto> fetchByCurrentPage(int currentPage);


}
