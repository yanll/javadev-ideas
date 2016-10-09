package sina.client;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.google.common.base.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.UUID;

/**
 * Created by YANLL on 2016/06/29.
 */
public class WeiboCrawlerII extends BreadthCrawler {

    public WeiboCrawlerII(String crawlPath, boolean autoParse) {
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
            System.out.println("返回结果：" + html);
            html = html.substring(8);
            html = html.substring(0, html.length() - 2);
            Document doc_ = Jsoup.parse(html);
            Elements hrefs = doc_.select("a[usercard]");
            for (Element e_ : hrefs) {
                String nickname = e_.text();
                nickname = Const.filterString(nickname);
                System.out.println(nickname);
            }
        }
    }

    @Override
    protected void afterParse(Page page, CrawlDatums next) {
        String pageindex = page.meta("pageindex");
        if (Strings.isNullOrEmpty(pageindex)) return;
        int index = Integer.valueOf(pageindex);
        index++;
        next.add(new CrawlDatum(("http://weibo.com/p/1005052714280233/follow?relate=fans&page=#$##Pl_Official_HisRelation__64&" + UUID.randomUUID().toString()).replace("#$#", index + "")).meta("pageindex", index + ""));

    }
}
