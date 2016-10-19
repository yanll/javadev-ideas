package api;

import com.cmp.common.json.UtilJackson;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by YANLL on 2016/10/19.
 */
public class Mock {
    public static void main(String[] args) {


        ObjectNode node = UtilJackson.createObjectNode();
        node.put("name", "3333333333333333");
        node.put("idNo", "420982198800000000");
        node.put("mobile", "13888822222");
        node.put("email", "51@i.com");
        node.put("fundChannelId", "1");
        node.put("fundAccountManagerId", "1");
        node.put("empFlag", "N");
        PostCrawler.post("http://localhost:8080/investor/register", node.toString());
    }

}