package com.wcp.board.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdminBoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private AdminBoardRepository adminBoardRepository;

    public AdminBoardManager(AdminBoardRepository adminBoardRepository) {
        this.adminBoardRepository = adminBoardRepository;
    }

    public void save(AdminBoard adminBoard){
        adminBoardRepository.save(adminBoard);
    }

    public Optional<AdminBoard> fetchById(Long id){
        return adminBoardRepository.findById(id);
    }

    public List<AdminBoard> fetchAll(){
        return adminBoardRepository.findAll();
    }

    public void update(AdminBoard adminBoard){
        AdminBoard fetchAdminBoard = fetchById(adminBoard.getSeq()).get();
        fetchAdminBoard = adminBoard;
    }

    public void remove(AdminBoard adminBoard){
        adminBoardRepository.delete(adminBoard);
    }

}
