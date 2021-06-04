package com.wcp;

public class WCPTable {
    public class UserTable {
        public static final String TABLE_NAME = "wcp_user";
        public static final String PK = "user_key";
        public static final String ID = "user_id";
        public static final String PW = "password";
        public static final String NAME = "name";
        public static final String NICKNAME = "nickname";
        public static final String REGISTER_AT = "register_at";
        public static final String PHONE = "phone";
        public static final String STATUS = "status";
        public static final String ROLE = "role";
    }

    public class CertificationTokenTable {
        public static final String TABLE_NAME = "wcp_certification_token";
        public static final String PK = "ctt_key";
        public static final String TOKEN = "token";
        public static final String TYPE = "type";
        public static final String CREATED_AT = "created_at";
        public static final String USED_AT = "used_at";
        public static final String EXPIRED = "expired";
    }

    public class BoardLikeTable {
        public static final String TABLE_NAME = "wcp_board_like";
        public static final String PK = "lik_key";
        public static final String TARGET_ID = "target_id";
        public static final String TARGET_TYPE = "target_type";
        public static final String TYPE = "type";
        public static final String LIKE_AT = "like_at";
    }

    public class BoardTable {
        public static final String TABLE_NAME = "wcp_board";
        public static final String PK = "board_key";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String UPLOAD_AT = "upload_at";
        public static final String UPDATED_AT = "updated_at";
        public static final String HIT = "hit";
        public static final String DELETE = "del";
        public static final String LIKE = "like_cnt";
        public static final String DISLIKE = "dislike_cnt";
    }

    public class BoardCategoryTable {
        public static final String TABLE_NAME = "wcp_board_category";
        public static final String PK = "bca_key";
        public static final String NAME = "name";
    }

    public class BoardCommantTable {
        public static final String TABLE_NAME = "wcp_board_commant";
        public static final String PK = "cmt_key";
        public static final String CONTENT = "content";
        public static final String UPLOAD_AT = "upload_at";
        public static final String UPDATED_AT = "updated_at";
        public static final String LIKE = "like_cnt";
        public static final String DISLIKE = "dislike_cnt";
        public static final String DELETE = "del";
    }

    public class CodingRoomTable {
        public static final String TABLE_NAME = "wcp_coding_room";
        public static final String PK = "cr_key";
        public static final String TITLE = "title";
        public static final String INTRO = "intro";
        public static final String CREATED_AT = "created_at";
        public static final String PASSWORD = "password";
        public static final String MAX_USER = "max_user";
        public static final String RAMDOM_KEY = "ramdom_key";

    }

    public class CodingJoinUserTable {
        public static final String TABLE_NAME = "wcp_coding_join_user";
        public static final String PK = "cju_key";
        public static final String JOIN_AT = "join_at";
        public static final String STATUS = "status";
        public static final String ROLE = "role";
    }

    public class CodingTestTable {
        public static final String TABLE_NAME = "wcp_coding_test";
        public static final String PK = "cct_key";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String SUBMIT_AT = "submit_at";
        public static final String AUTH = "auth";
        public static final String LANGUAGE = "language";
    }

    public class SubmitHistoryTable {
        public static final String TABLE_NAME = "wcp_submit_history";
        public static final String PK = "sbh_key";
        public static final String STATUS = "status";
        public static final String LANGUAGE = "language";
        public static final String CODE = "code";
        public static final String SUBMIT_AT = "submit_at";
        public static final String RUN_TIME = "run_time";
    }

    public class CodeInputFileTable {
        public static final String TABLE_NAME = "wcp_code_input_file";
        public static final String PK = "cf_key";
        public static final String PATH = "path";
        public static final String GIVEN_NAME = "given_name";
        public static final String FILE_NAME = "file_name";
        public static final String FILE_SIZE = "file_size";
        public static final String UPLOAD_AT = "upload_at";
    }

    public class PersistentLoginsTable {
        public static final String TABLE_NAME = "persistent_logins";
        public static final String PK = "series";
        public static final String USERNAME = "username";
        public static final String TOKEN = "token";
        public static final String LASTUSED = "lastUsed";
    }
}
