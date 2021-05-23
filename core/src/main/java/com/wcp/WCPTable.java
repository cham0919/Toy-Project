package com.wcp;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class WCPTable {
    public class UserTable {
        public static final String TABLE_NAME = "WCP_User";
        public static final String PK = "user_key";
        public static final String ID = "user_id";
        public static final String PW = "user_pw";
        public static final String EMAIL = "user_email";
        public static final String NAME = "user_name";
        public static final String REGISTER_DATETIME = "user_register_datetime";
        public static final String NICKNAME = "user_nickname";
        public static final String PHONE = "user_phone";
        public static final String STATUS = "user_status";
        public static final String ROLE = "user_role";
    }

    public class TokenTable {
        public static final String TABLE_NAME = "WCP_Token";
        public static final String PK = "token_key";
        public static final String VALUE = "token_value";
        public static final String TYPE = "token_type";
        public static final String CREATE_DATETIME = "token_create_datetime";
        public static final String USE_DATETIME = "token_use_datetime";
        public static final String EXPIRED = "token_expired";
    }

    public class BoardLikeTable {
        public static final String TABLE_NAME = "WCP_Board_Like";
        public static final String PK = "lik_key";
        public static final String TARGET_ID = "target_id";
        public static final String TARGET_TYPE = "target_type";
        public static final String TYPE = "lik_type";
        public static final String DATETIME = "lik_datetime";
    }

    public class BoardTable {
        public static final String TABLE_NAME = "WCP_Board";
        public static final String PK = "board_key";
        public static final String TITLE = "post_title";
        public static final String CONTENT = "post_content";
        public static final String UPLOAD_DATETIME = "post_upload_datetime";
        public static final String UPDATE_DATETIME = "post_update_datetime";
        public static final String HIT = "post_hit";
        public static final String DELETE = "post_del";
        public static final String LIKE = "post_like";
        public static final String DISLIKE = "post_dislike";
    }

    public class BoardCategoryTable {
        public static final String TABLE_NAME = "WCP_Board_Category";
        public static final String PK = "bca_key";
        public static final String NAME = "bca_name";
    }

    public class BoardCommantTable {
        public static final String TABLE_NAME = "WCP_Board_Commant";
        public static final String PK = "cmt_key";
        public static final String CONTENT = "cmt_content";
        public static final String UPLOAD_DATETIME = "cmt_upload_datetime";
        public static final String UPDATE_DATETIME = "cmt_update_datetime";
        public static final String LIKE = "cmt_like";
        public static final String DISLIKE = "cmt_dislike";
        public static final String DELETE = "cmt_del";
    }

    public class CodingBoardTable {
        public static final String TABLE_NAME = "WCP_Coding_Board";
        public static final String PK = "cdb_key";
        public static final String TITLE = "cdb_title";
        public static final String INTRO = "cdb_intro";
        public static final String CREATE_DATETIME = "cdb_create_datetime";
        public static final String SECRET = "cdb_secret";
        public static final String PASSWORD = "cdb_password";
        public static final String MAX_USER = "cdb_max_user";
        public static final String RAMDOM_KEY = "cdb_ramdom_key";
        public static final String LANGUAGE = "cdb_language";
    }

    public class CodingJoinUserTable {
        public static final String TABLE_NAME = "WCP_Coding_Join_User";
        public static final String PK = "cju_key";
        public static final String JOIN_DATETIME = "cju_join_datetime";
        public static final String STATUS = "cju_status";
        public static final String ROLE = "cju_role";
    }

    public class CodingContentTable {
        public static final String TABLE_NAME = "WCP_Code_Content";
        public static final String PK = "cct_key";
        public static final String TITLE = "cct_title";
        public static final String CONTENT = "cct_content";
        public static final String SUBMIT_DATETIME = "cct_submit_datetime";
        public static final String AUTH = "cct_auth";
    }

    public class SubmitHistoryTable {
        public static final String TABLE_NAME = "WCP_Submit_History";
        public static final String PK = "sbh_key";
        public static final String STATUS = "sbh_status";
        public static final String LANGUAGE = "sbh_language";
        public static final String CODE = "sbh_code";
        public static final String SUBMIT_DATETIME = "sbh_submit_datetime";
        public static final String RUN_TIME = "sbh_run_time";
    }

    public class CheckFileTable {
        public static final String TABLE_NAME = "WCP_Check_File";
        public static final String PK = "cf_key";
        public static final String PATH = "cf_path";
        public static final String GIVEN_NAME = "cf_given_name";
        public static final String FILE_NAME = "cf_file_name";
        public static final String UPLOAD_DATETIME = "cf_upload_datetime";
    }

    public class PersistentLoginsTable {
        public static final String TABLE_NAME = "persistent_logins";
        public static final String PK = "series";
        public static final String USERNAME = "username";
        public static final String TOKEN = "token";
        public static final String LASTUSED = "lastUsed";
    }
}
