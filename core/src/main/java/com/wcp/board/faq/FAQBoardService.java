package com.wcp.board.faq;

import com.wcp.board.page.Page;
import com.wcp.board.page.PageInfo;
import com.wcp.board.page.PageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FAQBoardManager faqBoardManager;

    @Autowired
    private PageService pageService;

    public void save(FAQBoard faqBoard){
        faqBoardManager.save(faqBoard);
    }

    public void delete(FAQBoard faqBoard){
        faqBoardManager.delete(faqBoard);
    }

    public void deleteById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        faqBoardManager.deleteById(Long.valueOf(id));
    }


    public FAQBoard fetchById(String id){
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

    public Long count() {
        return faqBoardManager.count();
    }

    public List<FAQBoard> findByPage(int currentPage){
        return faqBoardManager.fetchByPage(currentPage);
    }

    public PageInfo getPageList(String currentPage){
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return getPageList(Integer.valueOf(currentPage));
    }

    public PageInfo getPageList(int currentPage){
        PageInfo pageInfo = PageInfo.of()
                .currentPage(currentPage)
                .pageCount(Page.MAIN_PAGE_COUNT)
                .postCount(Page.MAIN_POST_COUNT)
                .totalPostCount(this.count());
        log.debug(pageInfo.toString());
        pageService.getPageList(pageInfo);
        return pageInfo;
    }
}
