package com.wcp.coding.inputFile;

import com.wcp.common.file.FileUtils;
import com.wcp.common.file.MimeType;
import com.wcp.env.Config;
import com.wcp.mapper.CodeInputFileMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.mime.MimeTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wcp.coding.inputFile.InputFileExtension.IN;
import static com.wcp.coding.inputFile.InputFileExtension.OUT;

@Service
@RequiredArgsConstructor
public class CodeInputFileServiceImpl implements CodeInputFileService{

    private final Logger log = LoggerFactory.getLogger(CodeInputFileServiceImpl.class);
    private final CodeInputFileRepository codeInputFileRepository;


    @Override
    public CodeInputFile multiPartToEntity(MultipartFile file) throws Throwable {
        if(!FileUtils.checkMimeType(file, MimeType.ZIP)){
            throw new MimeTypeException("Upload is only possible as a zip file");
        };
        String fileKey = UUID.randomUUID().toString();
        String fileName = fileKey + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(file.getOriginalFilename());
        File uploadFile = new File(FileUtils.resourceDirToday(Config.getProperty("com.wcp.default.dir")),
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
    public File[] fetchIOFilesById(Long fileId) throws FileExistsException {
        CodeInputFile codeInputFile = fetchById(fileId);
        checkUnZip(codeInputFile);
        File dir = codeInputFile.getDir();
        return FileUtils.sortFileList(dir.listFiles());
    }

    @Override
    public void checkUnZip(CodeInputFile codeInputFile) throws FileExistsException {
        File zip = codeInputFile.getInputFile();
        File dir = codeInputFile.getDir();
        File[] files = fetchIOFiles(dir);

        if( zip.exists() ) {
           if(files.length == 0){
               FileUtils.unZip(zip);
           }
        } else {
            log.error("No Exist File {}", zip.getAbsolutePath());
            throw new FileExistsException();
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
    public CodeInputFileDto save(CodeInputFileDto dto){
        CodeInputFile codeInputFile = CodeInputFileMapper.CODE_INPUT_FILE_MAPPER.toEntity(dto);
        codeInputFileRepository.save(codeInputFile);
        return dto;
    }

    @Override
    public CodeInputFileDto fetchById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        CodeInputFile codeInputFile = fetchById(Long.valueOf(id));
        return CodeInputFileMapper.CODE_INPUT_FILE_MAPPER.toDto(codeInputFile);
    }

    public CodeInputFile fetchById(Long id) {
        return codeInputFileRepository.findById(id).get();
    }

    @Override
    public List<CodeInputFileDto> fetchAll(){
        List<CodeInputFile> codeInputFiles = codeInputFileRepository.findAll();
        List<CodeInputFileDto> dtos = new ArrayList<>();
        codeInputFiles.forEach(v -> {
            dtos.add(
                    CodeInputFileMapper.CODE_INPUT_FILE_MAPPER.toDto(v)
            );
        });
        return dtos;
    }

    @Override
    @Transactional
    public CodeInputFileDto update(CodeInputFileDto dto) {
        String id = dto.getKey();
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        CodeInputFile codeInputFile = fetchById(Long.valueOf(id));
        CodeInputFileMapper.CODE_INPUT_FILE_MAPPER.updateFromDto(dto, codeInputFile);
        return dto;
    }

    @Override
    public CodeInputFileDto delete(CodeInputFileDto dto) {
        CodeInputFile codeInputFile = CodeInputFileMapper.CODE_INPUT_FILE_MAPPER.toEntity(dto);
        codeInputFileRepository.delete(codeInputFile);
        return dto;
    }

    @Override
    public void deleteById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        deleteById(Long.valueOf(id));
    }

    private void deleteById(Long id) {
        codeInputFileRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codeInputFileRepository.count();
    }

}
