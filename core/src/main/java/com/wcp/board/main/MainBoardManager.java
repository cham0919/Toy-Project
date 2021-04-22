package com.wcp.board.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MainBoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MainBoardRepository mainBoardRepository;

    public void save(MainBoard mainBoard){
        mainBoardRepository.save(mainBoard);
    }

    public Optional<MainBoard> fetchById(Long id){
        return mainBoardRepository.findById(id);
    }

    public List<MainBoard> findAll(){
        return mainBoardRepository.findAll();
    }

    public List<MainBoard> findByPage(int currentPage){
        Page<MainBoard> mainBoards = mainBoardRepository
                                        .findAll(PageRequest
                                        .of(currentPage - 1, 10, Sort.by(Sort.Direction.ASC, "seq")));
        return mainBoards.getContent();
    }

    public void update(MainBoard faqBoard){
        MainBoard fetchMainBoard = fetchById(faqBoard.getSeq()).get();
        fetchMainBoard = faqBoard;
    }

    public void remove(MainBoard mainBoard){
        mainBoardRepository.delete(mainBoard);
    }

    public Long count(){
        return mainBoardRepository.count();
    }
}
