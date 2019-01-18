package me.will.crawler.service;

import me.will.crawler.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BossService {
    @Autowired
    private JobMapper jobMapper;


}
