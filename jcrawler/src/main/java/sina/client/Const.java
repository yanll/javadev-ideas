package sina.client;

import com.google.common.base.Strings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YANLL on 2016/06/29.
 */
public class Const {
    public static final String sina_cookie = "" +
            "SINAGLOBAL=6086617703725.85.1462873660624;" +
            "un=yen619@sina.cn;" +
            "wvr=6;" +
            "YF-Page-G0=f0e89c46e7ea678e9f91d029ec552e92;" +
            "SCF=An_3iuoZVXzSakOxolNnD-J3tdB0B3RuDsmADvvgY3rSJM5UiGEDekJQG0KgCKL_uuVmnb6Q7HCFRqxPW47ngds.;" +
            "SUB=_2A256u5X3DeTxGedJ7VYW9ybJyzyIHXVZsIA_rDV8PUNbmtANLVHCkW-FTEEoXOKVxaaH_0uOkzrDYYiVKw..;" +
            "SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9W5dbkqQzjWr9EMwYVIf.eCx5JpX5KMhUgL.Fo2NSoBNS0nfeh52dJLoIp7LxKBLBonLBK-LxKBLB.2LB--LxKBLB.2LB-Hk;" +
            "SUHB=0JvRaiE0cQPiZX;" +
            "ALF=1503729959;" +
            "SSOLoginState=1472193959;" +
            "_s_tentry=login.sina.com.cn;" +
            "Apache=2442300337928.3403.1472193967676;" +
            "ULV=1472193967812:34:9:3:2442300337928.3403.1472193967676:1471843513046;" +
            "UOR=www.csdn.net,widget.weibo.com,login.sina.com.cn;";

    public static final String regEx_html = "<[^>]+>";

    public static final String[] filter_words = new String[]{
            "品牌", "创始人", "批发", "总代", "代理", "公司", "集团", "顾问", "老师", "股市", "股票", "专家", "塑形", "塑身", "整形", "用户", "组织", "学校", "大学", "班", "年纪",
            "团", "淘宝", "女郎", "美体", "减肥", "代购", "专业", "珠宝", "定制", "投资", "期货", "白银", "黄金", "贵金属", "婚纱", "摄影", "童装", "男装", "女装", "鞋",
            "帽", "贷款", "原油", "祛痘", "现货", "咨询", "韩国", "软件", "论坛", "美瞳", "正品", "美甲", "购购", "商务", "服装", "会所", "经销", "营销", "度假", "公寓",
            "专卖", "女神", "设计", "工程", "金牌", "推广", "微商", "微信", "微博", "内衣", "广告", "全球", "新闻", "时事", "官方", "分析", "口腔", "平台", "互联网",
            "助理", "日韩", "购", "汽车", "服务", "现货", "特卖", "证券", "全国", "粉", "互粉", "护肤", "美容", "美肤", "包包", "淘宝", "爆款", "搜索",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static String filterString(String text) {
        if (Strings.isNullOrEmpty(text)) return "";
        Pattern p_html = Pattern.compile(Const.regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(text);
        text = m_html.replaceAll("");
        text = text.replace(" ", "");
        text = text.replace("\\r", "");
        text = text.replace("\\n", "");
        text = text.replace("\\t", "");
        return text;
    }

    public static boolean filterNickname(String nickname) {
        if (Strings.isNullOrEmpty(nickname)) return false;
        if (nickname.length() > 20) return false;
        for (String s : filter_words) {
            if (nickname.indexOf(s) >= 0) return false;
        }
        return true;
    }


    public static void download(String url_, String filename, String path) {
        // 输入流
        InputStream is = null;
        OutputStream os = null;
        try {
            // 构造URL
            URL url = new URL(url_);
            // 打开连接
            URLConnection con = url.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(5 * 1000);
            // 输入流
            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(path);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            os = new FileOutputStream(sf.getPath() + "\\" + filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) os.close();
                if (is != null) is.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
