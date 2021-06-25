package com.wcp.coding.test;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wcp.coding.room.QCodingRoomDto;
import com.wcp.page.PageCount;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.wcp.coding.join.QCodingJoinUser.codingJoinUser;
import static com.wcp.coding.room.QCodingRoom.codingRoom;
import static com.wcp.coding.test.QCodingTest.codingTest;
import static com.wcp.coding.submit.QSubmitHistory.*;

@Repository
@RequiredArgsConstructor
public class CodingTestJPQLRepositoryImpl implements CodingTestJPQLRepository{

    private final Logger log = LoggerFactory.getLogger(CodingTestJPQLRepositoryImpl.class);
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CodingTestDto> fetchByCurrentPage(int currentPage, Long roomId, Long userKey) {
        return queryFactory
                .select(new QCodingTestDto(
                        codingTest.key,
                        codingTest.user.key,
                        codingTest.codingRoom.key,
                        codingTest.title,
                        codingTest.language,
                        codingTest.auth,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(submitHistory.count())
                                        .from(submitHistory)
                                        .where(codingTest.key.eq(submitHistory.codingTest.key))
                                        .where(submitHistory.user.key.eq(userKey))
                                        .where(submitHistory.status.eq("1")),
                                "isPass")
                        )
                )
                .from(codingTest)
                .where(codingTest.codingRoom.key.eq(roomId))
                .offset((currentPage - 1) * PageCount.CODING_TEST.getPostCount())
                .limit(PageCount.CODING_TEST.getPostCount())
                .fetch();
//        return null;
    }
}
