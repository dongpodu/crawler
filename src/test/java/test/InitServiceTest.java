package test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import me.will.crawler.BossCrawler;
import me.will.crawler.entity.Job;
import me.will.crawler.enums.JobTypeEnum;
import me.will.crawler.mapper.JobMapper;
import org.apache.commons.lang.StringUtils;
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

    /**
     * 拉取列表
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        BossCrawler bossCrawler = new BossCrawler();
        for(int page=1;page<400;){
            Thread.sleep(2000);
            List<Job> jobList = bossCrawler.getJobList(page,page+9,"java");
            jobList.forEach(r ->{
                try {
                    r.setType(JobTypeEnum.java.getCode());
                    jobMapper.insert(r);
                }catch (Exception e){

                }
            });
            page = page+10;
        }
    }

    /**
     * 拉取详情
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        List<Job> list = jobMapper.selectList(
                new LambdaQueryWrapper<Job>()
                        .eq(Job::getType,JobTypeEnum.java.getCode())
                .isNull(Job::getJobDescription)
        );

        Job update = new Job();
        BossCrawler bossCrawler = new BossCrawler();
        for(Job job:list){
            String jobDemand = bossCrawler.getJobDemand(job.getDetailUrl());
            Thread.sleep(2000);
            if(StringUtils.isNotBlank(jobDemand)){
                update.setId(job.getId());
                update.setJobDescription(jobDemand);
                jobMapper.updateById(update);
            }
        }
    }


}
