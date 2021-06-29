package com.wcp.mapper;

import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.room.CodingRoomDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-26T23:02:46+0900",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.2.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class CodingRoomMapperImpl extends CodingRoomMapper {

    @Override
    public CodingRoomDto toDto(CodingRoom e) {
        disconnectProxy( e );

        if ( e == null ) {
            return null;
        }

        CodingRoomDto codingRoomDto = new CodingRoomDto();

        if ( e.getKey() != null ) {
            codingRoomDto.setKey( String.valueOf( e.getKey() ) );
        }
        codingRoomDto.setTitle( e.getTitle() );
        codingRoomDto.setIntro( e.getIntro() );
        codingRoomDto.setSecret( e.isSecret() );
        codingRoomDto.setPassword( e.getPassword() );
        codingRoomDto.setMaxUser( e.getMaxUser() );
        codingRoomDto.setRamdomKey( e.getRamdomKey() );
        codingRoomDto.setCreatedAt( e.getCreatedAt() );
        codingRoomDto.setJoinUsersCount( (long) e.getJoinUsersCount() );
        codingRoomDto.setCodingTestCount( (long) e.getCodingTestCount() );

        return codingRoomDto;
    }

    @Override
    public CodingRoom toEntity(CodingRoomDto d) {
        if ( d == null ) {
            return null;
        }

        CodingRoom codingRoom = new CodingRoom();

        if ( d.getKey() != null ) {
            codingRoom.setKey( Long.parseLong( d.getKey() ) );
        }
        codingRoom.setTitle( d.getTitle() );
        codingRoom.setIntro( d.getIntro() );
        codingRoom.setSecret( d.isSecret() );
        codingRoom.setPassword( d.getPassword() );
        codingRoom.setMaxUser( d.getMaxUser() );
        codingRoom.setRamdomKey( d.getRamdomKey() );
        codingRoom.setCreatedAt( d.getCreatedAt() );

        return codingRoom;
    }

    @Override
    public void updateFromDto(CodingRoomDto dto, CodingRoom entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getKey() != null ) {
            entity.setKey( Long.parseLong( dto.getKey() ) );
        }
        if ( dto.getTitle() != null ) {
            entity.setTitle( dto.getTitle() );
        }
        if ( dto.getIntro() != null ) {
            entity.setIntro( dto.getIntro() );
        }
        entity.setSecret( dto.isSecret() );
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getMaxUser() != null ) {
            entity.setMaxUser( dto.getMaxUser() );
        }
        if ( dto.getRamdomKey() != null ) {
            entity.setRamdomKey( dto.getRamdomKey() );
        }
        if ( dto.getCreatedAt() != null ) {
            entity.setCreatedAt( dto.getCreatedAt() );
        }
    }
}
