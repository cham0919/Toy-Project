package com.wcp.mapper;

import com.wcp.coding.submit.SubmitHistory;
import com.wcp.coding.submit.SubmitHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubmitHistoryMapper extends GenericMapper<SubmitHistoryDto, SubmitHistory>{

    SubmitHistoryMapper SUBMIT_HISTORY_MAPPER = Mappers.getMapper(SubmitHistoryMapper.class);

    @Override
    @Mappings({
            @Mapping(source = "language", target = "language_id"),
            @Mapping(source = "code", target = "source_code")
    })
    SubmitHistoryDto toDto(SubmitHistory submitHistory);

    @Override
    @Mappings({
            @Mapping(source = "language_id", target = "language"),
            @Mapping(source = "source_code", target = "code")
    })

    SubmitHistory toEntity(SubmitHistoryDto submitHistoryDto);
}
