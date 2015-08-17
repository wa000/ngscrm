package com.cn.wa000.framework.template;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 使用mybatis操作数据库的模板方法, 目前使用sqlite数据库.
 * 
 * @author wa000
 *
 */
@Component
@Scope("prototype")
public class SqliteTemplate
{
    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory factory;
    
    /**
     * 查找单条数据
     * 
     * @param selectId 配置文件中的id
     * @param object 作为入参的javaBean
     * @return 查询到的数据(如果没有查询到返回null)
     */
    public Object findOne(String selectId, Object object)
    {
        SqlSession session = factory.openSession();
        
        Object oneBean = session.selectOne(selectId, object);
        
        session.close();
        
        return oneBean;
    }
    
    /**
     * 查找多条数据
     * 
     * @param selectId 配置文件中的id
     * @param object 作为入参的javaBean
     * @return
     */
    public List<Object> findList(String selectId, Object object)
    {
        SqlSession session = factory.openSession();
        
        List<Object> selectList = session.selectList(selectId, object);
        
        session.close();
        
        return selectList;
    }
    
    /**
     * 修改数据库中的数据
     * 
     * @param updateId 配置文件中的id
     * @param object 作为入参的javaBean
     * @return 修改成功的条数
     */
    public int update(String updateId, Object object)
    {
        // 提供自动commit功能
        SqlSession session = factory.openSession(true);
        
        int updateNum = session.update(updateId, object);
        
        session.close();
        
        return updateNum;
    }
    
    /**
     * 往数据库中插入数据
     * 
     * @param insertId 配置文件中的id
     * @param object 作为入参的javaBean
     * @return 插入成功的条数
     */
    public int insert(String insertId, Object object)
    {
        // 提供自动commit功能
        SqlSession session = factory.openSession(true);
        
        int insertNum = session.insert(insertId, object);
        
        session.close();
        
        return insertNum;
    }
    
    /**
     * 在数据库中删除数据
     * 
     * @param deleteId 配置文件中的id
     * @param object 作为入参的javaBean
     * @return 删除成功的条数
     */
    public int delete(String deleteId, Object object)
    {
        // 提供自动commit功能
        SqlSession session = factory.openSession(true);
        
        int deleteNum = session.delete(deleteId, object);
        
        session.close();
        
        return deleteNum;
    }
}
