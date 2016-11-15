package zhuhu.client;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zhuhu.util.HttpClientUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 模拟登录知乎
 */
public class Login {
    private static final Logger logger = LoggerFactory.getLogger(Login.class);
    //知乎首页
    public final static String INDEX_URL = "https://www.zhihu.com";
    //手机号码登录地址
    public final static String PHONENUM_LOGIN_URL = "https://www.zhihu.com/login/phone_num";
    //登录验证码地址
    public final static String YZM_URL = "https://www.zhihu.com/captcha.gif?type=login";

    /**
     * @param httpClient Http客户端
     * @param context    Http上下文
     * @param phoneNum   邮箱或手机号码
     * @param pwd        密码
     * @return
     */
    public boolean login(CloseableHttpClient httpClient, HttpClientContext context, String phoneNum, String pwd) {
        String yzm = null;
        String loginState = null;
        HttpGet getRequest = new HttpGet(INDEX_URL);
        HttpClientUtil.getWebPage(httpClient, context, getRequest, "utf-8", false);
        HttpPost request = null;
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        //通过手机号码登录
        request = new HttpPost(PHONENUM_LOGIN_URL);
        formParams.add(new BasicNameValuePair("phone_num", phoneNum));
        yzm = yzm(httpClient, context, YZM_URL);//肉眼识别验证码
        formParams.add(new BasicNameValuePair("captcha", yzm));
        formParams.add(new BasicNameValuePair("_xsrf", ""));//这个参数可以不用
        formParams.add(new BasicNameValuePair("password", pwd));
        formParams.add(new BasicNameValuePair("remember_me", "true"));
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formParams, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.setEntity(entity);
        loginState = HttpClientUtil.getWebPage(httpClient, context, request, "utf-8", false);//登录
        System.out.println(loginState);
        return true;
    }

    /**
     * 肉眼识别验证码
     *
     * @param httpClient Http客户端
     * @param context    Http上下文
     * @param url        验证码地址
     * @return
     */
    public String yzm(CloseableHttpClient httpClient, HttpClientContext context, String url) {
        HttpClientUtil.downloadFile(httpClient, context, url, "/var/", "yzm.gif", true);
        Scanner sc = new Scanner(System.in);
        String yzm = sc.nextLine();
        return yzm;
    }

    public static void main(String args[]) {
        Login ml = new Login();
        HttpClientContext context = HttpClientUtil.getMyHttpClientContext();
        CloseableHttpClient httpClient = HttpClientUtil.getMyHttpClient();
        ml.login(httpClient, context, "18611700380", "123456");
    }
}
