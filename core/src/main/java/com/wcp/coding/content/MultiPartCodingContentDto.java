package com.wcp.coding.content;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Accessors(chain = true)
public class MultiPartCodingContentDto extends CodingContentDto{

    private MultipartFile file;

}
