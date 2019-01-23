package me.will.crawler.controller;

import me.will.crawler.dto.HttpResult;
import me.will.crawler.dto.PageDto;
import me.will.crawler.entity.Job;
import me.will.crawler.param.PageJobParam;
import me.will.crawler.service.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boss")
public class JobRestControlller {
    @Autowired
    private BossService bossService;

    @RequestMapping("page")
    public HttpResult page(PageJobParam param){
        PageDto<Job> page = bossService.page(param);
        return HttpResult.success(page);
    }

}
