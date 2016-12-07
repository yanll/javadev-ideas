package com.yanll.framework.core.service.mysql;


import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.data.mysql.domain.DataEntity;
import com.yanll.framework.data.mysql.domain.VOEntity;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<T extends DataEntity, V extends VOEntity> implements BaseService<V> {

    private Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Override
    public V selectByPrimaryKey(Long id) {
        V e = null;
        try {
            T t = getMapper().selectByPrimaryKey(id);
            e = toVO(t);
        } catch (Exception ex) {
            logger.error("query objects failed.", ex);
            throw new BizException(BizCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return e;
    }


    @Override
    public int updateByPrimaryKey(V e) {
        if (null == e)
            throw new BizException(BizCode.ENTITY_NOT_FOUNT.getValue());
        int result = 0;
        try {
            T t = toDO(e);
            result = getMapper().updateByPrimaryKey(t);
        } catch (Exception ex) {
            logger.error("Update failed, Entity is:{" + e.getClass().getName() + "}", ex);
            throw new BizException(BizCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(V e) {
        if (null == e)
            throw new BizException(BizCode.ENTITY_NOT_FOUNT.getValue());
        int result = 0;
        try {
            T t = toDO(e);
            result = getMapper().updateByPrimaryKeySelective(t);
        } catch (Exception ex) {
            logger.error("Update failed, Entity is:{" + e.getClass().getName() + "}", ex);
            throw new BizException(BizCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return result;
    }

    /**
     * 若需要插入时返回生成的主键不能调用此方法，应直接调用mapper
     *
     * @param e
     * @return
     */
    @Override
    public int insert(V e) {
        int result = 0;
        if (null == e)
            throw new BizException(BizCode.ENTITY_NOT_FOUNT.getValue());
        try {
            T t = toDO(e);
            result = getMapper().insert(t);
        } catch (Exception ex) {
            logger.error("Insert object failed. Entity is:{" + e.getClass().getName() + "}", ex);
            throw new BizException(BizCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return result;
    }

    /**
     * 若需要插入时返回生成的主键不能调用此方法，应直接调用mapper
     *
     * @param e
     * @return
     */
    @Override
    public int insertSelective(V e) {
        int result = 0;
        if (null == e)
            throw new BizException(BizCode.ENTITY_NOT_FOUNT.getValue());
        try {
            T t = toDO(e);
            result = getMapper().insertSelective(t);
        } catch (Exception ex) {
            logger.error("Insert object failed. Entity is:{" + e.getClass().getName() + "}", ex);
            throw new BizException(BizCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return result;
    }

    @Override
    public int deleteByPrimaryKeys(Long... ids) {
        int result = 0;
        try {
            result = getMapper().deleteByPrimaryKeys(ids);
        } catch (Exception ex) {
            logger.error("Delete entity failed. Exception:", ex);
            throw new BizException(BizCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return result;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int result = 0;
        try {
            result = getMapper().deleteByPrimaryKey(id);
        } catch (Exception ex) {
            logger.error("Delete entity failed. Exception:", ex);
            throw new BizException(BizCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return result;
    }


    /**
     * DO转VO
     *
     * @param t
     * @return
     */
    public V toVO(T t) {
        if (t == null) {
            return null;
        }
        V v = getVO();
        try {
            BeanUtils.copyProperties(t, v);
        } catch (BeansException ex) {
            logger.error("toVO BeansException:", ex);
        } catch (Exception ex) {
            logger.error("toVO Exception:", ex);
        }
        return v;
    }

    public List<V> toVOList(List<T> list) {
        List<V> rs = new ArrayList<V>();
        if (list == null) {
            return null;
        }
        for (T t : list) {
            if (t == null) continue;
            V v = getVO();
            try {
                BeanUtils.copyProperties(t, v);
            } catch (BeansException ex) {
                logger.error("toVO BeansException:", ex);
            } catch (Exception ex) {
                logger.error("toVO Exception:", ex);
            }
            rs.add(v);
        }
        return rs;
    }

    /**
     * VO转DO
     *
     * @param e
     * @return
     */
    public T toDO(V e) {
        if (e == null) {
            return null;
        }
        T t = getDataEntity();
        try {
            BeanUtils.copyProperties(e, t);
        } catch (BeansException ex) {
            logger.error("toDO BeansException:", ex);
        } catch (Exception ex) {
            logger.error("toDO Exception:", ex);
        }
        return t;
    }

    public List<T> toDOList(List<V> list) {
        List<T> rs = new ArrayList<T>();
        if (list == null) {
            return null;
        }
        for (V v : list) {
            if (v == null) continue;
            T t = getDataEntity();
            try {
                BeanUtils.copyProperties(v, t);
            } catch (BeansException ex) {
                logger.error("toDO BeansException:", ex);
            } catch (Exception ex) {
                logger.error("toDO Exception:", ex);
            }
            rs.add(t);
        }
        return rs;
    }

    public abstract T getDataEntity();

    public abstract V getVO();

    public abstract BaseMapper<T> getMapper();
}
