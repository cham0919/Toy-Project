package com.wcp.coding.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodingRoomRepository extends JpaRepository<CodingRoom, Long>, CodingRoomRepositoryCustom  {

    List<CodingRoom> findAllBySecret(boolean secret);

}
