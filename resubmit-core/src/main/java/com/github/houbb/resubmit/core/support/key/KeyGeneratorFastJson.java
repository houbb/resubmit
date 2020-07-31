package com.github.houbb.resubmit.core.support.key;

import com.alibaba.fastjson.JSON;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.secrect.Md5Util;
import com.github.houbb.resubmit.api.support.IKeyGenerator;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class KeyGeneratorFastJson implements IKeyGenerator {

    /**
     * 根据入参构建对应的 key
     * @param params 入参
     * @return 结果
     * @since 0.0.1
     */
    @Override
    public String gen(final Object params) {
        String json = JSON.toJSONString(params);
        return Md5Util.md5(json);
    }

}
