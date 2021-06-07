package com.wcp.coding.room;

import com.wcp.common.base.CRUDService;
import com.wcp.common.base.PageService;

public interface CodingRoomService extends CRUDService<CodingRoom>, PageService<CodingRoom> {


    CodingRoom save(CodingRoom codingRoom, String userKey);

    CodingRoomDto fetchDtoById(String id);




}
