package com.roadjava.market.bean.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 货架
 * @author 乐之者java
 * @see <a href="https://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("shelf")
public class ShelfDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 货架编号
     */
    private String shelfNo;
}