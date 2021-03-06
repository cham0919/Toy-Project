package com.wcp.coding.test;

import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.inputFile.CodeInputFileRepository;
import com.wcp.coding.inputFile.CodeInputFileService;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomRepository;
import com.wcp.page.PageCalculator;
import com.wcp.page.PageCount;
import com.wcp.page.PageInfo;
import com.wcp.user.User;
import com.wcp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.wcp.mapper.CodingTestMapper.*;

@Service
@RequiredArgsConstructor
public class CodingTestServiceImpl implements CodingTestService{

    private final Logger log = LoggerFactory.getLogger(CodingTestServiceImpl.class);
    private final CodeInputFileService codeInputFileService;
    private final CodeInputFileRepository codeInputFileRepository;
    private final PageCalculator pageCalculator;

    private final CodingTestRepository codingTestRepository;
    private final CodingRoomRepository codingRoomRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public void registerContent(MultiPartDto multiPartDto) throws IOException, MimeTypeException{
        CodingTest codingTest = CODING_TEST_MAPPER.toEntity(multiPartDto);
        MultipartFile file = multiPartDto.getFile();
        if (file == null || file.isEmpty()) {
            throw new FileUploadException("Only one file must be attached.");
        }
        CodeInputFile codeInputFile = codeInputFileService.multiPartToEntity(file);
        registerCodingTest(codingTest, multiPartDto.getPostId(), multiPartDto.getUserKey());
        registerContent(codingTest, codeInputFile);
    }

    @Override
    public List<CodingTestDto> fetchByCurrentPage(String currentPage, String roomId, String userKey) {
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        if (StringUtils.isEmpty(roomId) || !StringUtils.isNumeric(roomId)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check roomId : "+ roomId);
        }
        if (StringUtils.isEmpty(userKey) || !StringUtils.isNumeric(userKey)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check userKey : "+ userKey);
        }
        return codingTestRepository.fetchByCurrentPage(Integer.valueOf(currentPage), Long.valueOf(roomId), Long.valueOf(userKey));
    }

    public void registerContent(CodingTest codingTest, CodeInputFile codeInputFile){
        //CodeInputFile 등록
        codeInputFile.setCodingTest(codingTest);
        codeInputFileRepository.save(codeInputFile);
    }

    private void registerCodingTest(CodingTest codingTest, String postId, String userKey){
        if (StringUtils.isEmpty(postId) || !StringUtils.isNumeric(postId)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check postId : "+ postId);
        }
        if (StringUtils.isEmpty(userKey) || !StringUtils.isNumeric(userKey)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check userKey : "+ userKey);
        }
        // CodingTest 등록
        CodingRoom codingRoom = codingRoomRepository.getOne(Long.valueOf(postId));
        User user = userRepository.getOne(Long.valueOf(userKey));
        codingTest.setCodingRoom(codingRoom)
                .setUser(user);
        codingTestRepository.save(codingTest);
    }


    @Override
    public CodingTestDto save(CodingTestDto dto){
        CodingTest codingTest = CODING_TEST_MAPPER.toEntity(dto);
        codingTestRepository.save(codingTest);
        return dto;
    }


    @Override
    public PageInfo fetchPageList(String currentPage, String roomId) {
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(Integer.valueOf(currentPage))
                .setTotalPostCount(fetchTestCount(Long.valueOf(roomId)));
        return pageCalculator.fetchPageList(pageInfo, PageCount.CODING_TEST);
    }


    @Override
    public CodingTestDto fetchById(String id){
        CodingTest codingTest = fetchById(Long.valueOf(id));
        return CODING_TEST_MAPPER.toDto(codingTest);
    }

    public CodingTest fetchById(Long id) {
        return codingTestRepository.findById(id).get();
    }

    public CodingTest fetchByIdJoinUser(Long id, Long userKey) {
        return codingTestRepository.findByKeyAndUserKey(id, userKey);
    }

    @Override
    public List<CodingTestDto> fetchAll() {
        List<CodingTest> codingTests = codingTestRepository.findAll();
        List<CodingTestDto> dtos = new ArrayList<>();
        codingTests.forEach(v -> { dtos.add(CODING_TEST_MAPPER.toDto(v)); });
        return dtos;
    }

    @Override
    @Transactional
    public CodingTestDto update(CodingTestDto dto) {
        CodingTest codingTest = fetchByIdJoinUser(Long.valueOf(dto.getKey()), Long.valueOf(dto.getUserKey()));
        CODING_TEST_MAPPER.updateFromDto(dto, codingTest);
        return dto;
    }

    @Override
    public CodingTestDto delete(CodingTestDto dto) {
        CodingTest codingTest = CODING_TEST_MAPPER.toEntity(dto);
        codingTestRepository.delete(codingTest);
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
        codingTestRepository.deleteById(id);
    }


    public Long fetchTestCount(Long roomId) {
        return codingTestRepository.countByCodingRoom_Key(roomId);
    }

    @Override
    public Long count() {
        return codingTestRepository.count();
    }
}
