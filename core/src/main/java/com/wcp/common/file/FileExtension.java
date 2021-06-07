package com.wcp.common.file;

import com.wcp.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileExtension implements BaseEnum {

    ZIP("zip");

    private String fileExtension;


    @Override
    public boolean equalsValue(String value){
        return value.equals(getFileExtension());
    }

    @Override
    public boolean equalsIgnoreValue(String value){
        return value.equalsIgnoreCase(getFileExtension());
    }
}
