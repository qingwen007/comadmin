package com.xiaoshu.invent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshu.invent.entity.InventTable;
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
@Transactional(rollbackFor = Exception.class)
public class InventtableServiceImpl extends ServiceImpl<InventtableMapper, InventTable> implements InventtableService {

    @Override
    public InventTable findInventByItemId(String itemId) {
        Map<String,Object> map = new HashMap();
        map.put("itemId", itemId);
        return baseMapper.selectInventByMap(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInvent(InventTable inventtable) {
        baseMapper.updateById(inventtable);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInvent(InventTable inventtable) {
        baseMapper.insert(inventtable);
    }
}
