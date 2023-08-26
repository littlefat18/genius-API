package com.genius.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.genius.geniusapiclientsdk.client.GeniusApiClient;
import com.genius.geniusapicommon.model.entity.InterfaceInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口信息服务
 *
 * @author gen1us

 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    GeniusApiClient getGeniusApiClient(HttpServletRequest request);
}
