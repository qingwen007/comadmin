package com.xiaoshu.invent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshu.invent.entity.InventTable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jeff
 * @since 2019-12-05
 */
public interface InventtableService extends IService<InventTable> {

    InventTable findInventByItemId(String itemId);

    void updateInvent(InventTable inventtable);

    void saveInvent(InventTable inventtable);
}
