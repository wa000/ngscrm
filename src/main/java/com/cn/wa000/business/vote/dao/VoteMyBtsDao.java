package com.cn.wa000.business.vote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

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
}
