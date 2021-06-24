package com.wcp.coding.room;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.wcp.coding.room.QCodingRoomDto is a Querydsl Projection type for CodingRoomDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QCodingRoomDto extends ConstructorExpression<CodingRoomDto> {

    private static final long serialVersionUID = 342227796L;

    public QCodingRoomDto(com.querydsl.core.types.Expression<Long> key, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<Long> maxUser, com.querydsl.core.types.Expression<Integer> joinUsersCount, com.querydsl.core.types.Expression<Integer> codingTestCount) {
        super(CodingRoomDto.class, new Class<?>[]{long.class, String.class, long.class, int.class, int.class}, key, title, maxUser, joinUsersCount, codingTestCount);
    }

}

