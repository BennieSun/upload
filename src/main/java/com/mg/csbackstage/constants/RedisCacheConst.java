package com.mg.csbackstage.constants;

import com.mg.redis.core.utils.RedisCacheUtil;

/**
 * Created by BennieSun on 2017/2/7.
 *
 * redis 缓存 key
 */
public class RedisCacheConst {
    /**
     * 当前工程前缀
     */
    private static final String CURRENT_PREFIX = RedisCacheUtil.getCachePrefix()+"csbackstage_";


}
