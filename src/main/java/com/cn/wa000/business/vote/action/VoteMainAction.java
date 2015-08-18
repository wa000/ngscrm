package com.cn.wa000.business.vote.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cn.wa000.business.vote.dao.VoteMyBtsDao;
import com.cn.wa000.business.vote.domain.VoteDomain;
import com.cn.wa000.framework.util.CommonUtils;

/**
 * 正式使用的投票action类
 * 
 * @author wa000
 *
 */
@Controller
@RequestMapping("/vote")
public class VoteMainAction
{
    // ................................................................. 私有属性
    /**
     * dao类
     */
    @Autowired
    private VoteMyBtsDao dao;
    
    // .............................................................. action方法
    /**
     * 跳转到投票管理页面
     * 
     * @param modelAndView 
     * @return 
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView showManagerPage(ModelAndView modelAndView)
    {
        VoteDomain domain = new VoteDomain();
        
        // 初始化操作
        // 查询出所有的投票列表
        domain.setVoteList(dao.queryAllVoteList());
        
        modelAndView.getModelMap().addAttribute("domain", domain);
        
        modelAndView.setViewName("vote/voteManager");
        
        return modelAndView;
    }
    
    /**
     * 增加投票
     * 
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/addvote", method = RequestMethod.GET)
    public ModelAndView addVote(ModelAndView modelAndView)
    {
        modelAndView.getModelMap().addAttribute("domain", new VoteDomain());
        
        modelAndView.setViewName("vote/addvote");
        
        return modelAndView;
    }
    
    /**
     * 处理增加投票逻辑
     * 
     * @param modelAndView
     * @param domain
     * @return
     */
    @RequestMapping(value = "/doaddvote", method = RequestMethod.POST)
    public ModelAndView dealAddVote(ModelAndView modelAndView, VoteDomain domain)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        
        // 取出页面传过来的标题
        String title = domain.getAddTitle();
        
        if(CommonUtils.isNotEmpty(title))
        {
            map.put("title", title);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            
            map.put("createtime", sdf.format(new Date()));
            
            // 增加投票
            dao.addvote(map);
        }
        
        // 重定向到管理页面
        modelAndView.setViewName("redirect:/vote/manager");
        
        return modelAndView;
    }
}
