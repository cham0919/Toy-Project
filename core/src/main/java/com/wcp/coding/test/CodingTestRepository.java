package com.wcp.coding.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingTestRepository extends JpaRepository<CodingTest, Long>, CodingTestJPQLRepository {

    CodingTest findByKeyAndUserKey(Long key, Long userKey);

    Long countByCodingRoom_Key(Long key);

}
