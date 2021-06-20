package com.wcp.coding.test;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCodingTest is a Querydsl query type for CodingTest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCodingTest extends EntityPathBase<CodingTest> {

    private static final long serialVersionUID = -824681941L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCodingTest codingTest = new QCodingTest("codingTest");

    public final StringPath auth = createString("auth");

    public final com.wcp.coding.inputFile.QCodeInputFile codeInputFile;

    public final com.wcp.coding.room.QCodingRoom codingRoom;

    public final StringPath content = createString("content");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath language = createString("language");

    public final DateTimePath<java.time.LocalDateTime> submitAt = createDateTime("submitAt", java.time.LocalDateTime.class);

    public final ListPath<com.wcp.coding.submit.SubmitHistory, com.wcp.coding.submit.QSubmitHistory> sumitHistories = this.<com.wcp.coding.submit.SubmitHistory, com.wcp.coding.submit.QSubmitHistory>createList("sumitHistories", com.wcp.coding.submit.SubmitHistory.class, com.wcp.coding.submit.QSubmitHistory.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final com.wcp.user.QUser user;

    public QCodingTest(String variable) {
        this(CodingTest.class, forVariable(variable), INITS);
    }

    public QCodingTest(Path<? extends CodingTest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCodingTest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCodingTest(PathMetadata metadata, PathInits inits) {
        this(CodingTest.class, metadata, inits);
    }

    public QCodingTest(Class<? extends CodingTest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.codeInputFile = inits.isInitialized("codeInputFile") ? new com.wcp.coding.inputFile.QCodeInputFile(forProperty("codeInputFile"), inits.get("codeInputFile")) : null;
        this.codingRoom = inits.isInitialized("codingRoom") ? new com.wcp.coding.room.QCodingRoom(forProperty("codingRoom"), inits.get("codingRoom")) : null;
        this.user = inits.isInitialized("user") ? new com.wcp.user.QUser(forProperty("user")) : null;
    }

}

