package com.cn.wa000.business.vote.bean;

/**
 * 投票的model类
 * 
 * @author wa000
 *
 */
public class VoteMainBean
{
    // ................................................................. 私有属性
    /**
     * 投票id
     */
    private String voteId;
    
    /**
     * 投票标题
     */
    private String title;
    
    /**
     * 创建时间
     */
    private String createtime;

    // ............................................................... 构造器方法
    public String getVoteId()
    {
        return voteId;
    }

    public void setVoteId(String voteId)
    {
        this.voteId = voteId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    // ................................................................. 覆盖方法
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("VoteMainBean [voteId=");
        builder.append(voteId);
        builder.append(", title=");
        builder.append(title);
        builder.append(", createtime=");
        builder.append(createtime);
        builder.append("]");
        return builder.toString();
    }
}
