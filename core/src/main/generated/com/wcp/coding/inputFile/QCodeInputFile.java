package com.wcp.coding.inputFile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCodeInputFile is a Querydsl query type for CodeInputFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCodeInputFile extends EntityPathBase<CodeInputFile> {

    private static final long serialVersionUID = -1873527196L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCodeInputFile codeInputFile = new QCodeInputFile("codeInputFile");

    public final com.wcp.coding.test.QCodingTest codingTest;

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final StringPath givenName = createString("givenName");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath path = createString("path");

    public final DateTimePath<java.time.LocalDateTime> uploadAt = createDateTime("uploadAt", java.time.LocalDateTime.class);

    public QCodeInputFile(String variable) {
        this(CodeInputFile.class, forVariable(variable), INITS);
    }

    public QCodeInputFile(Path<? extends CodeInputFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCodeInputFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCodeInputFile(PathMetadata metadata, PathInits inits) {
        this(CodeInputFile.class, metadata, inits);
    }

    public QCodeInputFile(Class<? extends CodeInputFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.codingTest = inits.isInitialized("codingTest") ? new com.wcp.coding.test.QCodingTest(forProperty("codingTest"), inits.get("codingTest")) : null;
    }

}

