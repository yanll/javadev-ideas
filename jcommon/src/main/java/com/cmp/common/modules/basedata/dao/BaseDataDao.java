package com.cmp.common.modules.basedata.dao;

import com.cmp.common.bean.GoodsBrandBean;
import com.cmp.common.bean.GoodsTagBean;
import com.cmp.common.bean.GoodsTypeBean;

import java.util.List;

/**
 * Created by YANLL on 2016/05/13.
 */
public interface BaseDataDao {


    public List<GoodsTypeBean> selectGoodsTypes();

    public List<GoodsBrandBean> selectGoodsBrands();

    public List<GoodsTagBean> selectGoodsTags();


}
