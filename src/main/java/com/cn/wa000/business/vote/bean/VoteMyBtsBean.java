package com.cn.wa000.business.vote.bean;

/**
 * 调整为myBatis时, 使用的投票的javaBean对象
 * 
 * @author wa000
 *
 */
public class VoteMyBtsBean
{
    /**
     * 投票id
     */
    private String voteId;
    
    /**
     * 投票题目
     */
    private String title;
    
    /**
     * 创建时间
     */
    private String createTime;

    
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

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("VoteMyBtsBean [voteId=");
        builder.append(voteId);
        builder.append(", title=");
        builder.append(title);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append("]");
        return builder.toString();
    }
}
