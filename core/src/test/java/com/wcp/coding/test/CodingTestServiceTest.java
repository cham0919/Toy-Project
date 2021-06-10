package com.wcp.coding.test;

import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.inputFile.CodeInputFileRepository;
import com.wcp.coding.inputFile.CodeInputFileService;
import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomRepository;
import com.wcp.page.PageCalculator;
import com.wcp.page.PageCount;
import com.wcp.page.PageInfo;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CodingTestServiceTest {

    @InjectMocks
    private CodingTestServiceImpl codingTestService;

    @Mock
    private CodeInputFileService codeInputFileService;
    @Mock
    private CodeInputFileRepository codeInputFileRepository;
    @Mock
    private PageCalculator pageCalculator;
    @Mock
    private CodingTestRepository codingTestRepository;
    @Mock
    private CodingRoomRepository codingRoomRepository;

    @Test
    public void registerContent_Success_DoesNotThrow() throws Exception {
        String postId = "1";
        MockMultipartFile file = new MockMultipartFile("file", "test.zip", "text/plain", "some xml".getBytes());
        MultiPartDto multiPartDto = new MultiPartDto().setFile(file);

        given(codeInputFileService.multiPartToEntity(any(MultipartFile.class)))
                .willReturn(new CodeInputFile());
        given(codingRoomRepository.getOne(anyLong()))
                .willReturn(new CodingRoom());
        given(codeInputFileRepository.save(any(CodeInputFile.class)))
                .willReturn(new CodeInputFile());

        assertDoesNotThrow(() -> codingTestService.registerContent(multiPartDto, postId));
    }

    @Test
    public void registerContent_IsNotNumericId_ExceptionThrown() throws Exception {
        String postId = "A";
        MockMultipartFile file = new MockMultipartFile("file", "test.zip", "text/plain", "some xml".getBytes());
        MultiPartDto multiPartDto = new MultiPartDto().setFile(file);

        given(codeInputFileService.multiPartToEntity(any(MultipartFile.class)))
                .willReturn(new CodeInputFile());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingTestService.registerContent(multiPartDto, postId);
        });
    }

    @Test
    public void registerContent_IsNullFile_ExceptionThrown() throws Exception {
        String postId = "A";
        MultiPartDto multiPartDto = new MultiPartDto();

        Assertions.assertThrows(FileUploadException.class, () -> {
            codingTestService.registerContent(multiPartDto, postId);
        });
    }

    @Test
    public void save_Success_NotNull(){
        given(codingTestRepository.save(any(CodingTest.class)))
                .willReturn(mock(CodingTest.class));

        CodingTestDto dto = codingTestService.save(mock(CodingTestDto.class));

        assertNotNull(dto);
    }

    @Test
    public void fetchByPage_Success_NotNull() {
        String currentPage = "1";

        given(codingTestRepository.findAll(any(PageRequest.class)))
                .willReturn(mock(Page.class));

        List<CodingTestDto> codingTestDtos = codingTestService.fetchByPage(currentPage);

        assertNotNull(codingTestDtos);
    }

    @Test
    public void fetchByPage_IsNotNumericCurrentPage_ExceptionThrown() {
        String currentPage = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingTestService.fetchByPage(currentPage);
        });
    }

    @Test
    public void fetchPageList_Success_NotNull(){
        String currentPage = "1";

        given(codingTestRepository.count())
                .willReturn(10L);
        given(pageCalculator.fetchPageList(any(PageInfo.class), any(PageCount.class)))
                .willReturn(mock(PageInfo.class));

        PageInfo pageInfo = codingTestService.fetchPageList(currentPage);

        assertNotNull(pageInfo);
    }

    @Test
    public void fetchPageList_IsNotNumericCurrentPage_ExceptionThrown(){
        String currentPage = "A";


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingTestService.fetchPageList(currentPage);
        });
    }

    @Test
    public void fetchById_IsNotNumericId_ExceptionThrown(){
        String id = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingTestService.fetchById(id);
        });
    }

    @Test
    public void fetchAll_Success_NotNull() {
        given(codingTestRepository.findAll())
                .willReturn(mock(List.class));

        List<CodingTestDto> dtos = codingTestService.fetchAll();

        assertNotNull(dtos);
    }

    @Test
    public void update_Success_IOEquals() {
        CodingTestDto tempDto = new CodingTestDto().setKey("1");

        given(codingTestRepository.findById(anyLong()))
                .willReturn((Optional.ofNullable(mock(CodingTest.class))));

        CodingTestDto dto = codingTestService.update(tempDto);

        assertEquals(dto, tempDto);
    }

    @Test
    public void update_IsNotNumericId_ExceptionThrown() {
        CodingTestDto tempDto = new CodingTestDto().setKey("A");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingTestService.update(tempDto);
        });
    }

    @Test
    public void delete_Success_IOEquals() {
        CodingTestDto mockDto = mock(CodingTestDto.class);

        CodingTestDto dto = codingTestService.delete(mockDto);

        assertEquals(dto, mockDto);
    }

    @Test
    public void deleteById_IsNotNumericId_ExceptionThrown(){
        String id = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingTestService.deleteById(id);
        });
    }
}