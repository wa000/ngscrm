package com.cn.wa000.business.vote.bean;

/**
 * 投票详细信息的bean对象
 * 
 * @author wa000
 *
 */
public class VoteDetailBean
{
    // ................................................................. 私有属性
    /**
     * 投票id
     */
    private String voteId;
    
    /**
     * 投票项id
     */
    private String itemId;
    
    /**
     * 投票项内容
     */
    private String content;
    
    /**
     * 票数
     */
    private String count;
    
    /**
     * 投票类型
     */
    private String type;

    /**
     * 类型名称
     */
    private String typeName;
    
    // ................................................................... 构造器
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getTypeName()
    {
        if("1".equals(type))
        {
            return "单选框";
        }
        if("2".equals(type))
        {
            return "文本框";
        }
        
        return "";
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    // ................................................................. 覆盖方法
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("VoteDetailBean [voteId=");
        builder.append(voteId);
        builder.append(", itemId=");
        builder.append(itemId);
        builder.append(", content=");
        builder.append(content);
        builder.append(", count=");
        builder.append(count);
        builder.append(", type=");
        builder.append(type);
        builder.append(", typeName=");
        builder.append(typeName);
        builder.append("]");
        return builder.toString();
    }
    
}
