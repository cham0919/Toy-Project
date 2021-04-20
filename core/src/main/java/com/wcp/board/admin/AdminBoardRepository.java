package com.wcp.board.admin;

import com.wcp.board.main.MainBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminBoardRepository extends JpaRepository<AdminBoard, Long> {

}
