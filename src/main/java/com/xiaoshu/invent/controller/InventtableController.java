package com.xiaoshu.invent.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshu.admin.entity.User;
import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.base.PageData;
import com.xiaoshu.invent.entity.Inventtable;
import com.xiaoshu.invent.service.InventtableService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jeff
 * @since 2019-12-05
 */
@Controller
@RequestMapping("/Invent/inventTable")
public class InventtableController {

    @Autowired
    InventtableService inventtableService;
    @GetMapping("list")
    @SysLog("跳转物料列表页面")
    public String list(){
        return "invent/inventTable/list";
    }

    @RequiresPermissions("inv:inventTable:list")
    @PostMapping("list")
    @ResponseBody
    public PageData<Inventtable> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                               ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        PageData<Inventtable> inventPageData = new PageData<>();
        QueryWrapper<Inventtable> inventWrapper = new QueryWrapper<>();
        if(!map.isEmpty()){

            String keys = (String) map.get("key");
            if(StringUtils.isNotBlank(keys)) {
                inventWrapper.and(wrapper -> wrapper.like("itemId", keys).or().like("itemName", keys).or().like("material", keys));
            }
        }
        IPage<Inventtable> inventPage = inventtableService.page(new Page<>(page,limit),inventWrapper);
        inventPageData.setCount(inventPage.getTotal());
        inventPageData.setData(inventPage.getRecords());
        return inventPageData;
    }
}
