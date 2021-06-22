package com.wcp.coding.join;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCodingJoinUser is a Querydsl query type for CodingJoinUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCodingJoinUser extends EntityPathBase<CodingJoinUser> {

    private static final long serialVersionUID = -1083417194L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCodingJoinUser codingJoinUser = new QCodingJoinUser("codingJoinUser");

    public final com.wcp.coding.room.QCodingRoom codingRoom;

    public final DateTimePath<java.time.LocalDateTime> joinAt = createDateTime("joinAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath role = createString("role");

    public final StringPath status = createString("status");

    public final com.wcp.user.QUser user;

    public QCodingJoinUser(String variable) {
        this(CodingJoinUser.class, forVariable(variable), INITS);
    }

    public QCodingJoinUser(Path<? extends CodingJoinUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCodingJoinUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCodingJoinUser(PathMetadata metadata, PathInits inits) {
        this(CodingJoinUser.class, metadata, inits);
    }

    public QCodingJoinUser(Class<? extends CodingJoinUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.codingRoom = inits.isInitialized("codingRoom") ? new com.wcp.coding.room.QCodingRoom(forProperty("codingRoom"), inits.get("codingRoom")) : null;
        this.user = inits.isInitialized("user") ? new com.wcp.user.QUser(forProperty("user")) : null;
    }

}

