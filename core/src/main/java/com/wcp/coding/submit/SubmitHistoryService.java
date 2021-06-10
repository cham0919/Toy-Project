package com.wcp.coding.submit;


import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestService;
import com.wcp.mapper.SubmitHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SubmitHistoryService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final SubmitHistoryRepository submitHistoryRepository;
    private final CodingTestService codingTestService;




//    public void registerSubmitHistory(SubmitHistoryDto dto, String postId, String userKey){
    @Transactional
    public String registerSubmitHistory(SubmitHistoryDto dto, String postId){
        SubmitHistory submitHistory = SubmitHistoryMapper.INSTANCE.toEntity(dto);
//        submitHistory.setUser(new User().setKey(verifyKey(userKey)));
        CodingTest codingTest =  codingTestService.fetchById(postId).get();
        submitHistory.setCodingTest(codingTest);
        submitHistoryRepository.save(submitHistory);
        return codingTest.getCodingRoom().getKey().toString();
    }

    public Long verifyKey(String key){
        if (StringUtils.isEmpty(key) || !StringUtils.isNumeric(key)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check key : "+ key);
        }
        return Long.valueOf(key);
    }

}
