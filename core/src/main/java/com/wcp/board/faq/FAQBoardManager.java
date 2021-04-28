package com.wcp.board.faq;


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
public class FAQBoardManager implements BoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FAQBoardRepository faqBoardRepository;

    public void save(FAQBoard faqBoard){
        faqBoardRepository.save(faqBoard);
    }

    public List<FAQBoard> fetchByPage(int currentPage){
        Page<FAQBoard> faqBoards = faqBoardRepository
                .findAll(PageRequest
                        .of(currentPage - 1, 10, Sort.by(Sort.Direction.ASC, "seq")));
        return faqBoards.getContent();
    }

    public FAQBoard fetchById(Long id){ return faqBoardRepository.getOne(id); }

    public List<FAQBoard> fetchAll(){
        return faqBoardRepository.findAll();
    }

    public void update(FAQBoard faqBoard){
        FAQBoard fetchFAQBoard = fetchById(faqBoard.getId());
        fetchFAQBoard = faqBoard;
    }

    public void delete(FAQBoard faqBoard){
        faqBoardRepository.delete(faqBoard);
    }

    public void deleteById(Long id){ faqBoardRepository.deleteById(id); }

    public Long count(){ return faqBoardRepository.count(); }
}
