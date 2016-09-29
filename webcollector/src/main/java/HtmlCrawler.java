import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.util.FileSystemOutput;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;

import java.io.File;

/**
 * Created by YANLL on 2016/06/29.
 */
public class HtmlCrawler extends BreadthCrawler {

    FileSystemOutput fsOutput = new FileSystemOutput("dd_download");

    public HtmlCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }


    @Override
    public HttpResponse getResponse(CrawlDatum crawlDatum) throws Exception {
        HttpRequest request = new HttpRequest(crawlDatum.getUrl());
        /*request.setCookie("");*/
        return request.getResponse();
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        fsOutput.output(page);
    }

    @Override
    protected void afterParse(Page page, CrawlDatums next) {

    }

    public static void main(String[] args) throws Exception {
        File downloadDir = new File("dd_download");
        if (downloadDir.exists()) {
            FileUtils.deleteDir(downloadDir);
        }

        HtmlCrawler crawler = new HtmlCrawler("dd_crawl", true);
        crawler.addSeed("http://m.dangdang.com");
        crawler.addRegex("http://m.dangdang.com/.*");
        //crawler.addExtractor(".*", HtmlExtractor.class, new ExtractorParams("fsOutput", fsOutput));
        //crawler.add(".*", HtmlExtractor.class, new ExtractorParams("fsOutput", fsOutput));
        //crawler.set
        crawler.start(5);
    }

}
