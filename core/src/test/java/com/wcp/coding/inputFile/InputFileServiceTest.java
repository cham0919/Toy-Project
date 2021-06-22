package com.wcp.coding.inputFile;

import org.apache.commons.io.FileExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class InputFileServiceTest {

    @InjectMocks
    private CodeInputFileServiceImpl codeInputFileService;
    @Mock
    private CodeInputFileRepository codeInputFileRepository;


    @Test
    public void multiPartToEntity_Success_NotNull() throws Throwable {
        MockMultipartFile file = new MockMultipartFile("file", "test.zip", "text/plain", "some xml".getBytes());

        CodeInputFile codeInputFile = codeInputFileService.multiPartToEntity(file);

        assertNotNull(codeInputFile);
    }


    @Test
    public void fetchIOFilesById_Success_NotNull() throws FileExistsException {
        CodeInputFile codeInputFile = new CodeInputFile()
                .setFileName("4b648c0b-6683-466c-991e-4776eb18a976.zip")
                .setGivenName("test")
                .setPath("C:\\git\\file\\2021\\05\\31\\4b648c0b-6683-466c-991e-4776eb18a976");

        given(codeInputFileRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(codeInputFile));

        File[] files = codeInputFileService.fetchIOFilesById(1L);

        assertNotNull(files);
    }

    @Test
    public void fetchIOFilesById_NotExistFile_ExceptionThrown(){
        CodeInputFile codeInputFile = new CodeInputFile()
                .setFileName("4b648c0b-6683-466c-991e-4776eb18a976.zip")
                .setGivenName("test")
                .setPath("C:\\git\\file\\");

        given(codeInputFileRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(codeInputFile));

        Assertions.assertThrows(FileExistsException.class, () -> {
            codeInputFileService.fetchIOFilesById(1L);
        });
    }

//    @Test
//    public void checkUnZip_Success_NoAction() throws FileExistsException {
//        CodeInputFile codeInputFile = new CodeInputFile()
//                .setFileName("4b648c0b-6683-466c-991e-4776eb18a976.zip")
//                .setGivenName("test")
//                .setPath("C:\\git\\file\\2021\\05\\31\\4b648c0b-6683-466c-991e-4776eb18a976");
//
//
//        codeInputFileService.isUnZip(codeInputFile);
//    }

//    @Test
//    public void checkUnZip_NotExistFile_ExceptionThrown(){
//        CodeInputFile codeInputFile = new CodeInputFile()
//                .setFileName("4b648c0b-6683-466c-991e-4776eb18a976.zip")
//                .setGivenName("test")
//                .setPath("C:\\git\\file\\");
//
//
//        Assertions.assertThrows(FileExistsException.class, () -> {
//            codeInputFileService.isUnZip(codeInputFile);
//        });
//    }

    @Test
    public void fetchIOFiles_Success_NoAction(){
        File dir = mock(File.class);
        File[] files = new File[2];
        given(dir.listFiles(any(FilenameFilter.class)))
                .willReturn(files);

        files = codeInputFileService.fetchIOFiles(dir);

        assertNotNull(files);
    }

    @Test
    public void save_Success_Equals(){
        CodeInputFileDto dto = mock(CodeInputFileDto.class);

        CodeInputFileDto returnDto = codeInputFileService.save(dto);

        assertEquals(dto, returnDto);
    }

    @Test
    public void fetchById_Success_NotNull() {
        String id = "1";

        given(codeInputFileRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(mock(CodeInputFile.class)));

        CodeInputFileDto dto = codeInputFileService.fetchById(id);

        assertNotNull(dto);
    }

    @Test
    public void fetchById_IsNotNumericId_ExceptionThrown() {
        String id = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codeInputFileService.fetchById(id);
        });
    }

    @Test
    public void fetchAll_Success_Equals(){
        given(codeInputFileRepository.findAll())
                .willReturn(mock(List.class));

        List<CodeInputFileDto> dtos = codeInputFileService.fetchAll();

        assertNotNull(dtos);
    }

    @Test
    public void update_Success_NotNull() {
        String id = "1";
        CodeInputFileDto dto = new CodeInputFileDto().setKey(id);

        given(codeInputFileRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(mock(CodeInputFile.class)));

        dto = codeInputFileService.update(dto);

        assertNotNull(dto);
    }

    @Test
    public void delete_Success_IOEquals() {
        CodeInputFileDto dto = mock(CodeInputFileDto.class);

        CodeInputFileDto returnDto = codeInputFileService.delete(dto);

        assertEquals(dto, returnDto);
    }

    @Test
    public void deleteById_Success_NoAction(){
        String id = "1";

        codeInputFileService.deleteById(id);
    }

    @Test
    public void deleteById_IsNotNumericId_ExceptionThrown(){
        String id = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codeInputFileService.deleteById(id);
        });
    }
}
