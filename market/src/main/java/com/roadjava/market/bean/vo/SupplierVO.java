package com.roadjava.market.bean.vo;
import lombok.Data;
/**
  * 供应商表视图对象
  * @author 乐之者java
  * @see <a href="http://www.roadjava.com">乐之者java</a>
  */
@Data
public class SupplierVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 供应商名
     */
    private String name;
}