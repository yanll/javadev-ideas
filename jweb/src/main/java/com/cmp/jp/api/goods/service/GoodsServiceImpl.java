package com.cmp.jp.api.goods.service;

import com.cmp.jp.api.goods.bean.GoodsTypeBean;
import com.cmp.jp.api.goods.dao.GoodsDao;
import com.cmp.jp.api.goods.vo.GoodsVO;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YANLL on 2016/08/29.
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    private static final Log logger = LogFactory.getLog(GoodsServiceImpl.class);
    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<GoodsVO> selectGoods(Long brand_id, Long tag_id, String goods_name, PageBounds pageBounds) {
        return goodsDao.selectGoods(brand_id, tag_id, goods_name, pageBounds);
    }

    public List<GoodsTypeBean> selectGoodsTypes() {
        List<GoodsTypeBean> list = goodsDao.selectGoodsTypes();
        return list;
    }
}
