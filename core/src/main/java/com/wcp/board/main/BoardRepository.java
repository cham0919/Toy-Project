package com.wcp.board.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<MainBoard, Long> {

}
