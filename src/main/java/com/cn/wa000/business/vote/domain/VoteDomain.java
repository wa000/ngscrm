package com.cn.wa000.business.vote.domain;

import java.util.ArrayList;
import java.util.List;

import com.cn.wa000.business.vote.bean.VoteDetailBean;
import com.cn.wa000.business.vote.bean.VoteMainBean;

/**
 * 投票功能的domain对象
 * 
 * @author wa000
 *
 */
public class VoteDomain
{
    // ................................................................. 私有属性
    /**
     * 详情列表
     */
    private List<VoteDetailBean> voteDetailList = new ArrayList<VoteDetailBean>();

    /**
     * 投票类型为文本框的投票项
     */
    private VoteDetailBean detailTypeBean;
    
    /**
     * 选择的id
     */
    private String selectId;
    
    /**
     * json信息
     */
    private String jsonResult;
    
    /**
     * 投票list
     */
    private List<VoteMainBean> voteList;
    
    /**
     * 增加投票时的标题
     */
    private String addTitle;
    
    /**
     * 投票id
     */
    private String voteId;
    
    /**
     * 投票项内容
     */
    private String content;
    
    /**
     * 类型
     */
    private String type;
    
    // ............................................................... 构造器方法
    public List<VoteDetailBean> getVoteDetailList()
    {
        return voteDetailList;
    }

    public void setVoteDetailList(List<VoteDetailBean> voteDetailList)
    {
        this.voteDetailList = voteDetailList;
    }

    public String getSelectId()
    {
        return selectId;
    }

    public void setSelectId(String selectId)
    {
        this.selectId = selectId;
    }

    public String getJsonResult()
    {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult)
    {
        this.jsonResult = jsonResult;
    }
    
    public List<VoteMainBean> getVoteList()
    {
        return voteList;
    }

    public void setVoteList(List<VoteMainBean> voteList)
    {
        this.voteList = voteList;
    }

    public String getAddTitle()
    {
        return addTitle;
    }

    public void setAddTitle(String addTitle)
    {
        this.addTitle = addTitle;
    }
    
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    public VoteDetailBean getDetailTypeBean()
    {
        return detailTypeBean;
    }

    public void setDetailTypeBean(VoteDetailBean detailTypeBean)
    {
        this.detailTypeBean = detailTypeBean;
    }
    
    // ................................................................. 覆盖方法
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("VoteDomain [voteDetailList=");
        builder.append(voteDetailList);
        builder.append(", selectId=");
        builder.append(selectId);
        builder.append(", jsonResult=");
        builder.append(jsonResult);
        builder.append(", voteList=");
        builder.append(voteList);
        builder.append(", addTitle=");
        builder.append(addTitle);
        builder.append(", voteId=");
        builder.append(voteId);
        builder.append("]");
        return builder.toString();
    }

    
}
