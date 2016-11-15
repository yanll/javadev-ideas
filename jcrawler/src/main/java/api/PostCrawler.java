package api;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by YANLL on 2016/10/19.
 */
public class PostCrawler extends BreadthCrawler {

    private static final Logger logger = LoggerFactory.getLogger(PostCrawler.class);


    public PostCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

    @Override
    public HttpResponse getResponse(CrawlDatum crawlDatum) throws Exception {
        HttpRequest request = new HttpRequest(crawlDatum.getUrl());
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.setMethod(crawlDatum.meta("method"));
        String outputData = crawlDatum.meta("data");
        System.out.println("POST Params:\n" + outputData);
        if (outputData != null) {
            request.setOutputData(outputData.getBytes("UTF-8"));
        }
        return request.getResponse();
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        HttpResponse response = page.getResponse();
        System.out.println("PostCrawler请求结果:\n" + response);
        System.out.println();
        System.out.println();
    }

    public static void post(String url, String params) {
        try {
            int i = 0;
            PostCrawler crawler = new PostCrawler("post_crawler", true);
            crawler.addSeed(new CrawlDatum(url).meta("method", "POST").meta("data", params));
            crawler.start(++i);
        } catch (Exception e) {
            logger.error("PostCrawler请求失败！", e);
        }
    }
}
