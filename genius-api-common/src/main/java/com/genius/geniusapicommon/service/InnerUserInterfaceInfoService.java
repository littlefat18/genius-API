package com.genius.geniusapicommon.service;
import com.genius.geniusapicommon.model.entity.UserInterfaceInfo;
/**
 * 内部用户接口信息服务
 *
 * @author gen1us
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    UserInterfaceInfo getById(long interfaceInfoId, long userId);

    int addDefault(long interfaceInfoId, long userId);
}
