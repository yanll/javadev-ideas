package sina.client;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.util.FileSystemOutput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by YANLL on 2016/06/29.
 */
public class WeiboCrawlerIV extends BreadthCrawler {

    public static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.AbortPolicy());

    FileSystemOutput fsOutput = new FileSystemOutput("weibo_download");

    public WeiboCrawlerIV(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }


    @Override
    public HttpResponse getResponse(CrawlDatum crawlDatum) throws Exception {
        HttpRequest request = new HttpRequest(crawlDatum.getUrl());
        request.setCookie(Const.sina_cookie);
        return request.getResponse();
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        Document doc = page.doc();
        Elements scripts = doc.select("script");
        for (Element e : scripts) {
            String html = e.html();
            if (!html.startsWith("FM.view")) continue;
            if (!html.contains("她的粉丝") && !html.contains("他的粉丝")) continue;
            html = html.substring(8);
            html = html.substring(0, html.length() - 2);
            Document doc_ = Jsoup.parse(html);
            Elements hrefs = doc_.select("a[usercard]");
            for (Element e_ : hrefs) {
                String href = e_.attr("href").replace("\\", "").replace("\"", "");
                int i = href.indexOf("?");
                String userid = "";
                if (i >= 0) {
                    userid = href.substring(0, i);
                    userid = userid.replace("/u", "").replace("/", "");
                    next.add("http://weibo.com/u/" + userid + "?wvr=5&is_hot=1");
                }
            }
        }


        for (Element e : scripts) {
            String html = e.html();
            if (!html.startsWith("FM.view")) continue;
            if (!html.contains("header.head.index")) continue;
            if (!html.contains("W_icon icon_pf_female")) continue;
            html = html.substring(8);
            html = html.substring(0, html.length() - 2);
            Document doc_ = Jsoup.parse(html);
            Elements imgs = doc_.select("img");
            for (Element e_ : imgs) {
                String href = e_.attr("src").replace("quot;", "").replace("\"", "").replace("\\", "");
                int i = href.lastIndexOf("/");
                String name = href.substring(i);
                threadPool.execute(new ThreadPool.MyThread(href, name));
                /*next.add(href);*/
            }
        }
        /*fsOutput.output(page);*/
    }
}


class ThreadPool {
    static class MyThread implements Runnable {
        private String url;
        private String name;

        public MyThread(String url, String name) {
            this.name = name;
            this.url = url;
        }

        @Override
        public void run() {
            // 做点事情
            try {
                System.out.println(url + "," + name);
                Const.download(url, name, "/var/img");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
