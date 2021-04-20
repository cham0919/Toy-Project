package com.wcp.board.faq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FAQBoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private FAQBoardRepository faqBoardRepository;

    public FAQBoardManager(FAQBoardRepository faqBoardRepository) {
        this.faqBoardRepository = faqBoardRepository;
    }

    public void save(FAQBoard adminBoard){
        faqBoardRepository.save(adminBoard);
    }

    public Optional<FAQBoard> fetchById(Long id){
        return faqBoardRepository.findById(id);
    }

    public List<FAQBoard> fetchAll(){
        return faqBoardRepository.findAll();
    }

    public void update(FAQBoard faqBoard){
        FAQBoard fetchFAQBoard = fetchById(faqBoard.getSeq()).get();
        fetchFAQBoard = faqBoard;
    }

    public void remove(FAQBoard faqBoard){
        faqBoardRepository.delete(faqBoard);
    }

}
