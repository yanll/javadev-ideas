package sina.client;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.cmp.common.utils.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by YANLL on 2016/06/29.
 */
public class WeiboCrawlerIII extends BreadthCrawler {

    public WeiboCrawlerIII(String crawlPath, boolean autoParse) {
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
            if (!html.contains("relation.fans.index") && !html.contains("content.followTab.index")) continue;
            html = html.substring(8);
            html = html.substring(0, html.length() - 2);
            Document doc_ = Jsoup.parse(html);
            Elements hrefs = doc_.select("a[usercard]");
            Elements imgs = doc_.select("img[usercard]");
            for (Element e_ : hrefs) {
                String nickname = e_.text();
                nickname = Const.filterString(nickname);
                String href = e_.attr("href").replace("\\", "").replace("\"", "");
                int i = href.indexOf("?");
                String userid = "";
                if (i >= 0) {
                    userid = href.substring(0, i);
                    userid = userid.replace("/u", "").replace("/", "");
                    String url = "http://weibo.com/" + userid + "/fans?current=fans";
                    next.add(url);
                }
                if (!Const.filterNickname(nickname)) continue;
                FileUtil.log("/var/sina.txt", nickname + "\n");
            }
        }
    }
}
