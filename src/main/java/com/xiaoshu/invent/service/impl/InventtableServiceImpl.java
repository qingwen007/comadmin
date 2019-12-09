package com.xiaoshu.invent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshu.invent.entity.Inventtable;
import com.xiaoshu.invent.mapper.InventtableMapper;
import com.xiaoshu.invent.service.InventtableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jeff
 * @since 2019-12-05
 */
@Service
public class InventtableServiceImpl extends ServiceImpl<InventtableMapper, Inventtable> implements InventtableService {

    @Override
    public Inventtable findInventByItemId(String itemId) {
        Map<String,Object> map = new HashMap();
        map.put("itemId", itemId);
        return baseMapper.selectInventByMap(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInvent(Inventtable inventtable) {
        baseMapper.updateById(inventtable);

    }

    @Override
    public void saveInvent(Inventtable inventtable) {
        baseMapper.insert(inventtable);
    }
}
