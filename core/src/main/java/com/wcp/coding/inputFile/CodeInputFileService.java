package com.wcp.coding.inputFile;

import com.wcp.common.base.CRUDService;
import com.wcp.judge.JudgeRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CodeInputFileService extends CRUDService<CodeInputFile> {

    CodeInputFile multiPartToEntity(MultipartFile file) throws IOException;

    File[] fetchIOFilesById(Long fileId);

    void checkUnZip(CodeInputFile codeInputFile);

    File[] fetchIOFiles(File dir);

}
