package com.wcp.board;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class BoardDto {

    private Long seq;

    private String title;

    private String content;

    private LocalDateTime saveDate;

    private LocalDateTime updateDate;

    private String category;

    private Long count;

}
