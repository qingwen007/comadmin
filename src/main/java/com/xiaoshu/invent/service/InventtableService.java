package com.xiaoshu.invent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshu.invent.entity.Inventtable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jeff
 * @since 2019-12-05
 */
public interface InventtableService extends IService<Inventtable> {

    Inventtable findInventByItemId(String itemId);

    void updateInvent(Inventtable inventtable);

    void saveInvent(Inventtable inventtable);
}
