package com.wcp.coding.check.file;

import com.wcp.common.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodeInputFileMapper extends GenericMapper<CodeInputFileDto, CodeInputFile> {

}

