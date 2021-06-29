package com.wcp.commant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardCommant is a Querydsl query type for BoardCommant
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardCommant extends EntityPathBase<BoardCommant> {

    private static final long serialVersionUID = -575828545L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardCommant boardCommant = new QBoardCommant("boardCommant");

    public final com.wcp.board.QBoard board;

    public final StringPath content = createString("content");

    public final StringPath delete = createString("delete");

    public final NumberPath<Long> disLikeCnt = createNumber("disLikeCnt", Long.class);

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final NumberPath<Long> likeCnt = createNumber("likeCnt", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> uploadAt = createDateTime("uploadAt", java.time.LocalDateTime.class);

    public final com.wcp.user.QUser user;

    public QBoardCommant(String variable) {
        this(BoardCommant.class, forVariable(variable), INITS);
    }

    public QBoardCommant(Path<? extends BoardCommant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardCommant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardCommant(PathMetadata metadata, PathInits inits) {
        this(BoardCommant.class, metadata, inits);
    }

    public QBoardCommant(Class<? extends BoardCommant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.wcp.board.QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new com.wcp.user.QUser(forProperty("user")) : null;
    }

}

