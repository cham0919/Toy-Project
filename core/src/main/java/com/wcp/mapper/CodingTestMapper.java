package com.wcp.mapper;

import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CodingTestMapper extends GenericMapper<CodingTestDto, CodingTest> {

    CodingTestMapper INSTANCE = Mappers.getMapper(CodingTestMapper.class);

}

