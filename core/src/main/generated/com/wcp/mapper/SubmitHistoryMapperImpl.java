package com.wcp.mapper;

import com.wcp.coding.submit.SubmitHistory;
import com.wcp.coding.submit.SubmitHistoryDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-31T13:25:46+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class SubmitHistoryMapperImpl implements SubmitHistoryMapper {

    @Override
    public SubmitHistoryDto toDto(SubmitHistory e) {
        if ( e == null ) {
            return null;
        }

        SubmitHistoryDto submitHistoryDto = new SubmitHistoryDto();

        return submitHistoryDto;
    }

    @Override
    public SubmitHistory toEntity(SubmitHistoryDto d) {
        if ( d == null ) {
            return null;
        }

        SubmitHistory submitHistory = new SubmitHistory();

        return submitHistory;
    }

    @Override
    public void updateFromDto(SubmitHistoryDto dto, SubmitHistory entity) {
        if ( dto == null ) {
            return;
        }
    }
}
