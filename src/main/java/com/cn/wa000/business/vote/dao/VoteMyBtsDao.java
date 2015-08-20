package com.cn.wa000.business.vote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cn.wa000.business.vote.bean.VoteDetailBean;
import com.cn.wa000.business.vote.bean.VoteIpBean;
import com.cn.wa000.business.vote.bean.VoteMainBean;
import com.cn.wa000.framework.base.BaseDao;

/**
 * 将原来的jdbc方式 切换为 mybatis
 * 
 * @author wa000
 *
 */
@Component
public class VoteMyBtsDao extends BaseDao
{
    // ................................................................. 静态字段
    /**
     * 查询所有投票列表
     */
    public static final String QUERY_ALL_VOTELIST = "com.cn.wa000.business.vote.dao.VoteBeanMapper.queryAllVoteList";
    
    /**
     * 增加一项投票
     */
    public static final String ADD_VOTE = "com.cn.wa000.business.vote.dao.VoteBeanMapper.addvote";
    
    /**
     * 根据voteId查询投票项
     */
    public static final String QUERY_VOTEDETAIL_LIST = "com.cn.wa000.business.vote.dao.VoteBeanMapper.queryVoteDetailList";
    
    /**
     * 增加一个投票项
     */
    public static final String ADD_VOTEDETAIL = "com.cn.wa000.business.vote.dao.VoteBeanMapper.addvotedetail";
    
    /**
     * 删除一个投票项
     */
    public static final String DELETE_VOTEDETAIL = "com.cn.wa000.business.vote.dao.VoteBeanMapper.deletevotedetail";
    
    /**
     * 删除一个投票项
     */
    public static final String DELETE_ALL_VOTEDETAIL = "com.cn.wa000.business.vote.dao.VoteBeanMapper.deleteallvotedetail";
    
    /**
     * 删除一个投票
     */
    public static final String DELETE_VOTE = "com.cn.wa000.business.vote.dao.VoteBeanMapper.deletevote";
    
    /**
     * 根据voteid查询出对应的ip信息
     */
    public static final String QUERY_VOTERECORD = "com.cn.wa000.business.vote.dao.VoteBeanMapper.queryvoterecord";
    
    // ................................................................. dao方法
    /**
     * 查询所有投票列表
     * 
     * @return 查询出的所有投票列表
     */
    public List<VoteMainBean> queryAllVoteList()
    {
        return this.getTemplate().findList(QUERY_ALL_VOTELIST, "");
    }
    
    /**
     * 增加一项投票
     * 
     * @param map 参数map
     * @return 执行的个数
     */
    public int addvote(Map<String, Object> map)
    {
        return this.getTemplate().insert(ADD_VOTE, map);
    }
    
    /**
     * 根据voteId查询投票项
     * 
     * @param voteId
     * @return
     */
    public List<VoteDetailBean> queryVoteDetailList(String voteId)
    {
        return this.getTemplate().findList(QUERY_VOTEDETAIL_LIST, voteId);
    }
    
    /**
     * 增加一个投票项
     * 
     * @param map
     * @return
     */
    public int addVotedetail(Map<String, Object> map)
    {
        return this.getTemplate().insert(ADD_VOTEDETAIL, map);
    }
    
    /**
     * 删除一个投票项
     * 
     * @param map
     * @return
     */
    public int deleteVotedetail(Map<String, Object> map)
    {
        return this.getTemplate().delete(DELETE_VOTEDETAIL, map);
    }
    
    /**
     * 根据voteid删除所有投票项
     * 
     * @param voteid
     * @return
     */
    public int deleteAllVotedetail(String voteid)
    {
        return this.getTemplate().delete(DELETE_ALL_VOTEDETAIL, voteid);
    }
    
    /**
     * 根据voteid删除投票
     * 
     * @param voteid
     * @return
     */
    public int deleteVote(String voteid)
    {
        return this.getTemplate().delete(DELETE_VOTE, voteid);
    }
    
    /**
     * 根据voteid查询出对应的ip信息
     * 
     * @param voteid
     * @return
     */
    public List<VoteIpBean> queryVoterecordByVoteId(String voteid)
    {
        return this.getTemplate().findList(QUERY_VOTERECORD, voteid);
    }
}
