package me.will.crawler.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageParam extends BaseParam {
    protected Integer pageSize=10;
    protected Integer pageNum=1;
}
