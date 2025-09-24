package com.roadjava.market.bean.req;

import com.roadjava.market.constants.Constants;
import lombok.Data;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class BasePageReq {

    private Long pageNow = Constants.DEFAULT_PAGE_NOW;
    /**
     * 每页有多少条
     */
    private Long pageSize = Constants.DEFAULT_PAGE_SIZE;

    public Long getStart() {
        return  (pageNow - 1) * pageSize;
    }
}
