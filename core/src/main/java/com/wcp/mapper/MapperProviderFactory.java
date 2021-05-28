package com.wcp.mapper;

import org.springframework.stereotype.Component;

@Component
public class MapperProviderFactory implements MapperFactory{

    @Override
    public GenericMapper create(MapperEnum mapper) {
        if(mapper.equals(MapperEnum.BOARD)){
            return BoardMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.BOARDCATEGORY)){
            return BoardCategoryMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.BOARDCOMMANT)){
            return BoardCommantMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.BOARDLIKE)){
            return BoardLikeMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.CERTIFICATIONTOKEN)){
            return CertificationTokenMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.CODEINPUTFILE)){
            return CodeInputFileMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.CODINGJOINUSER)){
            return CodingJoinUserMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.CODINGROOM)){
            return CodingRoomMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.CODINGTEST)){
            return CodingTestMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.SUBMITHISTORY)){
            return SubmitHistoryMapper.INSTANCE;
        }else if(mapper.equals(MapperEnum.USER)){
            return UserMapper.INSTANCE;
        }else{
            return null;
        }
    }
}
