package com.genius.geniusapiinterface.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.genius.geniusapicommon.model.request.ChickenSoupRequest;
import com.genius.geniusapiinterface.entity.ChickenSoupText;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chickenSoup")
public class PoisonousChickenSoupController {

    @PostMapping("/make")
    public String getAvatarUrlByPost(@RequestBody(required = false) ChickenSoupRequest chickenSoupRequest, HttpServletRequest request) throws Exception {
    //public String getChickenSoupByPost(ChickenSoupParams chickenSoupParams, HttpServletRequest request) throws Exception {
        String chickenSoupUrl = "http://api.btstu.cn/yan/api.php";
        HashMap<String, Object> paramMap = new HashMap<>();
        if (chickenSoupRequest == null) {
            chickenSoupRequest = new ChickenSoupRequest();
        }

        if (chickenSoupRequest.getCharset() == null || "".equals(chickenSoupRequest.getCharset())) {
            paramMap.put("charset", "utf-8");
        }else {
            paramMap.put("charset", chickenSoupRequest.getCharset());
        }
        if (chickenSoupRequest.getEncode() == null || "".equals(chickenSoupRequest.getEncode())) {
            paramMap.put("encode", "text");
        }else {
            paramMap.put("charset", chickenSoupRequest.getEncode());
        }
        String text = HttpUtil.get(chickenSoupUrl, paramMap);
//        ChickenSoupText chickenSoupText = JSONUtil.toBean(originalText, ChickenSoupText.class);
//        String text = chickenSoupText.getText();
        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("{\"text\": \"");
        stringBuffer.append(text);
//        stringBuffer.append("\"}");
        return stringBuffer.toString();
    }
}
