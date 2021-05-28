package com.wcp.mapper;

import com.wcp.coding.submit.SubmitHistory;
import com.wcp.coding.submit.SubmitHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubmitHistoryMapper extends GenericMapper<SubmitHistoryDto, SubmitHistory>{

    SubmitHistoryMapper INSTANCE = Mappers.getMapper(SubmitHistoryMapper.class);
}
