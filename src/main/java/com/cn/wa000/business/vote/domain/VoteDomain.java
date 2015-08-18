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
        builder.append("]");
        return builder.toString();
    }
}
