package com.wcp.board.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminBoardManager adminBoardManager;

    public void save(AdminBoard adminBoard){
        adminBoardManager.save(adminBoard);
    }

    public void remove(AdminBoard adminBoard){
        adminBoardManager.remove(adminBoard);
    }

    public Optional<AdminBoard> fetchById(String id){
        if(!StringUtils.isNumeric(id)){
            throw new IllegalArgumentException();
        }
        return adminBoardManager.fetchById(Long.valueOf(id));
    }

    public List<AdminBoard> fetchAll(){
        return adminBoardManager.fetchAll();
    }

    public void update(AdminBoard adminBoard) {
        adminBoardManager.update(adminBoard);
    }

}
