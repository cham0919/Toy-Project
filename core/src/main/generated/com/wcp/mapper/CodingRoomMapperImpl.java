package com.wcp.mapper;

import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-25T18:03:19+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class CodingRoomMapperImpl extends CodingRoomMapper {

    @Override
    public CodingRoomDto toDto(CodingRoom arg0) {
        disconnectProxy( arg0 );

        if ( arg0 == null ) {
            return null;
        }

        CodingRoomDto codingRoomDto = new CodingRoomDto();

        if ( arg0.getKey() != null ) {
            codingRoomDto.setKey( String.valueOf( arg0.getKey() ) );
        }
        codingRoomDto.setTitle( arg0.getTitle() );
        codingRoomDto.setIntro( arg0.getIntro() );
        codingRoomDto.setSecret( arg0.isSecret() );
        codingRoomDto.setPassword( arg0.getPassword() );
        codingRoomDto.setMaxUser( arg0.getMaxUser() );
        codingRoomDto.setRamdomKey( arg0.getRamdomKey() );
        codingRoomDto.setCreatedAt( arg0.getCreatedAt() );
        codingRoomDto.setJoinUsersCount( (long) arg0.getJoinUsersCount() );
        codingRoomDto.setCodingTestCount( (long) arg0.getCodingTestCount() );

        return codingRoomDto;
    }

    @Override
    public CodingRoom toEntity(CodingRoomDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        CodingRoom codingRoom = new CodingRoom();

        if ( arg0.getKey() != null ) {
            codingRoom.setKey( Long.parseLong( arg0.getKey() ) );
        }
        codingRoom.setTitle( arg0.getTitle() );
        codingRoom.setIntro( arg0.getIntro() );
        codingRoom.setSecret( arg0.isSecret() );
        codingRoom.setPassword( arg0.getPassword() );
        codingRoom.setMaxUser( arg0.getMaxUser() );
        codingRoom.setRamdomKey( arg0.getRamdomKey() );
        codingRoom.setCreatedAt( arg0.getCreatedAt() );

        return codingRoom;
    }

    @Override
    public void updateFromDto(CodingRoomDto arg0, CodingRoom arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getKey() != null ) {
            arg1.setKey( Long.parseLong( arg0.getKey() ) );
        }
        if ( arg0.getTitle() != null ) {
            arg1.setTitle( arg0.getTitle() );
        }
        if ( arg0.getIntro() != null ) {
            arg1.setIntro( arg0.getIntro() );
        }
        arg1.setSecret( arg0.isSecret() );
        if ( arg0.getPassword() != null ) {
            arg1.setPassword( arg0.getPassword() );
        }
        if ( arg0.getMaxUser() != null ) {
            arg1.setMaxUser( arg0.getMaxUser() );
        }
        if ( arg0.getRamdomKey() != null ) {
            arg1.setRamdomKey( arg0.getRamdomKey() );
        }
        if ( arg0.getCreatedAt() != null ) {
            arg1.setCreatedAt( arg0.getCreatedAt() );
        }
    }
}
