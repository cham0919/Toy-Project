package com.wcp.board.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.TreeMap;

@Accessors(fluent = true)
@Getter
@Setter
@ToString
public class PageInfo implements Page{

    private int startPage;
    private int endPage;
    private int currentPage;
    private double totalPostCount;

    private int pageCount;
    private int postCount;

    private PageInfo() {}

    public static PageInfo of(){return new PageInfo();}

    public Map<String, Object> parsePageRangeToMap(){
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        return map;
    }

}
