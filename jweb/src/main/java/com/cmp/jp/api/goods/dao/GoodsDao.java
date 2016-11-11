package com.cmp.jp.api.goods.dao;

import com.cmp.jp.api.goods.bean.GoodsTypeBean;
import com.cmp.jp.api.goods.vo.GoodsVO;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by YANLL on 2016/08/29.
 */
public interface GoodsDao {
    public List<GoodsVO> selectGoods(@Param("brand_id") Long brand_id, @Param("tag_id") Long tag_id, @Param("goods_name") String goods_name, PageBounds pageBounds);

    List<GoodsTypeBean> selectGoodsTypes();
}
