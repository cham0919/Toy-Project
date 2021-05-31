package com.wcp.coding.inputFile;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CodeInputFileDto {

    private String key;
    private String contentId;
    private String path;
    private String givenName;
    private String fileName;
    private Long fileSize;
    private LocalDateTime uploadAt;

}
