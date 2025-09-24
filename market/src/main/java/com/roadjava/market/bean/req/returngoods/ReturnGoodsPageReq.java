package com.roadjava.market.bean.req.returngoods;

import com.roadjava.market.bean.req.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 退货表分页查询入参
 *
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReturnGoodsPageReq extends BasePageReq {
    /**
     * 进货批次号,业务外键
     */
    private String fuzzyRetNo;
    private Integer status;
}
