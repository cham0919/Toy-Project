package com.wcp.coding.check.file;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Builder
public class CheckFileDto {

    private String key;
    private String contentId;
    private String path;
    private String givenName;
    private String fileName;
    private Long fileSize;
    private LocalDateTime uploadDatetime;

}
