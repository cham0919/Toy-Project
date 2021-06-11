package com.wcp.coding.room;

import com.wcp.page.PageCalculator;
import com.wcp.page.PageCount;
import com.wcp.page.PageInfo;
import com.wcp.user.User;
import com.wcp.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.wcp.WCPTable.CodingRoomTable.PK;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CodingRoomServiceTest {

    @InjectMocks
    private CodingRoomServiceImpl codingRoomService;
    @Mock
    private CodingRoomRepository codingRoomRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PageCalculator pageCalculator;

    @Test
    public void save_Success_NotNull(){
        String userKey = "1";
        given(userRepository.getOne(anyLong()))
                .willReturn(mock(User.class));
        given(codingRoomRepository.save(any()))
                .willReturn(mock(CodingRoom.class));

        CodingRoomDto fetchDto = codingRoomService.save(mock(CodingRoomDto.class), userKey);

        assertNotNull(fetchDto);
    }

    @Test
    public void save_IsNotNumericId_ExceptionThrown(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingRoomService.save(mock(CodingRoomDto.class), "A");
        });
    }

    @Test
    public void save_NoFetchUser_ExceptionThrown(){
        given(userRepository.getOne(anyLong()))
                .willReturn(null);
        given(codingRoomRepository.save(any()))
                .willThrow(DataIntegrityViolationException.class);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            codingRoomService.save(mock(CodingRoomDto.class), "1");
        });
    }

    @Test
    public void fetchByPage_IsNotNumericCurrentPage_ExceptionThrown() {
        String currentPage = "A";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingRoomService.fetchByPage(currentPage);
        });
    }


    @Test
    public void fetchByPage_Success_NotNull() {
        String currentPage = "1";

        given(codingRoomRepository.findAll(any(PageRequest.class)))
                .willReturn(mock(Page.class));

        List<CodingRoomDto> dtos = codingRoomService.fetchByPage(currentPage);

        assertNotNull(dtos);
    }


    @Test
    public void fetchPageList_Success_NotNull(){
        String currentPage = "1";

        given(codingRoomRepository.count())
                .willReturn(10L);
        given(pageCalculator.fetchPageList(any(PageInfo.class), any(PageCount.class)))
                .willReturn(PageInfo.of());

        PageInfo pageInfo = codingRoomService.fetchPageList(currentPage);

        assertNotNull(pageInfo);
    }

    @Test
    public void fetchPageList_IsNotNumericCurrentPage_ExceptionThrown(){
        String currentPage = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingRoomService.fetchPageList(currentPage);
        });
    }

    @Test
    public void fetchById_IsNotNumericId_ExceptionThrown() {
        String id = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingRoomService.fetchById(id);
        });
    }

    @Test
    public void update_Success_NotNull() {
        CodingRoomDto dto = new CodingRoomDto().setKey("1");

        given(codingRoomRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(mock(CodingRoom.class)));

        CodingRoomDto fetchDto = codingRoomService.update(dto);

        assertNotNull(fetchDto);
    }

    @Test
    public void deleteById_IsNotNumericId_ExceptionThrown(){
        String id = "A";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codingRoomService.deleteById(id);
        });
    }
}
