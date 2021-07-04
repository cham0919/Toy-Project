package com.wcp.coding.room;

import java.util.List;

public interface CodingRoomQueryDSLRepository {

    List<CodingRoomDto> fetchByCurrentPage(int currentPage);

    CodingRoom fetchByIdJoinUser(Long id);

    List<CodingRoom> fetchAllPublicRoom();


}
