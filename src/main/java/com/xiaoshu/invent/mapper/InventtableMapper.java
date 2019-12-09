package com.xiaoshu.invent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshu.invent.entity.Inventtable;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jeff
 * @since 2019-12-05
 */
public interface InventtableMapper extends BaseMapper<Inventtable> {

    Inventtable selectInventByMap(Map<String,Object> map);

}
