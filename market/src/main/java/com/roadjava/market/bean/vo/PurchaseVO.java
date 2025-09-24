package com.roadjava.market.bean.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
  * 进货表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class PurchaseVO {
    /**
     * 主键
     */
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
     * 操作人姓名
     */
    private String operatorName;
    /**
     * 0:待审核 1:审核通过 2:审核拒绝,3:已退货
     * @see com.roadjava.market.enums.PurchaseStatusEnum
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;
    /**
     * 买入日期
     */
    private Date purchaseDate;
    /**
     * 进货明细列表
     */
    private List<PurchaseDetailVO> detailList;
}
