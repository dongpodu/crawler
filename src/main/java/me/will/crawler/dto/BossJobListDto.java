package me.will.crawler.dto;

import lombok.Data;

@Data
public class BossJobListDto {
    private Boolean hasMore;
    private String html;
    private Integer page;
}
