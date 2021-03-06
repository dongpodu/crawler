package me.will.crawler.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.will.crawler.dto.PageDto;
import me.will.crawler.entity.Job;
import me.will.crawler.mapper.JobMapper;
import me.will.crawler.param.PageJobParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class BossService {
    @Autowired
    private JobMapper jobMapper;

    public PageDto<Job> page(PageJobParam param){
        LambdaQueryWrapper<Job> query = new LambdaQueryWrapper<Job>()
                .orderByDesc(Job::getCompanyName);
        if(!StringUtils.isEmpty(param.getCompanyName())){
            query.like(Job::getCompanyName,param.getCompanyName());
        }

        IPage<Job> jobPage = jobMapper.selectPage(
                new Page<>(param.getPageNum(), param.getPageSize()),
                query
        );

        if (CollectionUtils.isEmpty(jobPage.getRecords())) {
            return PageDto.<Job>builder()
                    .total(0)
                    .build();
        }

        return PageDto.<Job>builder()
                .items(jobPage.getRecords())
                .total(jobPage.getTotal())
                .build();
    }

}
