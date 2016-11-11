package com.cmp.jp.api.indexdata.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by YANLL on 2016/08/29.
 */
@Controller
@RequestMapping(value = "/api/indexdata", name = "首页数据模块控制器")
public class IndexDataController {
    private static final Log logger = LogFactory.getLog(IndexDataController.class);
    /*
    @Autowired
    IIndexDataService indexDataService;

    @ResponseBody
    @RequestMapping(value = "/sort_type")
    public JSON sort_type() {
        try {
            ArrayNode array = UtilJackson.createArrayNode();
            array.add(UtilJackson.createObjectNode().put("sort_id", "1").put("sort_name", "最新").put("icon_url", ""));
            array.add(UtilJackson.createObjectNode().put("sort_id", "2").put("sort_name", "最热").put("icon_url", ""));
            array.add(UtilJackson.createObjectNode().put("sort_id", "3").put("sort_name", "高价").put("icon_url", ""));
            array.add(UtilJackson.createObjectNode().put("sort_id", "4").put("sort_name", "低价").put("icon_url", ""));
            return new JSON(1000, array);
        } catch (Exception e) {
            logger.error("sort_type error.", e);
            return new JSON(0);
        }
    }


    @RequestMapping(value = "/navi", name = "首页数据导航")
    @ResponseBody
    public JSON navi() {
        try {
            ObjectNode node = UtilJackson.createObjectNode();
            ModuleVO<List> loop = new ModuleVO<List>("轮播", "", Consts.YESNO.Y, Consts.YESNO.N);
            ModuleVO<List> navi = new ModuleVO<List>("导航", "", Consts.YESNO.Y, Consts.YESNO.N);
            List<IndexDataBean> loop_list = indexDataService.getIndexData(IEnum.IndexDefinition.INDEX_LOOP.getValue());
            List<IndexDataBean> navi_list = indexDataService.getIndexData(IEnum.IndexDefinition.INDEX_NAVI.getValue());
            loop.setData(loop_list);
            navi.setData(navi_list);
            node.putPOJO("loop", loop);
            node.putPOJO("navi", navi);
            return new JSON(1000, node);
        } catch (Exception e) {
            logger.error("indexdata navi error.", e);
            return new JSON(0);
        }
    }
    */
}

