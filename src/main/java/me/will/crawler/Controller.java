package me.will.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Controller {
    public static void main(String[] args) throws Exception {
        test1();
    }


    public static void test1() throws Exception {
        String crawStorageFolder="d:/crawler";
        int numberOfCrawlers=1;
        CrawlConfig config=new CrawlConfig();
        config.setCrawlStorageFolder(crawStorageFolder);

        PageFetcher pageFetcher=new MyFetcher(config);
        RobotstxtConfig robotstxtconfig=new RobotstxtConfig();
        //实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件
        //规定了该网站哪些页面可以爬，哪些页面禁止爬，该类是对robots.txt规范的实现
        RobotstxtServer robotstxtServer=new RobotstxtServer(robotstxtconfig,pageFetcher);
        CrawlController controller=new CrawlController(config,pageFetcher,robotstxtServer);
        //配置爬取种子页面，就是规定从哪里开始爬，可以配置多个种子页面
        controller.addSeed("https://m.zhipin.com/job_detail?city=101020100&source=10&query=风控");
//        controller.addSeed("https://m.zhipin.com");
        controller.start(MyCrawler.class,numberOfCrawlers);
    }
}
