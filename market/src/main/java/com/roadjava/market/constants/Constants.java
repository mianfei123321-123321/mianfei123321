package com.roadjava.market.constants;

import java.nio.charset.StandardCharsets;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class Constants {
    public static final Long DEFAULT_PAGE_NOW = 1L;
    public static final Long DEFAULT_PAGE_SIZE = 10L;
    public static final String UTF_8_NAME = StandardCharsets.UTF_8.name();
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String SLASH = "/";
    public static final String UNDER_LINE = "_";
    public static final String RESOURCES_PREFIX = "resources/";
    public static final String TEMP_PREFIX = "temp/";
    public static final String UPLOAD_DATE_FORMAT = "yyyyMMdd/";
    public static final String DOWNLOAD_HEAD_NAME = "file-name";
    /**
     * 进货单号前缀
     */
    public static final String PRE_PURCHASE = "PURCHASE_";
    /**
     * 退货单号前缀
     */
    public static final String PRE_RET = "RETURN_GOODS_";
    /**
     * 销售单号前缀
     */
    public static final String PRE_SALE = "SALE_";
    /**
     * 退款单号前缀
     */
    public static final String PRE_REFUND = "REFUND_";
}
