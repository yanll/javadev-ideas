package com.yanll.business.indexdata.service;

import com.yanll.business.indexdata.dao.IndexDataBeanMapper;
import com.yanll.business.indexdata.domain.IndexDataBean;
import com.yanll.business.indexdata.domain.IndexDataBeanVO;
import com.yanll.framework.core.service.mysql.BaseServiceImpl;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by YANLL on 2016/05/04.
 */
@Service
public class IndexDataServiceImpl extends BaseServiceImpl<IndexDataBean, IndexDataBeanVO> implements IIndexDataService {

    private static final Log logger = LogFactory.getLog(IndexDataServiceImpl.class);

    @Autowired
    IndexDataBeanMapper indexDataBeanMapper;

    @Override
    public IndexDataBeanVO getIndexData(Long id) {
        IndexDataBean bean = indexDataBeanMapper.selectByPrimaryKey(id);
        bean.setCreateTime(new Date());
        bean.setModifyTime(new Date());
        IndexDataBeanVO vo = toVO(bean);
        return vo;
    }

    @Override
    public IndexDataBean getDataEntity() {
        return new IndexDataBean();
    }

    @Override
    public IndexDataBeanVO getVO() {
        return new IndexDataBeanVO();
    }

    @Override
    public BaseMapper<IndexDataBean> getMapper() {
        return indexDataBeanMapper;
    }
}
