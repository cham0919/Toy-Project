package com.wcp.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -712196217L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.wcp.category.QBoardCategory boardCategory;

    public final ListPath<com.wcp.commant.BoardCommant, com.wcp.commant.QBoardCommant> boardCommants = this.<com.wcp.commant.BoardCommant, com.wcp.commant.QBoardCommant>createList("boardCommants", com.wcp.commant.BoardCommant.class, com.wcp.commant.QBoardCommant.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final StringPath delete = createString("delete");

    public final NumberPath<Long> disLikeCnt = createNumber("disLikeCnt", Long.class);

    public final NumberPath<Long> hit = createNumber("hit", Long.class);

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final NumberPath<Long> likeCnt = createNumber("likeCnt", Long.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> uploadAt = createDateTime("uploadAt", java.time.LocalDateTime.class);

    public final com.wcp.user.QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardCategory = inits.isInitialized("boardCategory") ? new com.wcp.category.QBoardCategory(forProperty("boardCategory")) : null;
        this.user = inits.isInitialized("user") ? new com.wcp.user.QUser(forProperty("user")) : null;
    }

}

