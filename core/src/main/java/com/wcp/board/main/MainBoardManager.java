package com.wcp.board.main;


import com.wcp.board.BoardManager;
import org.apache.commons.lang3.StringUtils;
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
public class MainBoardManager implements BoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MainBoardRepository mainBoardRepository;

    public void save(MainBoard mainBoard){
        mainBoardRepository.save(mainBoard);
    }

    public MainBoard fetchById(Long id){
        return mainBoardRepository.getOne(id);
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

    public void update(MainBoard mainBoard){
        MainBoard fetchMainBoard = fetchById(mainBoard.getId());
        fetchMainBoard = mainBoard;
    }

    public void delete(MainBoard mainBoard){
        mainBoardRepository.delete(mainBoard);
    }

    public void deleteById(Long id){
        mainBoardRepository.deleteById(id);
    }

    public Long count(){
        return mainBoardRepository.count();
    }
}
