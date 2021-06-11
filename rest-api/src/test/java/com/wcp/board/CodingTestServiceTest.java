package com.wcp.board;

import com.wcp.Boot;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.test.CodingTest;
import org.junit.jupiter.api.Test;

public class CodingTestServiceTest extends Boot {



    @Test
    public void saveTest(){
        Long key = 1L;
        CodingRoom codingRoom = new CodingRoom().setKey(key);

        CodingTest codingTest = new CodingTest();
        codingTest.setCodingRoom(codingRoom);


    }

}
