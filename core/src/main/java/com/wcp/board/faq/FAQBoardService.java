package com.wcp.board.faq;

import com.wcp.board.admin.AdminBoard;
import com.wcp.board.admin.AdminBoardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FAQBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FAQBoardManager faqBoardManager;

    public void savePost(FAQBoard faqBoard){
        faqBoardManager.savePost(faqBoard);
    }
}
