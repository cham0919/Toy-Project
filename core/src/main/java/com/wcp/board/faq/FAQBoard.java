package com.wcp.board.faq;

import com.wcp.board.Board;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class FAQBoard extends Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
