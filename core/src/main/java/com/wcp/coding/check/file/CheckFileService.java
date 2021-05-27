package com.wcp.coding.check.file;

import com.wcp.coding.CodingBoardService;
import com.wcp.coding.content.CodingContentDto;
import com.wcp.coding.content.MultiPartCodingContentDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckFileService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());



}
