package me.will.crawler.param;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class PageJobParam extends PageParam {
    private String companyName;
}
