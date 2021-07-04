package com.wcp.page;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BoardPageCalculatorTest {

    @InjectMocks
    private BoardPageCalculator pageCalculator;


    PageInfo pageInfoTest = PageInfo.of()
            .setCurrentPage(1)
            .setTotalPostCount(10);

    @Test
    public void fetchPageList_Success_NotNull(){
        PageInfo pageInfo = pageCalculator.fetchPageList(pageInfoTest, PageCount.CODING_TEST);

        assertNotNull(pageInfo);
    }

    @Test
    public void getPageList_Success_NotNull(){
        pageCalculator.fetchPageList(pageInfoTest);

        assertNotNull(pageInfoTest.getEndPage());
        assertNotNull(pageInfoTest.getStartPage());
    }

    @Test
    public void getPageList_NegativeNumber_EqualsOne(){
        PageInfo pageInfoTest = PageInfo.of()
                .setCurrentPage(-100)
                .setTotalPostCount(-10);
        pageCalculator.fetchPageList(pageInfoTest);

        assertEquals(pageInfoTest.getEndPage(), 1);
        assertEquals(pageInfoTest.getStartPage(), 1);
    }


}
