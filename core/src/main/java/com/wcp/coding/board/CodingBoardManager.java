package com.wcp.coding.board;

import com.wcp.page.PageService;
import com.wcp.user.User;
import com.wcp.user.UserPersistenceManager;
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
public class CodingBoardManager implements CodingBoardPersistenceManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodingBoardRepository codingBoardRepository;

    @Override
    public CodingBoard save(CodingBoard codingBoard) {
        return codingBoardRepository.save(codingBoard);
    }

    @Override
    public List<CodingBoard> fetchByPage(String currentPage) {
        if (StringUtils.isEmpty(currentPage) || !StringUtils.isNumeric(currentPage)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check currentPage : "+ currentPage);
        }
        return fetchByPage(Integer.valueOf(currentPage));
    }

    @Override
    public List<CodingBoard> fetchByPage(int currentPage) {
        Page<CodingBoard> codingBoardPage = codingBoardRepository
                .findAll(PageRequest
                        .of(currentPage - 1, CODE_BOARD_POST_COUNT, Sort.by(Sort.Direction.ASC, "key")));
        return codingBoardPage.getContent();
    }

    @Override
    public Optional<CodingBoard> fetchById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        return fetchById(Long.valueOf(id));
    }

    @Override
    public Optional<CodingBoard> fetchById(Long id) {
        return codingBoardRepository.findById(id);
    }

    @Override
    public List<CodingBoard> fetchAll() {
        return codingBoardRepository.findAll();
    }

    @Override
    @Transactional
    public CodingBoard update(CodingBoard codingBoard) {
        Optional<CodingBoard> fetchBoard = fetchById(codingBoard.getKey());
        fetchBoard = Optional.of(codingBoard);
        return fetchBoard.get();
    }

    @Override
    public CodingBoard delete(CodingBoard codingBoard) {
        codingBoardRepository.delete(codingBoard);
        return codingBoard;
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
        codingBoardRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codingBoardRepository.count();
    }
}
