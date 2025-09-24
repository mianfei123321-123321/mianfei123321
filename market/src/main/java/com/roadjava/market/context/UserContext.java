package com.roadjava.market.context;


import com.roadjava.market.bean.dto.TokenDTO;

/**
 * @author 乐之者java
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class UserContext {
    private static final ThreadLocal<TokenDTO> THREAD_LOCAL = new ThreadLocal<>();
    public static void set(TokenDTO dto) {
        THREAD_LOCAL.set(dto);
    }
    public static TokenDTO get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
