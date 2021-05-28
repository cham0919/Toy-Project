package com.wcp.mapper;

import com.wcp.token.CertificationToken;
import com.wcp.token.CertificationTokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CertificationTokenMapper extends GenericMapper<CertificationTokenDto, CertificationToken>{

    CertificationTokenMapper INSTANCE = Mappers.getMapper(CertificationTokenMapper.class);
}
