package com.wcp.coding.test;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.wcp.coding.test.QCodingTestDto is a Querydsl Projection type for CodingTestDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QCodingTestDto extends ConstructorExpression<CodingTestDto> {

    private static final long serialVersionUID = -886702156L;

    public QCodingTestDto(com.querydsl.core.types.Expression<Long> key, com.querydsl.core.types.Expression<Long> userKey, com.querydsl.core.types.Expression<Long> postId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> language, com.querydsl.core.types.Expression<String> auth, com.querydsl.core.types.Expression<Long> isPass) {
        super(CodingTestDto.class, new Class<?>[]{long.class, long.class, long.class, String.class, String.class, String.class, long.class}, key, userKey, postId, title, language, auth, isPass);
    }

}

