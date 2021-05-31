package com.wcp.coding.test;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Accessors(chain = true)
public class MultiPartCodingTestDto extends CodingTestDto {

    private MultipartFile file;

}
