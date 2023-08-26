package com.genius.geniusapicommon.service;

import com.genius.geniusapicommon.model.entity.User;

/**
 * 内部用户服务
 *
 * @author gen1us
 */
public interface InnerUserService {

    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
