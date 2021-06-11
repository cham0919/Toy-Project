package com.wcp.coding.submit;

import com.wcp.common.base.CRUDService;

public interface SubmitHistoryService extends CRUDService<SubmitHistory, SubmitHistoryDto> {

    SubmitHistoryDto registerSubmitHistory(SubmitHistoryDto dto, String postId, String userKey);
}
