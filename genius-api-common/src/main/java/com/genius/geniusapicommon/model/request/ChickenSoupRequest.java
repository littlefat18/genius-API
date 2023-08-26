package com.genius.geniusapicommon.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dzl
 * @date 2023/8/23 16:44
 */
@Data
public class ChickenSoupRequest implements Serializable {

    private String charset;
    private String encode;
}
