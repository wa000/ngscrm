package com.cn.wa000.business.vote.bean;

/**
 * 表t_ucp_pairinfo的对应model对象
 * 
 * @author wa000
 *
 */
public class PairBean
{
    // ................................................................. 私有属性
    private String name;
    
    private String status;

    // ............................................................... 构造器方法
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    // ................................................................. 覆盖方法
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("PairBean [name=");
        builder.append(name);
        builder.append(", status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }
}
