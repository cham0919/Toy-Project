package com.wcp.coding.submit;


import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestRepository;
import com.wcp.coding.test.CodingTestService;
import com.wcp.mapper.CodeInputFileMapper;
import com.wcp.mapper.CodingTestMapper;
import com.wcp.mapper.SubmitHistoryMapper;
import com.wcp.user.User;
import com.wcp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmitHistoryServiceImpl implements SubmitHistoryService{

    private final Logger log = LoggerFactory.getLogger(SubmitHistoryServiceImpl.class);

    private final SubmitHistoryRepository submitHistoryRepository;
    private final CodingTestRepository codingTestRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public SubmitHistoryDto registerSubmitHistory(SubmitHistoryDto dto, String postId, String userKey){
        if (StringUtils.isEmpty(postId) || !StringUtils.isNumeric(postId)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check postId : "+ postId);
        }
        SubmitHistory submitHistory = SubmitHistoryMapper.INSTANCE.toEntity(dto);
        User user = userRepository.getOne(verifyKey(userKey));
        CodingTest codingTest =  codingTestRepository.getOne(Long.valueOf(postId));
        submitHistory.setUser(user)
                .setCodingTest(codingTest);
        submitHistoryRepository.save(submitHistory);
        return dto;
    }

    public Long verifyKey(String key){
        if (StringUtils.isEmpty(key) || !StringUtils.isNumeric(key)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check key : "+ key);
        }
        return Long.valueOf(key);
    }

    @Override
    public SubmitHistoryDto save(SubmitHistoryDto dto) {
        SubmitHistory entity = SubmitHistoryMapper.INSTANCE.toEntity(dto);
        submitHistoryRepository.save(entity);
        return dto;
    }

    @Override
    public SubmitHistoryDto fetchById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check id : "+ id);
        }
        SubmitHistory entity = submitHistoryRepository.findById(Long.valueOf(id)).get();
        return SubmitHistoryMapper.INSTANCE.toDto(entity);
    }

    @Override
    public List<SubmitHistoryDto> fetchAll() {
       List<SubmitHistory> entitys =  submitHistoryRepository.findAll();
       List<SubmitHistoryDto> dtos = new ArrayList<>();
       entitys.forEach(v -> {
            dtos.add(
                    SubmitHistoryMapper.INSTANCE.toDto(v)
            );
        });
        return dtos;
    }

    @Override
    @Transactional
    public SubmitHistoryDto update(SubmitHistoryDto dto) {
        String id = dto.getKey();
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        SubmitHistory entity = submitHistoryRepository.findById(Long.valueOf(id)).get();
        SubmitHistoryMapper.INSTANCE.updateFromDto(dto, entity);
        return dto;
    }

    @Override
    public SubmitHistoryDto delete(SubmitHistoryDto dto) {
        SubmitHistory submitHistory = SubmitHistoryMapper.INSTANCE.toEntity(dto);
        submitHistoryRepository.delete(submitHistory);
        return dto;
    }

    @Override
    public void deleteById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        submitHistoryRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public Long count() {
        return submitHistoryRepository.count();
    }
}
