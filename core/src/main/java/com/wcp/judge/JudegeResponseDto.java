package com.wcp.judge;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class JudegeResponseDto {

    private String token;
    private String stdout;
    private String time;
    private String stderr;
    private String message;
    private Map status;


}
