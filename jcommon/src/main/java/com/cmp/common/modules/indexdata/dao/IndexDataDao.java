package com.cmp.common.modules.indexdata.dao;

import com.cmp.common.bean.IndexDataBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by YANLL on 2016/05/04.
 */
public interface IndexDataDao {

    @Select("select * from t_index_data where def_id = #{def_id} and enabled = 1 order by idx")
    public List<IndexDataBean> selectIndexDatasByDefId(Long def_id);

}
