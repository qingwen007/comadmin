package com.xiaoshu.invent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jeff
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Inv_InventTable")
public class Inventtable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ItemId")
    private String itemId;

    @TableField("ItemName")
    private String itemName;

    @TableField("ItemGroup")
    private String itemGroup;

    @TableField("EnglishName")
    private String englishName;

    @TableField("ColorName")
    private String colorName;

    @TableField("Material")
    private String material;


}
