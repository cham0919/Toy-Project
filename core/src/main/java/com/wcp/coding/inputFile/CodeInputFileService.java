package com.wcp.coding.inputFile;

import com.wcp.common.base.CRUDService;
import com.wcp.judge.JudgeRequestDto;
import org.apache.commons.io.FileExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CodeInputFileService extends CRUDService<CodeInputFile, CodeInputFileDto> {

    CodeInputFile multiPartToEntity(MultipartFile file) throws Throwable;

    File[] fetchIOFilesById(Long fileId) throws FileExistsException;

    void checkUnZip(CodeInputFile codeInputFile) throws FileExistsException;

    File[] fetchIOFiles(File dir) throws FileExistsException;

}
