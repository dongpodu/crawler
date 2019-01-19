package me.will.crawler;

import me.will.crawler.dto.BossJobListDto;
import me.will.crawler.entity.Job;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BossCrawler {
    public static final String BASE_URL="https://m.zhipin.com";
    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static HttpGet httpGet = new HttpGet();
    static {
        httpGet.addHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/604.1.34 (KHTML, like Gecko) CriOS/64.0.3282.112 Mobile/15G77 Safari/604.1");
    }

    public List<Job> getJobList(Integer pageStart,Integer pageEnd,String query) throws Exception {
        boolean hasMore = true;
        List<Job> list = new ArrayList<>();
        int page=pageStart;
        while (hasMore){
            if(page>=pageEnd){
                break;
            }
            String url = BASE_URL + "/mobile/jobs.json?&city=101020100"
                    +"&query="+ URLEncoder.encode(query,"utf-8")
                    +"&page="+pageStart;
            String result = request(url);

            BossJobListDto bossJobList = MyObjectMapper.getObjectMapper().readValue(result,BossJobListDto.class);
            hasMore = bossJobList.getHasMore();
            Document document = Jsoup.parse(bossJobList.getHtml());
            Elements elements = document.getElementsByClass("item");
            Iterator<Element> it = elements.listIterator();
            while (it.hasNext()) {
                Element element = it.next();
                Element detailUrl = element.getElementsByTag("a").first();
                Element company = element.getElementsByClass("name").first();
                Element salary = element.getElementsByClass("salary").first();
                Element tag = element.getElementsByClass("msg").first();
                Element title = element.getElementsByClass("title").first();
                Job job = new Job();
                job.setDetailUrl(detailUrl.attr("href"));
                job.setCompanyName(company.text());
                job.setSalary(salary.text());
                job.setTitle(title.text());
                job.setTag(tag.text());
                list.add(job);
            }
            page++;
        }
        return list;
    }


    public String getJobDemand(String detailUrl) throws Exception {
        String url = BossCrawler.BASE_URL+detailUrl;
        String result = request(url);
        Document document = Jsoup.parse(result);
        Element element = document.getElementsByClass("job-sec").first();
        return element==null?null:element.text();
    }


    public String request(String url) throws Exception{
        httpGet.setURI(new URI(url));
        CloseableHttpResponse response = httpClient.execute(httpGet);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        response.close();
        return wholeStr;
    }


    public static void main(String[] args) throws Exception {
        List<Job> list = new BossCrawler().getJobList(1,3,"风控");
        System.out.println(list);
    }

}
