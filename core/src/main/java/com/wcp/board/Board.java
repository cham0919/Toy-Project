package com.wcp.board;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class Board{


    @Column(nullable = false)
    private String userKey;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime saveDate;

    @CreatedDate
    private LocalDateTime updateDate;

    @Column(nullable = false)
    private String category;

    @Column
    private Long count;

}
