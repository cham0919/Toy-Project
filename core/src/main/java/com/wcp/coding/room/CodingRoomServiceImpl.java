package com.wcp.coding.room;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wcp.page.PageCalculator;
import com.wcp.page.PageCount;
import com.wcp.page.PageInfo;
import com.wcp.user.User;
import com.wcp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.wcp.coding.join.QCodingJoinUser.codingJoinUser;
import static com.wcp.coding.room.QCodingRoom.codingRoom;
import static com.wcp.coding.test.QCodingTest.codingTest;
import static com.wcp.mapper.CodingRoomMapper.CODING_ROOM_MAPPER;

@Service
@RequiredArgsConstructor
public class CodingRoomServiceImpl implements CodingRoomService{

    private final Logger log = LoggerFactory.getLogger(CodingRoomServiceImpl.class);
    private final JPAQueryFactory queryFactory;
    private final CodingRoomRepository codingRoomRepository;
    private final UserRepository userRepository;
    private final PageCalculator pageCalculator;

    @Override
    public List<CodingRoomDto> fetchAllPublicRoom(){
        List<CodingRoom> codingRooms = queryFactory
                .selectFrom(codingRoom)
                .leftJoin(codingRoom.codingJoinUsers)
                .fetchJoin()
                .where(codingRoom.secret.eq(false))
                .fetch();
        return parseCodingRoomDtos(codingRooms);
    }

    @Override
    @Transactional
    public CodingRoomDto save(CodingRoomDto dto, String userKey){
        if (StringUtils.isEmpty(userKey) || !StringUtils.isNumeric(userKey)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check userKey : "+ userKey);
        }
        CodingRoom codingRoom = CODING_ROOM_MAPPER.toEntity(dto);
        User user = userRepository.getOne(Long.valueOf(userKey));
        codingRoom.setUser(user);
        codingRoom = codingRoomRepository.save(codingRoom);
        return CODING_ROOM_MAPPER.toDto(codingRoom);
    }


    @Override
    public List<CodingRoomDto> fetchByPage(String currentPage) {
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check currentPage : " + currentPage);
        }
        List<CodingRoomDto> codingRoomDtos = fetchByPage(Integer.valueOf(currentPage));
        return codingRoomDtos;
    }

    @Override
    public PageInfo fetchPageList(String currentPage){
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        PageInfo pageInfo = PageInfo.of()
                .setCurrentPage(Integer.valueOf(currentPage))
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
        codingRoom = codingRoomRepository.save(codingRoom);
        return CODING_ROOM_MAPPER.toDto(codingRoom);
    }

    @Override
    public CodingRoomDto fetchById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        CodingRoom codingRoom = fetchById(Long.valueOf(id));
        return CODING_ROOM_MAPPER.toDto(codingRoom);
    }

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
        String id = dto.getKey();
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        CodingRoom fetchCodingRoom = fetchById(Long.valueOf(id));
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
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        deleteById(Long.valueOf(id));
    }

    public void deleteById(Long id) {
        codingRoomRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codingRoomRepository.count();
    }
}
