package com.wcp.mapper;

import com.wcp.coding.submit.SubmitHistory;
import com.wcp.coding.submit.SubmitHistoryDto;
import com.wcp.coding.test.CodingTest;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class SubmitHistoryMapper implements GenericMapper<SubmitHistoryDto, SubmitHistory>{

    public static final SubmitHistoryMapper SUBMIT_HISTORY_MAPPER = Mappers.getMapper(SubmitHistoryMapper.class);

    @Override
    @Mappings({
            @Mapping(source = "language", target = "language_id"),
            @Mapping(source = "code", target = "source_code")
    })
    public abstract SubmitHistoryDto toDto(SubmitHistory submitHistory);

    @Override
    @Mappings({
            @Mapping(source = "language_id", target = "language"),
            @Mapping(source = "source_code", target = "code")
    })

    public abstract SubmitHistory toEntity(SubmitHistoryDto submitHistoryDto);

    @BeforeMapping
    public void disconnectProxy(SubmitHistory submitHistory) {
        if (!Hibernate.isInitialized(submitHistory.getCodingTest())) {
            submitHistory.setCodingTest(null);
        }
        if (!Hibernate.isInitialized(submitHistory.getUser())) {
            submitHistory.setUser(null);
        }
    }
}
