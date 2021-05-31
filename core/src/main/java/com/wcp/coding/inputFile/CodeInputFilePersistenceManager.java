package com.wcp.coding.inputFile;

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
public class CodeInputFilePersistenceManager implements CodeInputFileManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodeInputFileRepository codeInputFileRepository;

    @Override
    public CodeInputFile save(CodeInputFile codeInputFile) {
        return codeInputFileRepository.save(codeInputFile);
    }

    @Override
    public Optional<CodeInputFile> fetchById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        return fetchById(Long.valueOf(id));
    }

    @Override
    public Optional<CodeInputFile> fetchById(Long id) {
        return codeInputFileRepository.findById(id);
    }

    @Override
    public List<CodeInputFile> fetchAll() {
        return codeInputFileRepository.findAll();
    }

    @Override
    @Transactional
    public CodeInputFile update(CodeInputFile codeInputFile) {
        Optional<CodeInputFile> fetchBoard = fetchById(codeInputFile.getKey());
        fetchBoard = Optional.of(codeInputFile);
        return fetchBoard.get();
    }

    @Override
    public CodeInputFile delete(CodeInputFile codeInputFile) {
        codeInputFileRepository.delete(codeInputFile);
        return codeInputFile;
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
        codeInputFileRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codeInputFileRepository.count();
    }
}
