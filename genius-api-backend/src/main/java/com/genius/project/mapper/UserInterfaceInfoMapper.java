package com.genius.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.genius.geniusapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
 * 用户接口信息 Mapper
 *
 * @author gen1us

 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




