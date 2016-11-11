package com.cmp.jp.api.goods.controller;

import com.cmp.jp.api.goods.bean.GoodsTypeBean;
import com.cmp.jp.api.goods.service.IGoodsService;
import com.cmp.jp.api.goods.vo.GoodsVO;
import com.yanll.framework.util.UPage;
import com.yanll.framework.web.result.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by YANLL on 2016/08/29.
 */
@Controller
@RequestMapping(value = "/api/goods", name = "商品模块控制器")
public class GoodsController {
    private static final Log logger = LogFactory.getLog(GoodsController.class);
    @Autowired
    IGoodsService goodsService;

    @RequestMapping(value = "/list", name = "查询商品列表")
    @ResponseBody
    public JSON list(Integer page, Integer limit) {
        try {
            List<GoodsVO> list = goodsService.selectGoods(null, null, null, UPage.toPageBounds(page, limit));
            return new JSON(1000, list);
        } catch (Exception e) {
            logger.error("goods list error.", e);
            return new JSON(0);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/types")
    public JSON selectGoodsTypes() {
        try {
            List<GoodsTypeBean> list = goodsService.selectGoodsTypes();
            return new JSON(1000, list);
        } catch (Exception e) {
            logger.error("goods types error.", e);
            return new JSON(0);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/hotwords")
    public JSON selectHotWords() {
        try {
            List<String> list = Arrays.asList("欧莱雅", "雅诗兰黛", "兰蔻", "迪奥", "香奈儿", "鳄鱼", "花花公子", "路易威登");
            return new JSON(1000, list);
        } catch (Exception e) {
            logger.error("goods hotwords error.", e);
            return new JSON(0);
        }
    }
}

