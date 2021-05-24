package com.wcp.category;


import com.wcp.WCPTable.BoardCategoryTable;
import com.wcp.board.Board;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = BoardCategoryTable.TABLE_NAME)
public class BoardCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BoardCategoryTable.PK)
    private Long key;

    @Column(name = BoardCategoryTable.NAME)
    private String name;


}
