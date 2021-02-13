package com.wcp.judge;

public interface Judge extends Base64Utils{

    static final String URL = "https://judge0-ce.p.rapidapi.com";
    static final String TOKEN = "b8648f0040msh885f2f3104a8cd9p17d505jsna839dc3d1d27";
    static final String HOST = "judge0-ce.p.rapidapi.com";

    static final String WIDGET = URL +"/widget";
    static final String SUBSCRIPTIONDETAIL = URL + "/rapidapi";
    static final String SUBMISSION = URL + "/submissions";
    static final String BATCHSUBMISSION = URL + "/submissions" + "/batch";
    static final String STATUSES = URL + "/statuses";
    static final String PASTE = URL + "/paste";
    static final String LANGUAGES = URL + "/languages";
    static final String CONFIG = URL + "/config_info";

}
