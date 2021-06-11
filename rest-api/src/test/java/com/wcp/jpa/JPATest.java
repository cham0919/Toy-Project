package com.wcp.jpa;

import com.wcp.boot.Boot;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomRepository;
import com.wcp.user.User;
import com.wcp.user.UserRepository;
import com.wcp.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.IOException;

public class JPATest extends Boot {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CodingRoomRepository codingRoomRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void UserSelectTest(){
            User user = userRepository.getOne(1L);
    }
}
