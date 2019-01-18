package me.will.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

public class MyFetcher extends PageFetcher {
    public MyFetcher(CrawlConfig config) {
        super(config);
    }

    protected HttpUriRequest newHttpUriRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/604.1.34 (KHTML, like Gecko) CriOS/64.0.3282.112 Mobile/15G77 Safari/604.1");
        return httpGet;
    }
}
