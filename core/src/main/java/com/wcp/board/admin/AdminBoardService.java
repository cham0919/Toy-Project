package com.wcp.board.admin;

import com.wcp.board.page.Page;
import com.wcp.board.page.PageInfo;
import com.wcp.board.page.PageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AdminBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminBoardManager adminBoardManager;

    @Autowired
    private PageService pageService;

    public void save(AdminBoard adminBoard){
        adminBoardManager.save(adminBoard);
    }

    public void delete(AdminBoard adminBoard){
        adminBoardManager.delete(adminBoard);
    }

    public void deleteById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        adminBoardManager.deleteById(Long.valueOf(id));
    }

    public Optional<AdminBoard> fetchById(String id){
        if(!StringUtils.isNumeric(id)){
            throw new IllegalArgumentException();
        }
        return adminBoardManager.fetchById(Long.valueOf(id));
    }

    public List<AdminBoard> fetchAll(){
        return adminBoardManager.fetchAll();
    }

    public void update(AdminBoard adminBoard) {
        adminBoardManager.update(adminBoard);
    }

    public Long count() {
        return adminBoardManager.count();
    }

    public List<AdminBoard> findByPage(int currentPage){
        return adminBoardManager.fetchByPage(currentPage);
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
