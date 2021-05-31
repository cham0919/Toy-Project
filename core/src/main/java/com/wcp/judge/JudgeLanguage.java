package com.wcp.judge;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JudgeLanguage {

    C("50"),
    CPP("54"),
    CLOJURE("86"),
    CSHARP("51"),
    GO("60"),
    JAVA("62"),
    JAVASCRIPT("63"),
    KOTLIN("78"),
    PYTHON("71"),
    R("80"),
    RUBY("72"),
    RUST("73"),
    SCALA("81"),
    SQL("82"),
    SWIFT("83"),
    TYPESCRIPT("74");

    private String value;


}
