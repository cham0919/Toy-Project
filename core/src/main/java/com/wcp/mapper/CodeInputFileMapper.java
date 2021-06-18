package com.wcp.mapper;

import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.inputFile.CodeInputFileDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CodeInputFileMapper extends GenericMapper<CodeInputFileDto, CodeInputFile> {

    CodeInputFileMapper CODE_INPUT_FILE_MAPPER = Mappers.getMapper(CodeInputFileMapper.class);

}

