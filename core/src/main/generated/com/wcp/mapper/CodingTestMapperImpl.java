package com.wcp.mapper;

import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-24T02:11:18+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class CodingTestMapperImpl implements CodingTestMapper {

    @Override
    public CodingTestDto toDto(CodingTest e) {
        if ( e == null ) {
            return null;
        }

        CodingTestDto codingTestDto = new CodingTestDto();

        if ( e.getKey() != null ) {
            codingTestDto.setKey( String.valueOf( e.getKey() ) );
        }
        codingTestDto.setTitle( e.getTitle() );
        codingTestDto.setContent( e.getContent() );
        codingTestDto.setLanguage( e.getLanguage() );
        codingTestDto.setAuth( e.getAuth() );
        codingTestDto.setSubmitAt( e.getSubmitAt() );

        return codingTestDto;
    }

    @Override
    public CodingTest toEntity(CodingTestDto d) {
        if ( d == null ) {
            return null;
        }

        CodingTest codingTest = new CodingTest();

        if ( d.getKey() != null ) {
            codingTest.setKey( Long.parseLong( d.getKey() ) );
        }
        codingTest.setTitle( d.getTitle() );
        codingTest.setContent( d.getContent() );
        codingTest.setLanguage( d.getLanguage() );
        codingTest.setAuth( d.getAuth() );
        codingTest.setSubmitAt( d.getSubmitAt() );

        return codingTest;
    }

    @Override
    public void updateFromDto(CodingTestDto dto, CodingTest entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getKey() != null ) {
            entity.setKey( Long.parseLong( dto.getKey() ) );
        }
        if ( dto.getTitle() != null ) {
            entity.setTitle( dto.getTitle() );
        }
        if ( dto.getContent() != null ) {
            entity.setContent( dto.getContent() );
        }
        if ( dto.getLanguage() != null ) {
            entity.setLanguage( dto.getLanguage() );
        }
        if ( dto.getAuth() != null ) {
            entity.setAuth( dto.getAuth() );
        }
        if ( dto.getSubmitAt() != null ) {
            entity.setSubmitAt( dto.getSubmitAt() );
        }
    }
}
