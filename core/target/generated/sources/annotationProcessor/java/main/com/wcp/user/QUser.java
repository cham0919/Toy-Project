package com.wcp.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -879862729L;

    public static final QUser user = new QUser("user");

    public final ListPath<com.wcp.commant.BoardCommant, com.wcp.commant.QBoardCommant> boardCommants = this.<com.wcp.commant.BoardCommant, com.wcp.commant.QBoardCommant>createList("boardCommants", com.wcp.commant.BoardCommant.class, com.wcp.commant.QBoardCommant.class, PathInits.DIRECT2);

    public final ListPath<com.wcp.like.BoardLike, com.wcp.like.QBoardLike> boardLikes = this.<com.wcp.like.BoardLike, com.wcp.like.QBoardLike>createList("boardLikes", com.wcp.like.BoardLike.class, com.wcp.like.QBoardLike.class, PathInits.DIRECT2);

    public final ListPath<com.wcp.board.Board, com.wcp.board.QBoard> boards = this.<com.wcp.board.Board, com.wcp.board.QBoard>createList("boards", com.wcp.board.Board.class, com.wcp.board.QBoard.class, PathInits.DIRECT2);

    public final ListPath<com.wcp.coding.room.CodingRoom, com.wcp.coding.room.QCodingRoom> codingRooms = this.<com.wcp.coding.room.CodingRoom, com.wcp.coding.room.QCodingRoom>createList("codingRooms", com.wcp.coding.room.CodingRoom.class, com.wcp.coding.room.QCodingRoom.class, PathInits.DIRECT2);

    public final ListPath<com.wcp.coding.test.CodingTest, com.wcp.coding.test.QCodingTest> codingTests = this.<com.wcp.coding.test.CodingTest, com.wcp.coding.test.QCodingTest>createList("codingTests", com.wcp.coding.test.CodingTest.class, com.wcp.coding.test.QCodingTest.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final DateTimePath<java.time.LocalDateTime> registerAt = createDateTime("registerAt", java.time.LocalDateTime.class);

    public final EnumPath<com.wcp.security.Role> role = createEnum("role", com.wcp.security.Role.class);

    public final EnumPath<UserSataus> status = createEnum("status", UserSataus.class);

    public final ListPath<com.wcp.coding.submit.SubmitHistory, com.wcp.coding.submit.QSubmitHistory> submitHistories = this.<com.wcp.coding.submit.SubmitHistory, com.wcp.coding.submit.QSubmitHistory>createList("submitHistories", com.wcp.coding.submit.SubmitHistory.class, com.wcp.coding.submit.QSubmitHistory.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

