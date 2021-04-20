package com.wcp.board.faq;

import com.wcp.board.admin.AdminBoard;
import com.wcp.board.admin.AdminBoardManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FAQBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FAQBoardManager faqBoardManager;

    public void save(FAQBoard faqBoard){
        faqBoardManager.save(faqBoard);
    }

    public void remove(FAQBoard faqBoard){
        faqBoardManager.remove(faqBoard);
    }

    public Optional<FAQBoard> fetchById(String id){
        if(!StringUtils.isNumeric(id)){
            throw new IllegalArgumentException();
        }
        return faqBoardManager.fetchById(Long.valueOf(id));
    }

    public List<FAQBoard> fetchAll(){
        return faqBoardManager.fetchAll();
    }

    public void update(FAQBoard faqBoard) {
        faqBoardManager.update(faqBoard);
    }
}
