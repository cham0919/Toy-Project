package com.wcp.coding.test;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.wcp.page.Page.CODE_CONTENT_POST_COUNT;

@Component
@RequiredArgsConstructor
public class CodingTestPersistenceManager implements CodingTestManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodingTestRepository codingTestRepository;

    @Override
    public CodingTest save(CodingTest codingTest) {
        return codingTestRepository.save(codingTest);
    }


    @Override
    public List<CodingTest> fetchByPage(String currentPage) {
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return fetchByPage(Integer.valueOf(currentPage));
    }

    @Override
    public List<CodingTest> fetchByPage(int currentPage) {
        Page<CodingTest> codingTestPage = codingTestRepository
                .findAll(PageRequest
                        .of(currentPage - 1, CODE_CONTENT_POST_COUNT, Sort.by(Sort.Direction.ASC, "key")));
        return codingTestPage.getContent();
    }


    @Override
    public Optional<CodingTest> fetchById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        return fetchById(Long.valueOf(id));
    }

    @Override
    public Optional<CodingTest> fetchById(Long id) {
        return codingTestRepository.findById(id);
    }

    @Override
    public List<CodingTest> fetchAll() {
        return codingTestRepository.findAll();
    }

    @Override
    @Transactional
    public CodingTest update(CodingTest codingTest) {
        Optional<CodingTest> fetchBoard = fetchById(codingTest.getKey());
        fetchBoard = Optional.of(codingTest);
        return fetchBoard.get();
    }

    @Override
    public CodingTest delete(CodingTest codingTest) {
        codingTestRepository.delete(codingTest);
        return codingTest;
    }

    @Override
    public void deleteById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        deleteById(Long.valueOf(id));
    }

    @Override
    public void deleteById(Long id) {
        codingTestRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codingTestRepository.count();
    }
}
