package com.genius.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genius.geniusapiclientsdk.client.GeniusApiClient;
import com.genius.geniusapicommon.model.entity.User;
import com.genius.project.common.ErrorCode;
import com.genius.project.exception.BusinessException;
import com.genius.project.mapper.InterfaceInfoMapper;
import com.genius.project.service.InterfaceInfoService;
import com.genius.geniusapicommon.model.entity.InterfaceInfo;
import com.genius.project.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 接口信息服务实现类
 *
 * @author gen1us

 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Resource
    private UserService userService;
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

    @Override
    public GeniusApiClient getGeniusApiClient(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();

        return new GeniusApiClient(accessKey,secretKey);
    }

}




