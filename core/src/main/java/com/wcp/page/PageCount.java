package com.wcp.page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageCount {

    MAIN(5,10),
    CODING_ROOM(5, 10),
    CODING_TEST(5, 10);

    private int pageCount;
    private int postCount;


}
