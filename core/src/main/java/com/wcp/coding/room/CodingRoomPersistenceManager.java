package com.wcp.coding.room;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.wcp.page.Page.CODE_BOARD_POST_COUNT;

@Component
@RequiredArgsConstructor
public class CodingRoomPersistenceManager implements CodingRoomManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodingRoomRepository codingRoomRepository;

    @Override
    public CodingRoom save(CodingRoom codingRoom) {
        return codingRoomRepository.save(codingRoom);
    }

    @Override
    public List<CodingRoom> fetchByPage(String currentPage) {
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return fetchByPage(Integer.valueOf(currentPage));
    }

    @Override
    public List<CodingRoom> fetchByPage(int currentPage) {
        Page<CodingRoom> codingRoomPage = codingRoomRepository
                .findAll(PageRequest
                        .of(currentPage - 1, CODE_BOARD_POST_COUNT, Sort.by(Sort.Direction.ASC, "key")));
        return codingRoomPage.getContent();
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
