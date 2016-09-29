package com.cmp.common.modules.basedata.service;

import com.cmp.common.bean.GoodsBrandBean;
import com.cmp.common.bean.GoodsTagBean;
import com.cmp.common.bean.GoodsTypeBean;
import com.cmp.common.modules.basedata.dao.BaseDataDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YANLL on 2016/05/13.
 */
@Service
public class BaseDataServiceImpl implements IBaseDataService {

    private static final Log logger = LogFactory.getLog(BaseDataServiceImpl.class);


    @Autowired
    BaseDataDao baseDataDao;

    @Override
    public void refreshBaseData() {

    }


    @Override
    public List<GoodsTypeBean> selectGoodsTypes() {
        List<GoodsTypeBean> list = baseDataDao.selectGoodsTypes();
        return list;
    }

    @Override
    public List<GoodsBrandBean> selectGoodsBrands() {
        List<GoodsBrandBean> list = baseDataDao.selectGoodsBrands();
        return list;
    }


    @Override
    public List<GoodsTagBean> selectGoodsTags() {
        List<GoodsTagBean> list = baseDataDao.selectGoodsTags();
        return list;
    }

}
