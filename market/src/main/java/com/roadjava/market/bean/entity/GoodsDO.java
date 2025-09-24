package com.roadjava.market.bean.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 商品表
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("goods")
public class GoodsDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 所在货架id
     */
    private Long shelfId;
    /**
     * 商品名
     */
    private String name;
    /**
     * 产地省市区编码,通过,分隔
     */
    private String bornPlaceCodes;
    /**
     * 销售价,单位:元
     */
    private BigDecimal salePrice;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 单位
     */
    private String unit;
    /**
     * 规格
     */
    private String specifications;
}
