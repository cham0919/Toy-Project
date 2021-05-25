package com.wcp.coding.board;

import com.wcp.page.Page;
import com.wcp.page.PageInfo;
import com.wcp.page.PageService;
import com.wcp.user.User;
import com.wcp.user.UserPersistenceManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodingBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodingBoardPersistenceManager codingBoardPersistenceManager;
    private final PageService pageService;


    public CodingBoard save(CodingBoard codingBoard, String userId){
        return codingBoardPersistenceManager.save(codingBoard, userId);
    }

    public CodingBoard save(CodingBoard codingBoard){
        return codingBoardPersistenceManager.save(codingBoard);
    }

    public List<CodingBoard> fetchByPage(String currentPage){
        return codingBoardPersistenceManager.fetchByPage(Integer.valueOf(currentPage));
    }

    public CodingBoard fetchById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        return codingBoardPersistenceManager.fetchById(Long.valueOf(id)).get();
    }

    public List<CodingBoard> fetchAll(){
        return codingBoardPersistenceManager.fetchAll();
    }

    public CodingBoard update(CodingBoard codingBoard){
        return codingBoardPersistenceManager.update(codingBoard);
    }

    public CodingBoard delete(CodingBoard codingBoard){
        return codingBoardPersistenceManager.delete(codingBoard);
    }

    public void deleteById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
       codingBoardPersistenceManager.deleteById(Long.valueOf(id));
    }

    public Long count(){
        return codingBoardPersistenceManager.count();
    }

    public PageInfo getPageList(String currentPage){
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return getPageList(Integer.valueOf(currentPage));
    }

    public PageInfo getPageList(int currentPage){
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(currentPage)
                .setPageCount(Page.MAIN_PAGE_COUNT)
                .setPostCount(Page.MAIN_POST_COUNT)
                .setTotalPostCount(this.count());
        log.debug(pageInfo.toString());
        return pageService.getPageList(pageInfo);
    }
}
