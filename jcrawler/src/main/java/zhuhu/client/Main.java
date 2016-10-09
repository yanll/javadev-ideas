package zhuhu.client;

import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import zhuhu.util.HttpClientUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by YANLL on 2016/06/29.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CookieStore cookieStore = (CookieStore) HttpClientUtil.deserialize("/var/zhihucookies");
        HttpRequest request = new HttpRequest("http://weibo.com/p/10080874b14ded4aa7dbb8f5e203c2375dbaeb/followlist");
        StringBuffer sb = new StringBuffer();
        List<Cookie> cookieList = cookieStore.getCookies();
        for (Cookie cookie : cookieList) {
            sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
        }
        HttpResponse response = request.getResponse();
        System.out.println(response.getUrl());
        System.out.println(response.getHtml("UTF-8"));

    }

    @Test
    public void login() {
        StringBuilder sb = new StringBuilder();
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        driver.get("http://weibo.com/login.php");

        System.out.print(driver.getPageSource());
        WebElement mobile = driver.findElementByCssSelector("input[name=username]");
        mobile.sendKeys("18611700380");
        WebElement pass = driver.findElementByCssSelector("input[name=password]");
        pass.sendKeys("123456");
        WebElement rem = driver.findElementByCssSelector("input[id=login_form_savestate]");
        rem.click();
        WebElement submit = driver.findElementByCssSelector("a[action-type=submit]");
        submit.click();

        Set cookieSet = driver.manage().getCookies();
        driver.close();

        Iterator<Cookie> it = cookieSet.iterator();

        while (it.hasNext()) {
            Cookie c = it.next();
            sb.append(c.getName() + "=" + c.getValue() + ";");
        }
        String result = sb.toString();
        System.out.println(sb);


    }

}
