package com.wcp.coding.content;

import com.wcp.coding.board.CodingBoard;
import com.wcp.coding.board.CodingBoardRepository;
import com.wcp.page.PageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.wcp.page.Page.CODE_CONTENT_POST_COUNT;

@Component
@RequiredArgsConstructor
public class CodingContentManager implements CodingContentPersistenceManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CodingContentRepository codingContentRepository;

    @Override
    public CodingContent save(CodingContent codingBoard) {
        return codingContentRepository.save(codingBoard);
    }

    @Override
    public List<CodingContent> fetchByPage(int currentPage) {
        Page<CodingContent> codingContentPage = codingContentRepository
                .findAll(PageRequest
                        .of(currentPage - 1, CODE_CONTENT_POST_COUNT, Sort.by(Sort.Direction.ASC, "key")));
        return codingContentPage.getContent();
    }

    @Override
    public Optional<CodingContent> fetchById(Long id) {
        return codingContentRepository.findById(id);
    }

    @Override
    public List<CodingContent> fetchAll() {
        return codingContentRepository.findAll();
    }

    @Override
    @Transactional
    public CodingContent update(CodingContent codingContent) {
        Optional<CodingContent> fetchBoard = fetchById(codingContent.getKey());
        fetchBoard = Optional.of(codingContent);
        return fetchBoard.get();
    }

    @Override
    public CodingContent delete(CodingContent codingContent) {
        codingContentRepository.delete(codingContent);
        return codingContent;
    }

    @Override
    public void deleteById(Long id) {
        codingContentRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return codingContentRepository.count();
    }
}
