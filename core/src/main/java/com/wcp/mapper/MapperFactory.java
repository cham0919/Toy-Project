package com.wcp.mapper;

public interface MapperFactory {

    <T extends GenericMapper> T create(MapperEnum mapperEnum);

}
