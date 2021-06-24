package com.wcp.mapper;

import com.wcp.coding.inputFile.CodeInputFile;
import com.wcp.coding.inputFile.CodeInputFileDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-24T02:11:18+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class CodeInputFileMapperImpl implements CodeInputFileMapper {

    @Override
    public CodeInputFileDto toDto(CodeInputFile e) {
        if ( e == null ) {
            return null;
        }

        CodeInputFileDto codeInputFileDto = new CodeInputFileDto();

        if ( e.getKey() != null ) {
            codeInputFileDto.setKey( String.valueOf( e.getKey() ) );
        }
        codeInputFileDto.setPath( e.getPath() );
        codeInputFileDto.setGivenName( e.getGivenName() );
        codeInputFileDto.setFileName( e.getFileName() );
        codeInputFileDto.setFileSize( e.getFileSize() );
        codeInputFileDto.setUploadAt( e.getUploadAt() );

        return codeInputFileDto;
    }

    @Override
    public CodeInputFile toEntity(CodeInputFileDto d) {
        if ( d == null ) {
            return null;
        }

        CodeInputFile codeInputFile = new CodeInputFile();

        if ( d.getKey() != null ) {
            codeInputFile.setKey( Long.parseLong( d.getKey() ) );
        }
        codeInputFile.setPath( d.getPath() );
        codeInputFile.setGivenName( d.getGivenName() );
        codeInputFile.setFileName( d.getFileName() );
        codeInputFile.setFileSize( d.getFileSize() );
        codeInputFile.setUploadAt( d.getUploadAt() );

        return codeInputFile;
    }

    @Override
    public void updateFromDto(CodeInputFileDto dto, CodeInputFile entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getKey() != null ) {
            entity.setKey( Long.parseLong( dto.getKey() ) );
        }
        if ( dto.getPath() != null ) {
            entity.setPath( dto.getPath() );
        }
        if ( dto.getGivenName() != null ) {
            entity.setGivenName( dto.getGivenName() );
        }
        if ( dto.getFileName() != null ) {
            entity.setFileName( dto.getFileName() );
        }
        if ( dto.getFileSize() != null ) {
            entity.setFileSize( dto.getFileSize() );
        }
        if ( dto.getUploadAt() != null ) {
            entity.setUploadAt( dto.getUploadAt() );
        }
    }
}
