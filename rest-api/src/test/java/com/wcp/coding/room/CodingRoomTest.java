package com.wcp.coding.room;

import com.wcp.boot.Boot;
import com.wcp.env.Config;
import com.wcp.page.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class CodingRoomTest extends Boot {

    @Autowired
    private CodingRoomService codingRoomService;

    @Test
    public void fetchByRangeTest(){
        System.out.println(Config.getProperty("test.key"));
    }
}
