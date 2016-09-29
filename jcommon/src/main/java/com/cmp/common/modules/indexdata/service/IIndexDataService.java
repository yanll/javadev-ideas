package com.cmp.common.modules.indexdata.service;


import com.cmp.common.bean.IndexDataBean;

import java.util.List;

/**
 * Created by YANLL on 2016/05/04.
 */
public interface IIndexDataService {

    List<IndexDataBean> getIndexData(Long def_id);
}
