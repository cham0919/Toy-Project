package com.wcp.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardCategory is a Querydsl query type for BoardCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardCategory extends EntityPathBase<BoardCategory> {

    private static final long serialVersionUID = 35744651L;

    public static final QBoardCategory boardCategory = new QBoardCategory("boardCategory");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath name = createString("name");

    public QBoardCategory(String variable) {
        super(BoardCategory.class, forVariable(variable));
    }

    public QBoardCategory(Path<? extends BoardCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardCategory(PathMetadata metadata) {
        super(BoardCategory.class, metadata);
    }

}

