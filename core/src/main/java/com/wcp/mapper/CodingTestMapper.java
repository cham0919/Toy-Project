package com.wcp.mapper;

import com.wcp.coding.room.CodingRoom;
import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestDto;
import org.hibernate.Hibernate;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class CodingTestMapper implements GenericMapper<CodingTestDto, CodingTest> {

    public static final CodingTestMapper CODING_TEST_MAPPER = Mappers.getMapper(CodingTestMapper.class);

    @BeforeMapping
    public void disconnectProxy(CodingTest codingTest) {
        if (!Hibernate.isInitialized(codingTest.getCodeInputFile())) {
            codingTest.setCodeInputFile(null);
        }
        if (!Hibernate.isInitialized(codingTest.getCodingRoom())) {
            codingTest.setCodingRoom(null);
        }
        if (!Hibernate.isInitialized(codingTest.getSumitHistories())) {
            codingTest.setSumitHistories(new ArrayList<>());
        }
        if (!Hibernate.isInitialized(codingTest.getUser())) {
            codingTest.setUser(null);
        }
    }

}

