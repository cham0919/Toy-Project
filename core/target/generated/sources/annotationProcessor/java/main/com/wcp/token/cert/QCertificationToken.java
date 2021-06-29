package com.wcp.token.cert;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCertificationToken is a Querydsl query type for CertificationToken
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCertificationToken extends EntityPathBase<CertificationToken> {

    private static final long serialVersionUID = 1245312541L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCertificationToken certificationToken = new QCertificationToken("certificationToken");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath expired = createString("expired");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath token = createString("token");

    public final StringPath type = createString("type");

    public final DateTimePath<java.time.LocalDateTime> usedAt = createDateTime("usedAt", java.time.LocalDateTime.class);

    public final com.wcp.user.QUser user;

    public QCertificationToken(String variable) {
        this(CertificationToken.class, forVariable(variable), INITS);
    }

    public QCertificationToken(Path<? extends CertificationToken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCertificationToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCertificationToken(PathMetadata metadata, PathInits inits) {
        this(CertificationToken.class, metadata, inits);
    }

    public QCertificationToken(Class<? extends CertificationToken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.wcp.user.QUser(forProperty("user")) : null;
    }

}

