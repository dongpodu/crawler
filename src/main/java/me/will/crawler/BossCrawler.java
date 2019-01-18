package me.will.crawler;

import com.fasterxml.jackson.databind.type.TypeFactory;
import me.will.crawler.dto.BossJobListDto;
import me.will.crawler.entity.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class BossCrawler {
    public static final String BASE_URL="https://m.zhipin.com";

    public List<Job> getJobList(Integer pageStart,String query) throws IOException {
        String url = BASE_URL + "mobile/jobs.json?&city=101020100"
                +"&query="+ URLEncoder.encode(query,"utf-8")
                +"&page="+pageStart;

        boolean hasMore = true;
        TypeFactory typeFactory = MyObjectMapper.getObjectMapper().getTypeFactory();
        while (hasMore){
            BossJobListDto bossJobList = MyObjectMapper.getObjectMapper().readValue(url,BossJobListDto.class);
            hasMore = bossJobList.getHasMore();
            Document document = Jsoup.parse(bossJobList.getHtml());
            Elements elements = document.getElementsByClass("item");
            List<BossJobListDto> list = MyObjectMapper.getObjectMapper().readValue(bossJobList.getHtml(),typeFactory.constructCollectionType(List.class, BossJobListDto.class));
        }
        return null;
    }

}
