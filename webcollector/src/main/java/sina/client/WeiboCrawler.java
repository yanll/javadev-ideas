package sina.client;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by YANLL on 2016/06/29.
 */
public class WeiboCrawler extends BreadthCrawler {

    public WeiboCrawler(String crawlPath, boolean autoParse) {
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
            if (!html.contains("content.followTab.index")) continue;
            html = html.substring(8);
            html = html.substring(0, html.length() - 2);
            Document doc_ = Jsoup.parse(html);
            Elements hrefs = doc_.select("a[usercard]");
            for (Element e_ : hrefs) {
                String nickname = e_.text();
                nickname = Const.filterString(nickname);
                String href = e_.attr("href").replace("\\", "").replace("\"", "");
                String userindex = "http://weibo.com" + href;
                System.out.println(userindex);
                int i = href.indexOf("?");
                if (i >= 0) {
                    String userid = href.substring(0, i);
                    userid = userid.replace("/u", "").replace("/", "");
                    String url = "http://weibo.com/p/" + userid + "/follow?from=page_100505&wvr=6&mod=headfollow#place";
                    next.add(url);
                }
            }
        }
    }

}
