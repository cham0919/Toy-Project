package com.wcp.board;

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
public class BoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardRepository boardRepository;

    public void save(Board Board){
        boardRepository.save(Board);
    }

    public List<Board> fetchByPage(int currentPage){
        Page<Board> boardPage = boardRepository
                .findAll(PageRequest
                        .of(currentPage - 1, 10, Sort.by(Sort.Direction.ASC, "key")));
        return boardPage.getContent();
    }

    public Optional<Board> fetchById(Long id){
        return boardRepository.findById(id);
    }

    public List<Board> fetchAll(){
        return boardRepository.findAll();
    }

    public void update(Board board){
        Optional<Board> fetchBoard = fetchById(board.getKey());
        fetchBoard = Optional.of(board);
    }

    public void delete(Board board){
        boardRepository.delete(board);
    }

    public void deleteById(Long id){ boardRepository.deleteById(id); }

    public Long count(){ return boardRepository.count(); }

}
