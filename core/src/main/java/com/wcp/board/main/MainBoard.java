package com.wcp.board.main;

import com.wcp.board.Board;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class MainBoard extends Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reply;

}
