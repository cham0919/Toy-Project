package com.wcp.board.main;


import com.wcp.board.faq.FAQBoard;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MainBoardManager mainBoardManager;

    public void save(MainBoard mainBoard){
        mainBoardManager.save(mainBoard);
    }

    public void remove(MainBoard mainBoard){
        mainBoardManager.remove(mainBoard);
    }

    public Optional<MainBoard> fetchById(String id){
        if(!StringUtils.isNumeric(id)){
            throw new IllegalArgumentException();
        }
        return mainBoardManager.fetchById(Long.valueOf(id));
    }

    public List<MainBoard> fetchAll(){
        return mainBoardManager.fetchAll();
    }

    public void update(MainBoard mainBoard) {
        mainBoardManager.update(mainBoard);
    }

}
