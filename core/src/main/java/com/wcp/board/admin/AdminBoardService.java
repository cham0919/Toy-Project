package com.wcp.board.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminBoardManager adminBoardManager;

    public void savePost(AdminBoard adminBoard){
        adminBoardManager.save(adminBoard);
    }

    public void deletePost(AdminBoard adminBoard){
        adminBoardManager.remove(adminBoard);
    }

    public Optional<AdminBoard> fetchById(String postId){
        if(!StringUtils.isNumeric(postId)){
            throw new IllegalArgumentException();
        }
        return adminBoardManager.fetchById(Long.valueOf(postId));
    }
}
