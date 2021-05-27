package com.wcp.coding.check.file;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckFileManager implements CheckFilePersistenceManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CheckFileRepository checkFileRepository;

    @Override
    public CheckFile save(CheckFile checkFile) {
        return checkFileRepository.save(checkFile);
    }

    @Override
    public Optional<CheckFile> fetchById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        return fetchById(Long.valueOf(id));
    }

    @Override
    public Optional<CheckFile> fetchById(Long id) {
        return checkFileRepository.findById(id);
    }

    @Override
    public List<CheckFile> fetchAll() {
        return checkFileRepository.findAll();
    }

    @Override
    @Transactional
    public CheckFile update(CheckFile checkFile) {
        Optional<CheckFile> fetchBoard = fetchById(checkFile.getKey());
        fetchBoard = Optional.of(checkFile);
        return fetchBoard.get();
    }

    @Override
    public CheckFile delete(CheckFile checkFile) {
        checkFileRepository.delete(checkFile);
        return checkFile;
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
        checkFileRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return checkFileRepository.count();
    }
}
