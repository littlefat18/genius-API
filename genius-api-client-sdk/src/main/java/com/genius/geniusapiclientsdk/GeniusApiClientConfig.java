package com.genius.geniusapiclientsdk;

import com.genius.geniusapiclientsdk.client.GeniusApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author dzl
 * @date 2023/7/29 12:31
 */
@Configuration
@Data
@ComponentScan
@ConfigurationProperties("geniusapi.client")
public class GeniusApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public GeniusApiClient geniusApiClient(){
        return new GeniusApiClient(accessKey,secretKey);
    }
}
