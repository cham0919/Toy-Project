package com.wcp.coding.room;

import com.wcp.WCPTable;
import com.wcp.mapper.CodingRoomMapper;
import com.wcp.page.PageCalculator;
import com.wcp.page.PageCount;
import com.wcp.page.PageInfo;
import com.wcp.user.User;
import com.wcp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import static com.wcp.WCPTable.CodingRoomTable.*;


@Service
@RequiredArgsConstructor
public class CodingRoomServiceImpl implements CodingRoomService{

    private final Logger log = LoggerFactory.getLogger(CodingRoomServiceImpl.class);
    private final CodingRoomRepository codingRoomRepository;
    private final UserRepository userRepository;
    private final PageCalculator pageCalculator;


    @Override
    public CodingRoomDto fetchDtoById(String id){
        CodingRoom codingRoom = fetchById(id).get();
        return CodingRoomMapper.INSTANCE.toDto(codingRoom);
    }

    @Transactional
    public CodingRoom save(CodingRoom codingRoom, String userKey){
        if (StringUtils.isEmpty(userKey) || !StringUtils.isNumeric(userKey)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check userKey : "+ userKey);
        }
        User user = userRepository.getOne(Long.valueOf(userKey));
        codingRoom.setUser(user);
        return save(codingRoom);
    }


    @Override
    public List<CodingRoom> fetchByPage(String currentPage) {
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check currentPage : " + currentPage);
        }
        return fetchByPage(Integer.valueOf(currentPage));
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
    public List<CodingRoom> fetchByPage(int currentPage) {
        Page<CodingRoom> codingRoomPage = codingRoomRepository
                .findAll(PageRequest
                        .of(currentPage - 1, PageCount.CODING_ROOM.getPostCount(), Sort.by(Sort.Direction.ASC, PK)));
        return codingRoomPage.getContent();
    }

    @Override
    public CodingRoom save(CodingRoom codingRoom) {
        return codingRoomRepository.save(codingRoom);
    }

    @Override
    public Optional<CodingRoom> fetchById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        return fetchById(Long.valueOf(id));
    }

    @Override
    public Optional<CodingRoom> fetchById(Long id) {
        return codingRoomRepository.findById(id);
    }

    @Override
    public List<CodingRoom> fetchAll() {
        return codingRoomRepository.findAll();
    }

    @Override
    @Transactional
    public CodingRoom update(CodingRoom codingRoom) {
        Optional<CodingRoom> fetchCodingRoom = fetchById(codingRoom.getKey());
        fetchCodingRoom = Optional.of(codingRoom);
        return fetchCodingRoom.get();
    }

    @Override
    public CodingRoom delete(CodingRoom codingRoom) {
        codingRoomRepository.delete(codingRoom);
        return codingRoom;
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
        codingRoomRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codingRoomRepository.count();
    }
}
