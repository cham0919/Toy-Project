package com.wcp.mapper;

import com.wcp.board.Board;
import com.wcp.token.cert.CertificationToken;
import com.wcp.token.cert.CertificationTokenDto;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CertificationTokenMapper implements GenericMapper<CertificationTokenDto, CertificationToken>{

    public static final CertificationTokenMapper CERTIFICATION_TOKEN_MAPPER = Mappers.getMapper(CertificationTokenMapper.class);

    @BeforeMapping
    public void disconnectProxy(CertificationToken certificationToken) {
        if (!Hibernate.isInitialized(certificationToken.getUser())) {
            certificationToken.setUser(null);
        }
    }
}
