package com.wcp.mapper;

import com.wcp.token.CertificationToken;
import com.wcp.token.CertificationTokenDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-31T13:25:46+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class CertificationTokenMapperImpl implements CertificationTokenMapper {

    @Override
    public CertificationTokenDto toDto(CertificationToken e) {
        if ( e == null ) {
            return null;
        }

        CertificationTokenDto certificationTokenDto = new CertificationTokenDto();

        return certificationTokenDto;
    }

    @Override
    public CertificationToken toEntity(CertificationTokenDto d) {
        if ( d == null ) {
            return null;
        }

        CertificationToken certificationToken = new CertificationToken();

        return certificationToken;
    }

    @Override
    public void updateFromDto(CertificationTokenDto dto, CertificationToken entity) {
        if ( dto == null ) {
            return;
        }
    }
}