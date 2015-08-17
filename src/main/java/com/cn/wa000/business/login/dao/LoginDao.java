package com.cn.wa000.business.login.dao;

import org.springframework.stereotype.Component;

import com.cn.wa000.business.login.bean.LoginBean;
import com.cn.wa000.framework.base.BaseDao;

/**
 * 登录时查询数据库的dao类
 * 
 * @author wa000
 *
 */
@Component
public class LoginDao extends BaseDao
{
    // ................................................................. 静态字段
    /**
     * 查询单条用户信息的mapperId
     */
    public static final String QUERY_ONE_LOGINBEAN = "com.cn.wa000.business.login.dao.LoginBeanMapper.queryOneLoginBean";
    
    // ................................................................. dao方法
    /**
     * 查询单条用户信息
     * 
     * @param staffid 用户id
     * @return 查询结果
     */
    public LoginBean queryOneLoginBean(String staffid)
    {
        return (LoginBean) getTemplate().findOne(QUERY_ONE_LOGINBEAN, staffid);
    }
}
