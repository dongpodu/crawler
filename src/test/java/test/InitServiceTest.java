package test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jdk.nashorn.internal.scripts.JO;
import lombok.extern.slf4j.Slf4j;
import me.will.crawler.BossCrawler;
import me.will.crawler.entity.Job;
import me.will.crawler.mapper.JobMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by duyisong on 30/11/2018.
 */
@Slf4j
public class InitServiceTest extends BaseTest {
    @Autowired
    private JobMapper jobMapper;

    @Test
    public void test() throws Exception {
        BossCrawler bossCrawler = new BossCrawler();
        int pageStart=1;
        for(int page=1;page<10;page++){
            List<Job> jobList = bossCrawler.getJobList(pageStart,pageStart+9,"风控");
            jobList.forEach(r ->{
                jobMapper.insert(r);
            });
            pageStart = pageStart+10;
        }
    }

    @Test
    public void test1() throws Exception {
        List<Job> list = jobMapper.selectList(
                new LambdaQueryWrapper<Job>()
                .isNull(Job::getJobDescription)
        );

        Job update = new Job();
        BossCrawler bossCrawler = new BossCrawler();
        for(Job job:list){
            String jobDemand = bossCrawler.getJobDemand(job.getDetailUrl());
            update.setId(job.getId());
            update.setJobDescription(jobDemand);
            jobMapper.updateById(update);
        }
    }


}
