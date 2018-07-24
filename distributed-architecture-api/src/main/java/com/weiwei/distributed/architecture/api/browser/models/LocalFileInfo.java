package com.weiwei.distributed.architecture.api.browser.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LocalFileInfo implements Serializable {

    private static final long serialVersionUID = -6349599547215942153L;

    @ApiModelProperty(value = "文件上传路径")
    private String filePath;

    public LocalFileInfo(String filePath){
        this.filePath = filePath;
    }
}
