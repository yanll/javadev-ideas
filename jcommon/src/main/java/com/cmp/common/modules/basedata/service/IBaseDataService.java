package com.cmp.common.modules.basedata.service;

import com.cmp.common.bean.GoodsBrandBean;
import com.cmp.common.bean.GoodsTagBean;
import com.cmp.common.bean.GoodsTypeBean;

import java.util.List;

/**
 * Created by YANLL on 2016/05/13.
 * 基础数据提供接口
 */
public interface IBaseDataService {

    /**
     * 刷新基础数据缓存
     */
    public void refreshBaseData();

    /**
     * List<商品类型对象>
     *
     * @return
     */
    public List<GoodsTypeBean> selectGoodsTypes();


    /**
     * List<品牌类型对象>
     *
     * @return
     */
    public List<GoodsBrandBean> selectGoodsBrands();


    /**
     * List<标签对象>
     *
     * @return
     */
    public List<GoodsTagBean> selectGoodsTags();


}
