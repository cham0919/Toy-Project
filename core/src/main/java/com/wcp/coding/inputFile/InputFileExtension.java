package com.wcp.coding.inputFile;

import com.wcp.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InputFileExtension implements BaseEnum {

    IN("in"),
    OUT("out");

    private String InputFileExtension;


    @Override
    public boolean equalsValue(String value) {
        return value.equals(getInputFileExtension());
    }

    @Override
    public boolean equalsIgnoreValue(String value) {
        return value.equalsIgnoreCase(getInputFileExtension());
    }
}

