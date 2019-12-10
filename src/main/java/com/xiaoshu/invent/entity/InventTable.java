package com.xiaoshu.invent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoshu.common.base.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.omg.CORBA.IDLType;

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
@TableName("Inv_InventTable")
public class InventTable extends DataEntity<InventTable> {

    //private static final long serialVersionUID = 1L;
  /*  @TableId(type= IdType.UUID)
    private String id;*/

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
