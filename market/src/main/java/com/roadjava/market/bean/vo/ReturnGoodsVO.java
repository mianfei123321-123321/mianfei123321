package com.roadjava.market.bean.vo;

import com.roadjava.market.enums.ReturnGoodsStatusEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
  * 退货表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class ReturnGoodsVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * purchase表主键
     */
    private Long purchaseId;
    /**
     * 进货批次号,业务外键
     */
    private String purchaseNo;
    /**
     * 退货批次号
     */
    private String retNo;
    /**
     * 经办人id
     */
    private Long operatorId;
    /**
     * 经办人姓名
     */
    private String operatorName;
    /**
     * 0:待审核 1:审核通过 2:审核拒绝
     * @see ReturnGoodsStatusEnum
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;
    /**
     * 退货日期
     */
    private Date returnDate;
    private List<ReturnGoodsDetailVO> detailList;
}
