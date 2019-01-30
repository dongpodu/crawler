package me.will.crawler.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum JobTypeEnum {
    风控(1),
    java(5);
    @Getter
    private final Integer code;
}
