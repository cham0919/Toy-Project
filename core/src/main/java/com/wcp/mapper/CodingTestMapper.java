package com.wcp.coding.content;

import com.wcp.common.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodingTestMapper extends GenericMapper<CodingTestDto, CodingTest> {

}

