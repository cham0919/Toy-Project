package com.wcp.coding.room;


import com.wcp.page.PageCalculator;
import com.wcp.page.PageCount;
import com.wcp.page.PageInfo;
import com.wcp.user.User;
import com.wcp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.wcp.mapper.CodingRoomMapper.CODING_ROOM_MAPPER;

@Service
@RequiredArgsConstructor
public class CodingRoomServiceImpl implements CodingRoomService{

    private final Logger log = LoggerFactory.getLogger(CodingRoomServiceImpl.class);
    private final CodingRoomRepository codingRoomRepository;
    private final UserRepository userRepository;
    private final PageCalculator pageCalculator;

    @Override
    public List<CodingRoomDto> fetchAllPublicRoom(){
        List<CodingRoom> codingRooms = codingRoomRepository.fetchAllPublicRoom();
        return parseCodingRoomDtos(codingRooms);
    }

    @Override
    public List<CodingRoomDto> fetchByPage(String currentPage) {
        return fetchByPage(Integer.parseInt(currentPage));
    }

    @Override
    @Transactional
    public CodingRoomDto save(CodingRoomDto dto, String userKey){
        CodingRoom codingRoom = CODING_ROOM_MAPPER.toEntity(dto);
        User user = getOneUserToProxy(Long.valueOf(userKey));
        codingRoom.setUser(user);
        codingRoom = save(codingRoom);
        return CODING_ROOM_MAPPER.toDto(codingRoom);
    }



    @Override
    public PageInfo fetchPageList(String currentPage){
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(Integer.parseInt(currentPage))
                .setTotalPostCount(count());
        return pageCalculator.fetchPageList(pageInfo, PageCount.CODING_ROOM);
    }

    @Override
    public List<CodingRoomDto> fetchByPage(int currentPage) {
        return codingRoomRepository.fetchByCurrentPage(currentPage);
    }

    @Override
    public CodingRoomDto save(CodingRoomDto dto) {
        CodingRoom codingRoom = CODING_ROOM_MAPPER.toEntity(dto);
        codingRoom = save(codingRoom);
        return CODING_ROOM_MAPPER.toDto(codingRoom);
    }

    @Override
    public CodingRoomDto fetchById(String id) {
        CodingRoom codingRoom = codingRoomRepository.fetchByIdJoinUser(Long.valueOf(id));
        return CODING_ROOM_MAPPER.toDto(codingRoom);
    }

    @Transactional(readOnly = true)
    public CodingRoom fetchById(Long id) {
        return codingRoomRepository.findById(id).get();
    }

    public List<CodingRoomDto> fetchAll() {
        List<CodingRoom> codingRooms = codingRoomRepository.findAll();
        List<CodingRoomDto> codingRoomDtos = parseCodingRoomDtos(codingRooms);
        return codingRoomDtos;
    }

    private List<CodingRoomDto> parseCodingRoomDtos(List<CodingRoom> codingRooms) {
        List<CodingRoomDto> codingRoomDtos = new ArrayList<>();
        codingRooms.forEach(v -> {
            codingRoomDtos.add(CODING_ROOM_MAPPER.toDto(v));
        });
        return codingRoomDtos;
    }

    @Transactional
    public CodingRoomDto update(CodingRoomDto dto) {
        CodingRoom fetchCodingRoom = fetchById(Long.valueOf(dto.getKey()));
        CODING_ROOM_MAPPER.updateFromDto(dto, fetchCodingRoom);
        return dto;
    }


    @Override
    public CodingRoomDto delete(CodingRoomDto dto) {
        CodingRoom codingRoom = CODING_ROOM_MAPPER.toEntity(dto);
        codingRoomRepository.delete(codingRoom);
        return dto;
    }

    @Override
    public void deleteById(String id){
        deleteById(Long.valueOf(id));
    }

    public void deleteById(Long id) {
        codingRoomRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codingRoomRepository.count();
    }

    private User getOneUserToProxy(Long userKey) {
        return userRepository.getOne(userKey);
    }

    private CodingRoom save(CodingRoom codingRoom) {
        return codingRoomRepository.save(codingRoom);
    }
}
