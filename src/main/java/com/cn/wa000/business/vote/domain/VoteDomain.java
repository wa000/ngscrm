package com.cn.wa000.business.vote.domain;

import java.util.ArrayList;
import java.util.List;

import com.cn.wa000.business.vote.bean.VoteDetailBean;

public class VoteDomain
{
    private List<VoteDetailBean> voteDetailList = new ArrayList<VoteDetailBean>();

    private String selectId;
    
    private String jsonResult;
    
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
        builder.append("]");
        return builder.toString();
    }
}
