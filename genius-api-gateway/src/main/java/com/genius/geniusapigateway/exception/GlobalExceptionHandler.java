package com.genius.geniusapigateway.exception;

import com.genius.geniusapicommon.common.BaseResponse;
import com.genius.geniusapicommon.common.ErrorCode;
import com.genius.geniusapicommon.common.ResultUtils;
import com.genius.geniusapicommon.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author gen1us

 */
//@RestControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(BusinessException.class)
//    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
//        log.error("businessException: " + e.getMessage(), e);
//        return ResultUtils.error(e.getCode(), e.getMessage());
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
//        log.error("runtimeException", e);
//        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
//    }
//}
