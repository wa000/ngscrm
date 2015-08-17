package com.cn.wa000.business.vote.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cn.wa000.business.vote.bean.VoteBean;
import com.cn.wa000.business.vote.bean.VoteDetailBean;
import com.cn.wa000.framework.common.SqliteJDBC;
import com.cn.wa000.framework.template.SqliteTemplate;
import com.cn.wa000.framework.util.SpringUtil;

@Component("voteDao")
public class VoteDao
{
    @Autowired
    private SqliteJDBC sqliteJDBC;
    
    private Connection connection;
    
    @Autowired
    private SqliteTemplate template;
    
    
    /**
     * @param voteId
     * @return
     * @deprecated 测试用
     */
    public List<VoteBean> queryVoteBeanList(String voteId)
    {
        String resource = "mybatis-config.xml";
        
        List<VoteBean> list = new ArrayList<VoteBean>();
        
        SqlSession openSession = null;
        
        try
        {
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            
            openSession = sessionFactory.openSession();
            
            VoteBean bean = (VoteBean) openSession.selectOne("com.cn.wa000.business.vote.dao.VoteBeanMapper.selectVoteBean", voteId);
            
            VoteBean bean2 = new VoteBean();
            
            bean2.setId("2");
            
            bean2.setTitle("mybatis:" + System.currentTimeMillis());
            
            openSession.update("com.cn.wa000.business.vote.dao.VoteBeanMapper.updateVote", bean2);
            
            openSession.commit();
            
            list.add(bean);
            
            
            // dbcp 测试
            
            // 手动初始化配置文件
            @SuppressWarnings({ "resource", "unused" })
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            
            SqlSessionFactory s2 = (SqlSessionFactory) SpringUtil.getBean("sqlSessionFactory");
            
            SqlSession openSession2 = s2.openSession();
            
            VoteBean v = (VoteBean) openSession2.selectOne("com.cn.wa000.business.vote.dao.VoteBeanMapper.selectVoteBean", voteId);
            
            openSession2.close();
            
            System.out.println("wa000-dbcp : " + v);
            
            System.out.println("mybatis template : " + template.findOne("com.cn.wa000.business.vote.dao.VoteBeanMapper.selectVoteBean", "123"));
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            openSession.close();
        }
        
        return list;
    }
    
    /**
     * @deprecated 测试用
     */
    private void init()
    {
        if(null == connection)
        {
            this.connection = sqliteJDBC.getConnection();
        }
    }
    
    /**
     * @deprecated 测试用
     */
    private void release()
    {
        if(null != connection)
        {
            try
            {
                connection.close();
                
                connection = null;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @return
     * @deprecated 测试用
     */
    public List<VoteBean> queryVoteList()
    {
        init();
        
        String sql = "select * from T_VOT_VOTESHOW";
        
        List<VoteBean> voteList = new ArrayList<VoteBean>();
        
        try
        {
            connection.setAutoCommit(false);
            
            Statement createStatement = connection.createStatement();
            
            ResultSet executeQuery = createStatement.executeQuery(sql);
            
            while(executeQuery.next())
            {
                VoteBean bean = new VoteBean();
                
                bean.setId(String.valueOf(executeQuery.getInt("VOTEID")));
                
                bean.setTitle(executeQuery.getString("TITLE"));
                
                bean.setCreateDate(executeQuery.getString("CREATETIME"));
                
                voteList.add(bean);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            release();
        }
        
        return voteList;
    }
    
    /**
     * @param voteId
     * @return
     * @deprecated 测试用
     */
    public List<VoteDetailBean> queryVoteDetailByVoteId(String voteId)
    {
        init();
        
        StringBuilder sqlSB = new StringBuilder();
        
        sqlSB.append("select * from T_VOT_VOTEDETAIL where voteid = '").append(voteId).append("'");
        
        List<VoteDetailBean> voteDetailList = new ArrayList<VoteDetailBean>();
        
        try
        {
            connection.setAutoCommit(false);
            
            Statement createStatement = connection.createStatement();
            
            ResultSet executeQuery = createStatement.executeQuery(sqlSB.toString());
            
            while(executeQuery.next())
            {
                VoteDetailBean bean = new VoteDetailBean();
                
                bean.setVoteId(executeQuery.getString("VOTEID"));
                
                bean.setItemId(executeQuery.getString("ITEMID"));
                
                bean.setContent(executeQuery.getString("CONTENT"));
                
                bean.setCount(executeQuery.getString("COUNT"));
                
                bean.setType(executeQuery.getString("TYPE"));
                
                voteDetailList.add(bean);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            release();
        }
        
        return voteDetailList;
    }
    
    /**
     * 投票的票数加1
     * 
     * @param voteid
     * @param itemid
     * @deprecated 测试用
     */
    public void updateVoteCount(String voteid, String itemid)
    {
        init();
        
        StringBuilder sqlSB = new StringBuilder();
        
        // update t_vot_votedetail set count = (select count + 1 from t_vot_votedetail where voteid = '1' and itemid = '1') where voteid = '1' and itemid = '1';
        sqlSB.append(
                "update t_vot_votedetail set count = (select count + 1 from t_vot_votedetail where voteid = '")
                .append(voteid).append("' and itemid = '").append(itemid)
                .append("') where voteid = '").append(voteid)
                .append("' and itemid = '").append(itemid).append("'");
        
        try
        {
            connection.setAutoCommit(false);
        
            Statement createStatement = connection.createStatement();
            
            createStatement.execute(sqlSB.toString());
            
            connection.commit();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        release();
    }
    
}
