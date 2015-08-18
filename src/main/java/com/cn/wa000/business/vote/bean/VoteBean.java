package com.cn.wa000.business.vote.bean;

/**
 * 测试jdbc用
 * 
 * @author wa000
 *
 */
public class VoteBean
{
    private String id;
    
    private String title;

    private String createDate;
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("VoteBean [id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", createDate=");
        builder.append(createDate);
        builder.append("]");
        return builder.toString();
    }
}
