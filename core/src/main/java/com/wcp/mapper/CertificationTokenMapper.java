package com.wcp.mapper;

import com.wcp.token.cert.CertificationToken;
import com.wcp.token.cert.CertificationTokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CertificationTokenMapper extends GenericMapper<CertificationTokenDto, CertificationToken>{

    CertificationTokenMapper CERTIFICATION_TOKEN_MAPPER = Mappers.getMapper(CertificationTokenMapper.class);
}
