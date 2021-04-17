package com.wcp.board.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MainBoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MainBoardRepository mainBoardRepository;

    public void savePost(MainBoard mainBoard){
        mainBoardRepository.save(mainBoard);
    }

    public MainBoardManager(MainBoardRepository mainBoardRepository) {
        this.mainBoardRepository = mainBoardRepository;
    }

}
