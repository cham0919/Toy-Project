package com.wcp.coding.test;

import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.inputFile.CodeInputFileRepository;
import com.wcp.coding.inputFile.CodeInputFileService;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomRepository;
import com.wcp.coding.room.CodingRoomService;
import com.wcp.mapper.CodingTestMapper;
import com.wcp.page.PageCalculator;
import com.wcp.page.PageCount;
import com.wcp.page.PageInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodingTestServiceImpl implements CodingTestService{

    private final Logger log = LoggerFactory.getLogger(CodingTestServiceImpl.class);
    private final CodeInputFileService codeInputFileService;
    private final CodeInputFileRepository codeInputFileRepository;
    private final PageCalculator pageCalculator;

    private final CodingTestRepository codingTestRepository;
    private final CodingRoomRepository codingRoomRepository;


    @Override
    public CodingTestDto fetchDtoById(String id){
        CodingTest codingTest = fetchById(id).get();
        return CodingTestMapper.INSTANCE.toDto(codingTest);
    }

    @Override
    public void registerContent(MultiPartDto multiPartDto, String postId) throws Exception {
        CodingTest codingTest = CodingTestMapper.INSTANCE.toEntity(multiPartDto);
        MultipartFile file = multiPartDto.getFile();
        if(file == null || file.isEmpty()) {
            log.error("Only one file must be attached.");
            throw new FileUploadException();
        }
        CodeInputFile codeInputFile = codeInputFileService.multiPartToEntity(file);
        registerContent(codingTest, codeInputFile, postId);
    }

    @Transactional
    public void registerContent(CodingTest codingTest, CodeInputFile codeInputFile, String postId){
        if (StringUtils.isEmpty(postId) || !StringUtils.isNumeric(postId)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check postId : "+ postId);
        }
        // CodingTest 등록
        CodingRoom codingRoom = codingRoomRepository.getOne(Long.valueOf(postId));
        codingTest.setCodingRoom(codingRoom);
        save(codingTest);

        //CodeInputFile 등록
        codeInputFile.setCodingTest(codingTest);
        codeInputFileRepository.save(codeInputFile);
    }

    @Transactional
    public CodingTest saveCodingTest(CodingTest codingTest, String codingRoomId){
        if (StringUtils.isEmpty(codingRoomId) || !StringUtils.isNumeric(codingRoomId)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check codingRoomId : "+ codingRoomId);
        }
        CodingRoom codingRoom = codingRoomRepository.getOne(Long.valueOf(codingRoomId));
        codingTest.setCodingRoom(codingRoom);
        return save(codingTest);
    }


    @Override
    public CodingTest save(CodingTest codingTest) {
        return codingTestRepository.save(codingTest);
    }


    @Override
    public List<CodingTest> fetchByPage(String currentPage) {
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return fetchByPage(Integer.valueOf(currentPage));
    }

    @Override
    public PageInfo fetchPageList(String currentPage) {
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(Integer.valueOf(currentPage))
                .setTotalPostCount(count());
        return pageCalculator.fetchPageList(pageInfo, PageCount.CODING_TEST);
    }

    @Override
    public List<CodingTest> fetchByPage(int currentPage) {
        Page<CodingTest> codingTestPage = codingTestRepository
                .findAll(PageRequest
                        .of(currentPage - 1, PageCount.CODING_TEST.getPostCount(), Sort.by(Sort.Direction.ASC, "key")));
        return codingTestPage.getContent();
    }


    @Override
    public Optional<CodingTest> fetchById(String id){
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        return fetchById(Long.valueOf(id));
    }

    @Override
    public Optional<CodingTest> fetchById(Long id) {
        return codingTestRepository.findById(id);
    }

    @Override
    public List<CodingTest> fetchAll() {
        return codingTestRepository.findAll();
    }

    @Override
    @Transactional
    public CodingTest update(CodingTest codingTest) {
        Optional<CodingTest> fetchBoard = fetchById(codingTest.getKey());
        fetchBoard = Optional.of(codingTest);
        return fetchBoard.get();
    }

    @Override
    public CodingTest delete(CodingTest codingTest) {
        codingTestRepository.delete(codingTest);
        return codingTest;
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
        codingTestRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codingTestRepository.count();
    }
}
