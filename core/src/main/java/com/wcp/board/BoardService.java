package com.wcp.board;

import com.wcp.page.PageCalculator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardService {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private BoardPersistenceManager boardPersistenceManager;

    private PageCalculator pageCalculator;


    /*
    public void save(MainBoard mainBoard){
        mainBoardManager.save(mainBoard);
    }

    public void delete(MainBoard mainBoard){
        mainBoardManager.delete(mainBoard);
    }

    public void deleteById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        mainBoardManager.deleteById(Long.valueOf(id));
    }

    public MainBoard fetchById(String id){
        if(!StringUtils.isNumeric(id)){
            throw new IllegalArgumentException();
        }
        return mainBoardManager.fetchById(Long.valueOf(id));
    }

    public List<MainBoard> fetchAll(){
        return mainBoardManager.findAll();
    }

    public void update(MainBoard mainBoard) {
        mainBoardManager.update(mainBoard);
    }

    public Long count(){
        return mainBoardManager.count();
    }

    public List<MainBoard> findByPage(int currentPage){
        return mainBoardManager.findByPage(currentPage);
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
    }*/
}
