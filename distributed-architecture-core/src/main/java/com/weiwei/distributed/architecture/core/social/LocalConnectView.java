package com.weiwei.distributed.architecture.core.social;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LocalConnectView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        if (null == map.get("connections")){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write("解绑成功");
        }else {
            httpServletResponse.setContentType("text/html;charset=UTF-8");
            httpServletResponse.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}
