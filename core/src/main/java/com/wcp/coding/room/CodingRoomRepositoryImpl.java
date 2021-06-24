package com.wcp.coding.room;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wcp.page.PageCount;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.wcp.coding.join.QCodingJoinUser.codingJoinUser;
import static com.wcp.coding.room.QCodingRoom.codingRoom;
import static com.wcp.coding.test.QCodingTest.codingTest;

@Repository
@RequiredArgsConstructor
public class CodingRoomRepositoryImpl implements CodingRoomRepositoryCustom{

    private final Logger log = LoggerFactory.getLogger(CodingRoomRepositoryImpl.class);
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CodingRoomDto> fetchByCurrentPage(int currentPage) {
        return queryFactory
                .select(new QCodingRoomDto(
                                codingRoom.key,
                                codingRoom.title,
                                codingRoom.maxUser,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(codingJoinUser.count())
                                                .from(codingJoinUser)
                                                .where(codingRoom.key.eq(codingJoinUser.codingRoom.key)),
                                        "joinUsersCount"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(codingTest.count())
                                                .from(codingTest)
                                                .where(codingRoom.key.eq(codingTest.codingRoom.key)),
                                        "codingTestCount")
                        )
                )
                .from(codingRoom)
                .where(codingRoom.secret.eq(false))
                .offset((currentPage - 1) * PageCount.CODING_ROOM.getPostCount())
                .limit(PageCount.CODING_ROOM.getPostCount() * currentPage)
                .fetch();
    }
}
