package com.roadjava.market.bean.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 退货表
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@TableName("return_goods")
public class ReturnGoodsDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * purchase表主键
     */
    private Long purchaseId;
    /**
     * 退货批次号
     */
    private String retNo;
    /**
     * 经办人id
     */
    private Long operatorId;
    /**
     * 0:待审核 1:审核通过 2:审核拒绝
     */
    private Integer status;
    /**
     * 进货日期
     */
    private Date returnDate;
}
