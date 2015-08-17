package com.cn.wa000.business.login.bean;

/**
 * t_ucp_userinfo表的model类
 * 
 * @author wa000
 *
 */
public class LoginBean
{
    // ................................................................. 私有属性
    /**
     * 用户id
     */
    private String staffId;
    
    /**
     * 用户名
     */
    private String staffName;
    
    /**
     * 密码
     */
    private String password;

    
    // ................................................................... 构造器
    public String getStaffId()
    {
        return staffId;
    }

    public void setStaffId(String staffId)
    {
        this.staffId = staffId;
    }

    public String getStaffName()
    {
        return staffName;
    }

    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    // ................................................................. 覆盖方法    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginBean [staffId=");
        builder.append(staffId);
        builder.append(", staffName=");
        builder.append(staffName);
        builder.append(", password=");
        builder.append(password);
        builder.append("]");
        return builder.toString();
    }
}
