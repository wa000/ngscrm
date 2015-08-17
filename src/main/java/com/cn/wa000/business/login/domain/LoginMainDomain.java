package com.cn.wa000.business.login.domain;

import com.cn.wa000.business.login.bean.LoginBean;

/**
 * 登陆时使用的domain对象,封装了所有页面信息
 * 
 * @author wa000
 *
 */
public class LoginMainDomain
{
    // ................................................................. 私有属性
    /**
     * 用户名
     */
    private String staffId;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 登陆信息
     */
    private LoginBean loginInfo = new LoginBean();
    
    // ................................................................... 构造器
    public String getStaffId()
    {
        return staffId;
    }

    public void setStaffId(String staffId)
    {
        this.staffId = staffId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public LoginBean getLoginInfo()
    {
        return loginInfo;
    }

    public void setLoginInfo(LoginBean loginInfo)
    {
        this.loginInfo = loginInfo;
    }
    
    // ................................................................. 覆盖方法
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginMainDomain [staffId=");
        builder.append(staffId);
        builder.append(", password=");
        builder.append(password);
        builder.append(", loginInfo=");
        builder.append(loginInfo);
        builder.append("]");
        return builder.toString();
    }
}
