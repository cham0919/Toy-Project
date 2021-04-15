package com.wcp.board.main;


import org.springframework.stereotype.Service;

@Service
public class MainBoardService {

    private BoardRepository boardRepository;

    public void savePost(MainBoard mainBoard){
        boardRepository.save(mainBoard);
    }

    public MainBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
}
