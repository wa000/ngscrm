package com.cn.wa000.business.vote.bean;

/**
 * 投票其它详情的model对象
 * 
 * @author wa000
 *
 */
public class VoteContentBean
{
    // ................................................................. 私有属性
    /**
     * 投票id
     */
    private String voteId;
    
    /**
     * 详情
     */
    private String content;

    // ............................................................... 构造器方法
    public String getVoteId()
    {
        return voteId;
    }

    public void setVoteId(String voteId)
    {
        this.voteId = voteId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    // ................................................................. 覆盖方法
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("VoteContentBean [voteId=");
        builder.append(voteId);
        builder.append(", content=");
        builder.append(content);
        builder.append("]");
        return builder.toString();
    }
}
