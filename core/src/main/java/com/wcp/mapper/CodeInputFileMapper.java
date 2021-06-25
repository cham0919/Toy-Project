package com.wcp.mapper;

import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.inputFile.CodeInputFileDto;
import com.wcp.token.cert.CertificationToken;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class CodeInputFileMapper implements GenericMapper<CodeInputFileDto, CodeInputFile> {

    public static final CodeInputFileMapper CODE_INPUT_FILE_MAPPER = Mappers.getMapper(CodeInputFileMapper.class);

    @BeforeMapping
    public void disconnectProxy(CodeInputFile codeInputFile) {
        if (!Hibernate.isInitialized(codeInputFile.getCodingTest())) {
            codeInputFile.setCodingTest(null);
        }
    }
}

