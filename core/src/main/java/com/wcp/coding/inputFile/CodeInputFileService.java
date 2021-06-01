package com.wcp.coding.inputFile;

import com.wcp.common.Base64Utils;
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


    public List<JudgeRequestDto> fetchCodingTestIOData(Long fileId){
        CodeInputFile codeInputFile = codeInputFileManager.fetchById(fileId).get();
        checkUnZip(codeInputFile);
        return fetchIOData(codeInputFile);
    }

    private List<JudgeRequestDto> fetchIOData(CodeInputFile codeInputFile) {
        File dir = new File(codeInputFile.getPath());
        File[] files = sortFileList(dir.listFiles());
        List<JudgeRequestDto> judgeRequestDtos = new ArrayList<>();
        JudgeRequestDto dto = null;
        for (File file : files) {
            String extenstion = FilenameUtils.getExtension(file.getName());
            if(extenstion.equalsIgnoreCase("zip")){ continue; }

            String content = FileUtils.readFileToString(file);
            if(extenstion.equalsIgnoreCase("in")) {
                dto = new JudgeRequestDto().setStdin(
                        Base64Utils.encode(content));
                judgeRequestDtos.add(dto);
            }else if(extenstion.equalsIgnoreCase("out")){
                dto.setExpected_output(
                        Base64Utils.encode(content));
            }
        }

        return judgeRequestDtos;
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

    public File[] sortFileList(File[] files){
        Arrays.sort(files,
                new Comparator<Object>()
                {
                    @Override
                    public int compare(Object object1, Object object2) {
                        String s1 = "";
                        String s2 = "";
                        s1 = ((File)object1).getName();
                        s2 = ((File)object2).getName();
                        return s1.compareTo(s2);
                    }
                });
        return files;
    }
}
