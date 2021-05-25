package com.wcp.coding.content;

import com.wcp.page.Page;
import com.wcp.page.PageInfo;
import com.wcp.page.PageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodingContentService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodingContentPersistenceManager codingContentPersistenceManager;
    private final PageService pageService;


    public CodingContent save(CodingContent codingContent){
        return codingContentPersistenceManager.save(codingContent);
    }

    public List<CodingContent> fetchByPage(String currentPage){
        return codingContentPersistenceManager.fetchByPage(Integer.valueOf(currentPage));
    }

    public CodingContent fetchById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        return codingContentPersistenceManager.fetchById(Long.valueOf(id)).get();
    }

    public List<CodingContent> fetchAll(){
        return codingContentPersistenceManager.fetchAll();
    }

    public CodingContent update(CodingContent codingContent){
        return codingContentPersistenceManager.update(codingContent);
    }

    public CodingContent delete(CodingContent codingContent){
        return codingContentPersistenceManager.delete(codingContent);
    }

    public void deleteById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
       codingContentPersistenceManager.deleteById(Long.valueOf(id));
    }

    public Long count(){
        return codingContentPersistenceManager.count();
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
