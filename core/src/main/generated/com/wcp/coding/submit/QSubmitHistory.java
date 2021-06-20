package com.wcp.coding.submit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubmitHistory is a Querydsl query type for SubmitHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubmitHistory extends EntityPathBase<SubmitHistory> {

    private static final long serialVersionUID = -1305097805L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubmitHistory submitHistory = new QSubmitHistory("submitHistory");

    public final StringPath code = createString("code");

    public final com.wcp.coding.test.QCodingTest codingTest;

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath language = createString("language");

    public final StringPath runTime = createString("runTime");

    public final StringPath status = createString("status");

    public final DateTimePath<java.time.LocalDateTime> submitAt = createDateTime("submitAt", java.time.LocalDateTime.class);

    public final com.wcp.user.QUser user;

    public QSubmitHistory(String variable) {
        this(SubmitHistory.class, forVariable(variable), INITS);
    }

    public QSubmitHistory(Path<? extends SubmitHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubmitHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubmitHistory(PathMetadata metadata, PathInits inits) {
        this(SubmitHistory.class, metadata, inits);
    }

    public QSubmitHistory(Class<? extends SubmitHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.codingTest = inits.isInitialized("codingTest") ? new com.wcp.coding.test.QCodingTest(forProperty("codingTest"), inits.get("codingTest")) : null;
        this.user = inits.isInitialized("user") ? new com.wcp.user.QUser(forProperty("user")) : null;
    }

}

