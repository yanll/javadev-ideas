package com.cmp.jp.api.goods.service;

import com.cmp.common.bean.GoodsTypeBean;
import com.cmp.jp.api.goods.vo.GoodsVO;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

/**
 * Created by YANLL on 2016/08/29.
 */
public interface IGoodsService {
    public List<GoodsVO> selectGoods(Long brand_id, Long tag_id, String goods_name, PageBounds pageBounds);

    public List<GoodsTypeBean> selectGoodsTypes();
}
