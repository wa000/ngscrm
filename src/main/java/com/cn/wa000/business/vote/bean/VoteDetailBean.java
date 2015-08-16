package com.cn.wa000.business.vote.bean;

public class VoteDetailBean
{
    private String voteId;
    
    private String itemId;
    
    private String content;
    
    private String count;
    

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

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }
}
