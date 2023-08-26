package com.genius.geniusapiinterface.controller;

import com.genius.geniusapicommon.model.request.AvatarRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author dzl
 * @date 2023/8/9 19:39
 */
@RestController
@RequestMapping("/avatar")
@Slf4j
public class AvatarController {

    @PostMapping("/erciyuan")
    public String getAvatarUrlByPost(@RequestBody(required = false) AvatarRequest avatarParams, HttpServletRequest request) throws Exception {
        //https://restapi.amap.com/v3/weather/weatherInfo?
        // https 不能是http 否则拿不到图片
        String avatarUrl = "https://www.loliapi.com/acg/pp/";
        String redirectUrl = getRedirectUrl(avatarUrl);
        log.info(redirectUrl);
        return redirectUrl;
    }


    /**
     * 获取重定向地址
     * @param path
     * @return
     * @throws Exception
     */
    private String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(path)
                .openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
        String location = conn.getHeaderField("Location");
        return location;
    }
}
