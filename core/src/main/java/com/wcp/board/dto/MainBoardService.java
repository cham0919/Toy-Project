package com.wcp.board.dto;


import com.wcp.board.entity.MainBoard;
import com.wcp.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class MainBoardService {

    private BoardRepository boardRepository;

    public MainBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void savePost(MainBoard mainBoard){
        boardRepository.save(mainBoard);
    }
}
