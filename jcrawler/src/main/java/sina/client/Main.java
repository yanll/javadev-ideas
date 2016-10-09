package sina.client;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by YANLL on 2016/06/29.
 */
public class Main {

    @Test
    public void sinaIV() throws Exception {


        String url = "http://weibo.com/u/3934389747?wvr=5&is_hot=1";
        WeiboCrawlerIV crawler = new WeiboCrawlerIV("depth_crawler", true);
        crawler.setExecuteInterval(5000);
        CrawlDatum crawlDatum = new CrawlDatum(url);
        crawler.addSeed(crawlDatum);
        /*不要爬取jpg|png|gif*/
        crawler.addRegex("-.*\\.(jpg|png|gif).*");
        /*不要爬取包含"#"的链接*/
        crawler.addRegex("-.*#.*");
        crawler.start(10);
        // 关闭线程池
        WeiboCrawlerIV.threadPool.shutdown();
    }

    @Test
    public void sinaIII() throws Exception {
        String url = "http://weibo.com/732943453/fans?current=fans";
        WeiboCrawlerIII crawler = new WeiboCrawlerIII("depth_crawler", true);
        crawler.setExecuteInterval(5000);
        CrawlDatum crawlDatum = new CrawlDatum(url);
        crawler.addSeed(crawlDatum);
        /*不要爬取jpg|png|gif*/
        crawler.addRegex("-.*\\.(jpg|png|gif).*");
        /*不要爬取包含"#"的链接*/
        crawler.addRegex("-.*#.*");
        crawler.start(5);
    }

    @Test
    public void sinaII() throws Exception {
        String url = "http://weibo.com/p/1005052714280233/follow?relate=fans&page=#$##Pl_Official_HisRelation__64&" + UUID.randomUUID().toString();
        WeiboCrawlerII crawler = new WeiboCrawlerII("depth_crawler", true);
        crawler.setExecuteInterval(5000);
        CrawlDatum crawlDatum = new CrawlDatum(url.replace("#$#", "2"));
        crawler.addSeed(crawlDatum.meta("pageindex", "2"));

        /*不要爬取jpg|png|gif*/
        crawler.addRegex("-.*\\.(jpg|png|gif).*");
        /*不要爬取包含"#"的链接*/
        crawler.addRegex("-.*#.*");
        crawler.start(5);
    }

    @Test
    public void sina() throws Exception {
        String url = "http://weibo.com/p/1005052714280233/follow?from=page_100505&wvr=6&mod=headfollow#place";
        CrawlDatum crawlDatum = new CrawlDatum(url);
        WeiboCrawler crawler = new WeiboCrawler("depth_crawler", true);
        crawler.setExecuteInterval(000);
        crawler.addSeed(crawlDatum.meta("usercard", "1005052714280233"));
        /*不要爬取jpg|png|gif*/
        crawler.addRegex("-.*\\.(jpg|png|gif).*");
        /*不要爬取包含"#"的链接*/
        crawler.addRegex("-.*#.*");
        crawler.setTopN(100);
        crawler.start(5);
    }

}
