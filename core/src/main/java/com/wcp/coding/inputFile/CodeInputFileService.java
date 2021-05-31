package com.wcp.coding.inputFile;

import com.wcp.common.FileUtils;
import com.wcp.judge.JudgeRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CodeInputFileService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodeInputFileManager codeInputFileManager;


    public Map<Integer, JudgeRequestDto> fetchCodingTestIOData(Long fileId){
        CodeInputFile codeInputFile = codeInputFileManager.fetchById(fileId).get();
        checkUnZip(codeInputFile);
        return fetchIOData(codeInputFile);
    }

    private Map<Integer, JudgeRequestDto> fetchIOData(CodeInputFile codeInputFile) {
        File dir = new File(codeInputFile.getPath());
        List<File> fileList = Arrays.asList(dir.listFiles());
        Map<Integer, JudgeRequestDto> fileIODtos = new HashMap<>();
        for ( File file : fileList ) {
            if(FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("zip")) { continue;}
            JudgeRequestDto dto;
            String fileIdx = FilenameUtils.getBaseName(file.getName()).trim();
            String content = FileUtils.readFileToString(file);

            dto = fileIODtos.getOrDefault(Integer.valueOf(fileIdx), new JudgeRequestDto());

            if(FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("in")){
                dto.setStdin(content);
            }else{
                dto.setExpected_output(content);
            }

            fileIODtos.put(Integer.valueOf(fileIdx), dto);
        }
        return fileIODtos;
    }

    public void checkUnZip(CodeInputFile codeInputFile){
        File zip = new File(codeInputFile.getFullPath());
        String dirPath = codeInputFile.getPath();
        if( zip.exists() &&
                countDirFile(dirPath) == 1 ){
            FileUtils.unZip(zip);
        }
    }

    public int countDirFile(String dirPath){
        File dir = new File(dirPath);
        return dir.listFiles().length;
    }




}
