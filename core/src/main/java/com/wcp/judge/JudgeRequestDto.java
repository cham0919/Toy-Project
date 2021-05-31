package com.wcp.judge;


import lombok.Data;
import lombok.experimental.Accessors;

import java.net.URI;

@Data
@Accessors(chain = true)
public class JudgeRequestDto {

    private URI uri;
    private int language_id;
    private String source_code;
    private String stdin;
    private String expected_output;
}
