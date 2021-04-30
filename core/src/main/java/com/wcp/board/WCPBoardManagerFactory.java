package com.wcp.board;


import com.wcp.board.admin.AdminBoardManager;
import com.wcp.board.faq.FAQBoardManager;
import com.wcp.board.main.MainBoardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("WCPBoardManagerFactory")
public class WCPBoardManagerFactory implements BoardManagerFactory {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MainBoardManager mainBoardManager;

    @Autowired
    private FAQBoardManager faqBoardManager;

    @Autowired
    private AdminBoardManager adminBoardManager;


    @Override
    public BoardManager create(String boardType) {
        if(boardType == null || boardType.length()==0){ throw new IllegalArgumentException("Given boardType is null or empty."); }
        log.debug("boardType : {}",boardType);

        if ( boardType.equalsIgnoreCase("main") ) {
            return mainBoardManager;
        } else if ( boardType.equalsIgnoreCase("faq") ) {
            return faqBoardManager;
        } else if( boardType.equalsIgnoreCase("admin") ) {
            return adminBoardManager;
        }

        throw new UnsupportedOperationException("Could not distinguish given boardType : "+boardType);
    }
}
