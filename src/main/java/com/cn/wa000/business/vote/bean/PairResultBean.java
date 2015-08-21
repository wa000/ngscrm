package com.cn.wa000.business.vote.bean;

/**
 * pair结果的bean (临时使用)
 * 
 * @author wa000
 *
 */
public class PairResultBean
{
    private String id;
    
    private String pair;
    
    private String time;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPair()
    {
        return pair;
    }

    public void setPair(String pair)
    {
        this.pair = pair;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("PairResultBean [id=");
        builder.append(id);
        builder.append(", pair=");
        builder.append(pair);
        builder.append(", time=");
        builder.append(time);
        builder.append("]");
        return builder.toString();
    }
}
