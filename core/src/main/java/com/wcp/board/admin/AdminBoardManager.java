package com.wcp.board.admin;


import com.wcp.board.BoardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminBoardManager implements BoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminBoardRepository adminBoardRepository;

    public void save(AdminBoard adminBoard){
        adminBoardRepository.save(adminBoard);
    }

    public List<AdminBoard> fetchByPage(int currentPage){
        Page<AdminBoard> faqBoards = adminBoardRepository
                .findAll(PageRequest
                        .of(currentPage - 1, 10, Sort.by(Sort.Direction.ASC, "seq")));
        return faqBoards.getContent();
    }

    public AdminBoard fetchById(Long id){
        return adminBoardRepository.getOne(id);
    }

    public List<AdminBoard> fetchAll(){
        return adminBoardRepository.findAll();
    }

    public void update(AdminBoard adminBoard){
        AdminBoard fetchAdminBoard = fetchById(adminBoard.getSeq());
        fetchAdminBoard = adminBoard;
    }

    public void delete(AdminBoard adminBoard){
        adminBoardRepository.delete(adminBoard);
    }

    public void deleteById(Long id){ adminBoardRepository.deleteById(id); }

    public Long count(){ return adminBoardRepository.count(); }
}
