package com.cn.wa000.framework.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * action类的基类, 主要用于基础对象的获取
 * 
 * @author wa000
 *
 */
public class BaseAction
{
    // ................................................................. 继承对象
    protected HttpServletRequest request;
    
    protected HttpServletResponse response;
    
    protected HttpSession session; 
    
    // ................................................................. 私有方法
    /**
     * 通过注解, 获取参数
     * 
     * @param request
     * @param response
     */
    @ModelAttribute
    private void setResAndReq(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }
}
