package com.wcp.board.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MainBoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MainBoardRepository mainBoardRepository;

    public void save(MainBoard mainBoard){
        mainBoardRepository.save(mainBoard);
    }

    public Optional<MainBoard> fetchById(Long id){
        return mainBoardRepository.findById(id);
    }

    public List<MainBoard> fetchAll(){
        return mainBoardRepository.findAll();
    }

    public void update(MainBoard faqBoard){
        MainBoard fetchMainBoard = fetchById(faqBoard.getSeq()).get();
        fetchMainBoard = faqBoard;
    }

    public void remove(MainBoard mainBoard){
        mainBoardRepository.delete(mainBoard);
    }
}
