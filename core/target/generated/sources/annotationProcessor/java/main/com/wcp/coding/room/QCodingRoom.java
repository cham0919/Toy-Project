package com.wcp.coding.room;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCodingRoom is a Querydsl query type for CodingRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCodingRoom extends EntityPathBase<CodingRoom> {

    private static final long serialVersionUID = -35454325L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCodingRoom codingRoom = new QCodingRoom("codingRoom");

    public final ListPath<com.wcp.coding.join.CodingJoinUser, com.wcp.coding.join.QCodingJoinUser> codingJoinUsers = this.<com.wcp.coding.join.CodingJoinUser, com.wcp.coding.join.QCodingJoinUser>createList("codingJoinUsers", com.wcp.coding.join.CodingJoinUser.class, com.wcp.coding.join.QCodingJoinUser.class, PathInits.DIRECT2);

    public final ListPath<com.wcp.coding.test.CodingTest, com.wcp.coding.test.QCodingTest> codingTests = this.<com.wcp.coding.test.CodingTest, com.wcp.coding.test.QCodingTest>createList("codingTests", com.wcp.coding.test.CodingTest.class, com.wcp.coding.test.QCodingTest.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath intro = createString("intro");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final NumberPath<Long> maxUser = createNumber("maxUser", Long.class);

    public final StringPath password = createString("password");

    public final StringPath ramdomKey = createString("ramdomKey");

    public final BooleanPath secret = createBoolean("secret");

    public final StringPath title = createString("title");

    public final com.wcp.user.QUser user;

    public QCodingRoom(String variable) {
        this(CodingRoom.class, forVariable(variable), INITS);
    }

    public QCodingRoom(Path<? extends CodingRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCodingRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCodingRoom(PathMetadata metadata, PathInits inits) {
        this(CodingRoom.class, metadata, inits);
    }

    public QCodingRoom(Class<? extends CodingRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.wcp.user.QUser(forProperty("user")) : null;
    }

}

