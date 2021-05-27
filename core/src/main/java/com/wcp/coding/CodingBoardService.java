package com.wcp.coding;

import com.wcp.coding.board.CodingBoard;
import com.wcp.coding.board.CodingBoardDto;
import com.wcp.coding.board.CodingBoardMapper;
import com.wcp.coding.board.CodingBoardPersistenceManager;
import com.wcp.coding.check.file.CheckFile;
import com.wcp.coding.check.file.CheckFileDto;
import com.wcp.coding.check.file.CheckFileMapper;
import com.wcp.coding.check.file.CheckFilePersistenceManager;
import com.wcp.coding.content.*;
import com.wcp.common.FileUtils;
import com.wcp.page.Page;
import com.wcp.page.PageInfo;
import com.wcp.page.PageService;
import com.wcp.user.User;
import com.wcp.user.UserPersistenceManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CodingBoardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodingBoardPersistenceManager codingBoardPersistenceManager;
    private final CodingContentPersistenceManager codingContentPersistenceManager;
    private final CheckFilePersistenceManager checkFilePersistenceManager;
    private final UserPersistenceManager userPersistenceManager;
    private final PageService pageService;

    private final CodingBoardMapper codingBoardMapper = Mappers.getMapper(CodingBoardMapper.class);
    private final CodingContentMapper codingContentMapper = Mappers.getMapper(CodingContentMapper.class);
    private final CheckFileMapper checkFileMapper = Mappers.getMapper(CheckFileMapper.class);


    public void registerContent(MultiPartCodingContentDto multiPartCodingContentDto, String postId) throws Exception {
        MultipartFile file = multiPartCodingContentDto.getFile();
        if(file == null || file.isEmpty()) {
            log.error("Only one file must be attached.");
            throw new FileUploadException();
        }
        CheckFileDto checkFileDto = registerFile(file);
        registerContent(multiPartCodingContentDto, checkFileDto, postId);
    }

    @Transactional
    public void registerContent(CodingContentDto codingContentDto, CheckFileDto checkFileDto, String postId){
            // codingContent 등록
        CodingBoard codingBoard = codingBoardPersistenceManager.fetchById(postId).get();
        CodingContent codingContent = codingContentMapper.toEntity(codingContentDto)
                .addCodingBoard(codingBoard);
        codingContentPersistenceManager.save(codingContent);

        //checkfile 등록
        CheckFile checkFile = checkFileMapper.toEntity(checkFileDto)
                .addCodingContent(codingContent);
        checkFilePersistenceManager.save(checkFile);
    }



    private CheckFileDto registerFile(MultipartFile file) throws Exception {
        //TODO.file 검증
        String fileName = UUID.randomUUID().toString()+"."+FileUtils.getExtension(file.getOriginalFilename());
        File uploadFile = new File(FileUtils.resourceDirToday("C:\\git\\file"),
                fileName);
        file.transferTo(uploadFile);

        return CheckFileDto.builder()
                .givenName(file.getOriginalFilename())
                .fileName(fileName)
                .fileSize(file.getSize())
                .path(uploadFile.getPath())
                .build();
    }

    @Transactional
    public CodingBoard saveCodingPost(CodingBoard codingBoard, String userKey){
        User user = userPersistenceManager.findByUserId(userKey);
        codingBoard.addUser(user);
        return codingBoardPersistenceManager.save(codingBoard);
    }

    @Transactional
    public CodingContent saveCodingContent(CodingContent codingContent, String codingBoardId){
        CodingBoard codingBoard = codingBoardPersistenceManager.fetchById(codingBoardId).get();
        codingContent.addCodingBoard(codingBoard);
        return codingContentPersistenceManager.save(codingContent);
    }

    public List<CodingBoard> fetchByCodingBoardPage(String currentPage){
        return codingBoardPersistenceManager.fetchByPage(currentPage);
    }

    public List<CodingContent> fetchByCodingContentPage(String currentPage){
        return codingContentPersistenceManager.fetchByPage(currentPage);
    }

    public CodingBoardDto fetchCodingPostById(String id){
        CodingBoard codingBoard = codingBoardPersistenceManager.fetchById(id).get();
        return codingBoardMapper.toDto(codingBoard);
    }

    public CodingContent fetchCodingContentById(String id){
        return codingContentPersistenceManager.fetchById(id).get();
    }

    public List<CodingBoard> fetchAllCodingBoard(){
        return codingBoardPersistenceManager.fetchAll();
    }

    public List<CodingContent> fetchAllCodingContent(){
        return codingContentPersistenceManager.fetchAll();
    }

    public CodingBoard updateCodingBoard(CodingBoard codingBoard){
        return codingBoardPersistenceManager.update(codingBoard);
    }

    public CodingContent updateCodingContent(CodingContent codingContent){
        return codingContentPersistenceManager.update(codingContent);
    }

    public CodingBoard deleteCodingBoard(CodingBoard codingBoard){
        return codingBoardPersistenceManager.delete(codingBoard);
    }

    public CodingContent deleteCodingContent(CodingContent codingContent){
        return codingContentPersistenceManager.delete(codingContent);
    }

    public void deleteCodingBoardById(String id){
        codingBoardPersistenceManager.deleteById(id);
    }

    public void deleteCodingContentById(String id){
        codingContentPersistenceManager.deleteById(id);
    }

    public Long codingBoardCount(){
        return codingBoardPersistenceManager.count();
    }

    public Long codingContentCount(){
        return codingContentPersistenceManager.count();
    }

    public PageInfo fetchCodingContentPageList(String currentPage){
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return fetchCodingContentPageList(Integer.valueOf(currentPage));
    }

    public PageInfo fetchCodingContentPageList(int currentPage){
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(currentPage)
                .setPageCount(Page.CODE_CONTENT_PAGE_COUNT)
                .setPostCount(Page.CODE_CONTENT_POST_COUNT)
                .setTotalPostCount(this.codingContentCount());
        log.debug(pageInfo.toString());
        return pageService.getPageList(pageInfo);
    }


    public PageInfo fetchCodingBoardPageList(String currentPage){
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return fetchCodingBoardPageList(Integer.valueOf(currentPage));
    }

    public PageInfo fetchCodingBoardPageList(int currentPage){
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(currentPage)
                .setPageCount(Page.CODE_BOARD_PAGE_COUNT)
                .setPostCount(Page.CODE_BOARD_POST_COUNT)
                .setTotalPostCount(this.codingBoardCount());
        log.debug(pageInfo.toString());
        return pageService.getPageList(pageInfo);
    }
}
