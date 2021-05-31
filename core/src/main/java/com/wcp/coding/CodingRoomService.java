package com.wcp.coding;

import com.wcp.coding.room.CodingRoomDto;
import com.wcp.mapper.*;
import com.wcp.coding.room.CodingRoomManager;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.inputFile.CodeInputFileDto;
import com.wcp.coding.inputFile.CodeInputFileManager;
import com.wcp.coding.test.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CodingRoomService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final CodingRoomManager codingRoomManager;
    private final CodingTestManager codingTestManager;
    private final CodeInputFileManager codeInputFileManager;
    private final UserPersistenceManager userPersistenceManager;
    private final PageService pageService;

    private final CodingRoomMapper codingRoomMapper = Mappers.getMapper(CodingRoomMapper.class);
    private final CodingTestMapper codingTestMapper = Mappers.getMapper(CodingTestMapper.class);
    private final CodeInputFileMapper codeInputFileMapper = Mappers.getMapper(CodeInputFileMapper.class);


    public void registerContent(MultiPartCodingTestDto multiPartCodingTestDto, String postId) throws Exception {
        MultipartFile file = multiPartCodingTestDto.getFile();
        if(file == null || file.isEmpty()) {
            log.error("Only one file must be attached.");
            throw new FileUploadException();
        }
        CodeInputFileDto codeInputFileDto = registerFile(file);
        registerContent(multiPartCodingTestDto, codeInputFileDto, postId);
    }

    @Transactional
    public void registerContent(CodingTestDto codingTestDto, CodeInputFileDto codeInputFileDto, String postId){
            // CodingTest 등록
        CodingRoom codingRoom = codingRoomManager.fetchById(postId).get();
        CodingTest codingTest = codingTestMapper.toEntity(codingTestDto)
                .addCodingRoom(codingRoom);
        codingTestManager.save(codingTest);

        //CodeInputFile 등록
        CodeInputFile codeInputFile = codeInputFileMapper.toEntity(codeInputFileDto)
                .addCodingTest(codingTest);
        codeInputFileManager.save(codeInputFile);
    }



    private CodeInputFileDto registerFile(MultipartFile file) throws Exception {
        //TODO.file 검증
        String fileKey = UUID.randomUUID().toString();
        String fileName = fileKey + "." + FileUtils.getExtension(file.getOriginalFilename());
        File uploadFile = new File(FileUtils.resourceDirToday("C:\\git\\file"),
                fileKey);
        uploadFile.mkdirs();
        uploadFile = new File(uploadFile, fileName);
//        uploadFile.mkdirs();
        file.transferTo(uploadFile);

        CodeInputFileDto codeInputFileDto = new CodeInputFileDto();
        return codeInputFileDto.setGivenName(file.getOriginalFilename())
                .setFileName(fileName)
                .setFileSize(file.getSize())
                .setPath(uploadFile.getParent());
    }

    @Transactional
    public CodingRoom saveCodingPost(CodingRoom codingRoom, String userKey){
        User user = userPersistenceManager.findByUserId(userKey);
        codingRoom.addUser(user);
        return codingRoomManager.save(codingRoom);
    }

    @Transactional
    public CodingTest saveCodingTest(CodingTest codingTest, String codingRoomId){
        CodingRoom codingRoom = codingRoomManager.fetchById(codingRoomId).get();
        codingTest.addCodingRoom(codingRoom);
        return codingTestManager.save(codingTest);
    }

    public List<CodingRoom> fetchByCodingRoomPage(String currentPage){
        return codingRoomManager.fetchByPage(currentPage);
    }

    public List<CodingTest> fetchByCodingTestPage(String currentPage){
        return codingTestManager.fetchByPage(currentPage);
    }

    public CodingRoomDto fetchCodingPostById(String id){
        CodingRoom codingRoom = codingRoomManager.fetchById(id).get();
//        CodingRoomMapper codingRoomMapper = mapperFactory.create(MapperEnum.CODINGROOM);
        return codingRoomMapper.toDto(codingRoom);
    }

    public CodingTestDto fetchCodingTestById(String id){
        CodingTest codingTest = codingTestManager.fetchById(id).get();
        return CodingTestMapper.INSTANCE.toDto(codingTest);
    }

    public List<CodingRoom> fetchAllCodingRoom(){
        return codingRoomManager.fetchAll();
    }

    public List<CodingTest> fetchAllCodingTest(){
        return codingTestManager.fetchAll();
    }

    public CodingRoom updateCodingRoom(CodingRoom codingRoom){
        return codingRoomManager.update(codingRoom);
    }

    public CodingTest updateCodingTest(CodingTest codingTest){
        return codingTestManager.update(codingTest);
    }

    public CodingRoom deleteCodingRoom(CodingRoom codingRoom){
        return codingRoomManager.delete(codingRoom);
    }

    public CodingTest deleteCodingTest(CodingTest codingTest){
        return codingTestManager.delete(codingTest);
    }

    public void deleteCodingRoomById(String id){
        codingRoomManager.deleteById(id);
    }

    public void deleteCodingTestById(String id){
        codingTestManager.deleteById(id);
    }

    public Long codingRoomCount(){
        return codingRoomManager.count();
    }

    public Long codingTestCount(){
        return codingTestManager.count();
    }

    public PageInfo fetchCodingTestPageList(String currentPage){
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return fetchCodingTestPageList(Integer.valueOf(currentPage));
    }

    public PageInfo fetchCodingTestPageList(int currentPage){
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(currentPage)
                .setPageCount(Page.CODE_CONTENT_PAGE_COUNT)
                .setPostCount(Page.CODE_CONTENT_POST_COUNT)
                .setTotalPostCount(this.codingTestCount());
        log.debug(pageInfo.toString());
        return pageService.getPageList(pageInfo);
    }


    public PageInfo fetchCodingRoomPageList(String currentPage){
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return fetchCodingRoomPageList(Integer.valueOf(currentPage));
    }

    public PageInfo fetchCodingRoomPageList(int currentPage){
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(currentPage)
                .setPageCount(Page.CODE_BOARD_PAGE_COUNT)
                .setPostCount(Page.CODE_BOARD_POST_COUNT)
                .setTotalPostCount(this.codingRoomCount());
        log.debug(pageInfo.toString());
        return pageService.getPageList(pageInfo);
    }
}
