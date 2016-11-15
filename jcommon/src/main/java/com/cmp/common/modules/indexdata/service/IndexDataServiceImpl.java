package com.cmp.common.modules.indexdata.service;

import com.cmp.common.bean.IndexDataBean;
import com.cmp.common.modules.indexdata.dao.IndexDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YANLL on 2016/05/04.
 */
@Service
public class IndexDataServiceImpl implements IIndexDataService {

    private static final Logger logger = LoggerFactory.getLogger(IndexDataServiceImpl.class);

    @Autowired
    IndexDataDao indexDataDao;

    @Override
    public List<IndexDataBean> getIndexData(Long def_id) {
        List<IndexDataBean> list = indexDataDao.selectIndexDatasByDefId(def_id);
        return list;
    }
}
