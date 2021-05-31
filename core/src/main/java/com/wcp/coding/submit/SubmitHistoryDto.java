package com.wcp.coding.submit;

import com.wcp.coding.test.CodingTestDto;
import com.wcp.user.UserDto;

import java.time.LocalDateTime;

public class SubmitHistoryDto {

    private String key;
    private CodingTestDto codingTest;
    private UserDto user;
    private String status;
    private String language;
    private String code;
    private LocalDateTime submitAt;
    private String runTime;
}
