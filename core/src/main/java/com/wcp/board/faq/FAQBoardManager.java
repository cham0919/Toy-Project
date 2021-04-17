package com.wcp.board.faq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FAQBoardManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private FAQBoardRepository faqBoardRepository;

    public void savePost(FAQBoard faqBoard){
        faqBoardRepository.save(faqBoard);
    }

    public FAQBoardManager(FAQBoardRepository faqBoardRepository) {
        this.faqBoardRepository = faqBoardRepository;
    }

}
