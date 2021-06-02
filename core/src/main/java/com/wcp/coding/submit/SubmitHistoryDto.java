package com.wcp.coding.submit;

import com.wcp.coding.test.CodingTestDto;
import com.wcp.user.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class SubmitHistoryDto {

    private String key;
    private CodingTestDto codingTest;
    private UserDto user;
    private String status;
    private String language_id;
    private String source_code;
    private LocalDateTime submitAt;
    private String runTime;
}
