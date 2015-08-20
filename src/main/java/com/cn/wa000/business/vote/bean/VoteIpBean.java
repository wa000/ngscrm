package com.cn.wa000.business.vote.bean;

/**
 * 表t_vot_voterecord的对应的model对象
 * 
 * @author wa000
 *
 */
public class VoteIpBean
{
    // ................................................................. 私有属性
    /**
     * 投票id
     */
    private String voteId;
    
    /**
     * ip地址
     */
    private String ip;

    // ............................................................... 构造器方法
    public String getVoteId()
    {
        return voteId;
    }

    public void setVoteId(String voteId)
    {
        this.voteId = voteId;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    // ................................................................. 覆盖方法
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("VoteIpBean [voteId=");
        builder.append(voteId);
        builder.append(", ip=");
        builder.append(ip);
        builder.append("]");
        return builder.toString();
    }
    
}
