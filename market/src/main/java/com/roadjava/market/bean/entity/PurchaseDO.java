package com.roadjava.market.bean.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 进货表
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("purchase")
public class PurchaseDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 进货批次号
     */
    private String purchaseNo;
    /**
     * 经办人id
     */
    private Long operatorId;
    /**
     * 0:待审核 1:审核通过 2:审核拒绝,3:已退货
     */
    private Integer status;
    /**
     * 进货日期
     */
    private Date purchaseDate;
}