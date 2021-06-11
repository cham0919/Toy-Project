package com.wcp.common.file;

import com.wcp.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MimeType implements BaseEnum {

    ZIP("application/zip");

    private String type;


    @Override
    public boolean equalsValue(String value) {
        return value.equals(getType());
    }

    @Override
    public boolean equalsIgnoreValue(String value) {
        return value.equalsIgnoreCase(getType());
    }
}