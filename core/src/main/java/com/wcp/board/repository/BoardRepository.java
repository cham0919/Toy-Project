package com.wcp.board.repository;

import com.wcp.board.entity.MainBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<MainBoard, Long> {

}
