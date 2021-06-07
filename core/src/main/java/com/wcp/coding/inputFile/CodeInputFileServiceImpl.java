package com.wcp.coding.inputFile;

import com.wcp.common.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.wcp.coding.inputFile.InputFileExtension.IN;
import static com.wcp.coding.inputFile.InputFileExtension.OUT;

@Service
@RequiredArgsConstructor
public class CodeInputFileServiceImpl implements CodeInputFileService{

    private final Logger log = LoggerFactory.getLogger(CodeInputFileServiceImpl.class);
    private final CodeInputFileRepository codeInputFileRepository;


    @Override
    public CodeInputFile multiPartToEntity(MultipartFile file) throws IOException {
        //TODO.file 검증
        String fileKey = UUID.randomUUID().toString();
        String fileName = fileKey + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(file.getOriginalFilename());
        File uploadFile = new File(FileUtils.resourceDirToday("C:\\git\\file"),
                fileKey);
        if(!uploadFile.exists()){ uploadFile.mkdirs(); }
        uploadFile = new File(uploadFile, fileName);
        file.transferTo(uploadFile);

        return new CodeInputFile().setGivenName(file.getOriginalFilename())
                .setFileName(fileName)
                .setFileSize(file.getSize())
                .setPath(uploadFile.getParent());
    }


    @Override
    public File[] fetchIOFilesById(Long fileId){
        CodeInputFile codeInputFile = fetchById(fileId).get();
        checkUnZip(codeInputFile);
        File dir = codeInputFile.getDir();
        return FileUtils.sortFileList(dir.listFiles());
    }

    @Override
    public void checkUnZip(CodeInputFile codeInputFile){
        File zip = codeInputFile.getInputFile();
        File dir = codeInputFile.getDir();
        File[] files = fetchIOFiles(dir);

        if( zip.exists() &&
                files.length == 0 ){
            FileUtils.unZip(zip);
        }
    }

    @Override
    public File[] fetchIOFiles(File dir){
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String extension = FilenameUtils.getExtension(name);
                if(IN.equalsIgnoreValue(extension)
                        || OUT.equalsIgnoreValue(extension)){
                    return true;
                }
                return false;
            }
        });
    }

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
