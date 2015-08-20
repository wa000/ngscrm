package com.cn.wa000.business.vote.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cn.wa000.business.vote.bean.VoteDetailBean;
import com.cn.wa000.business.vote.bean.VoteIpBean;
import com.cn.wa000.business.vote.dao.VoteMyBtsDao;
import com.cn.wa000.business.vote.domain.VoteDomain;
import com.cn.wa000.framework.base.BaseAction;
import com.cn.wa000.framework.util.CommonUtils;

/**
 * 进行投票操作时,调用的action类
 * 
 * @author wa000
 *
 */
@Controller
@RequestMapping("/showvote")
public class VoteDealAction extends BaseAction
{
    // ................................................................. 私有属性
    @Autowired
    private VoteMyBtsDao dao;
    
    // .............................................................. action方法
    /**
     * 跳转到投票总览页面
     * 
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/showvotepage", method = RequestMethod.GET)
    public ModelAndView showVotePage(ModelAndView modelAndView)
    {
        VoteDomain domain = new VoteDomain();
        
        domain.setVoteList(dao.queryAllVoteList());
        
        modelAndView.getModelMap().addAttribute("domain", domain);
        
        modelAndView.setViewName("vote/votelist_new");
        
        return modelAndView;
    }
    
    /**
     * 跳转到进行投票的页面
     * 
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/showvotedetailpage", method = RequestMethod.GET)
    public ModelAndView showVotedetailPage(ModelAndView modelAndView)
    {
        String voteId = request.getParameter("voteid");
        
        // ................................................. 判断该ip是否已经投过票
        String ip = request.getRemoteAddr();
        
        if(checkHeadVote(ip, voteId))
        {
            // 已经投过票直接跳转到结果页面
            // TODO
            modelAndView.setViewName("redirect:");
            
            return modelAndView;
        }
        
        // ............................. 如果没有投过票进行初始化处理,并跳转到投票页面
        VoteDomain domain = new VoteDomain();
        
        List<VoteDetailBean> voteDetailList = dao.queryVoteDetailList(voteId);
        
        List<VoteDetailBean> newVoteDetailList = new ArrayList<VoteDetailBean>();
        
        VoteDetailBean detailTypeBean = null;
        
        // 对投票项的类型进行处理 
        for(VoteDetailBean bean : voteDetailList)
        {
            if("1".equals(bean.getType()))
            {
                newVoteDetailList.add(bean);
            }
            
            if("2".equals(bean.getType()))
            {
                detailTypeBean = bean;
            }
        }
        
        domain.setVoteId(voteId);
        
        domain.setVoteDetailList(newVoteDetailList);
        
        domain.setDetailTypeBean(detailTypeBean);
        
        modelAndView.getModelMap().addAttribute("domain", domain);
        
        modelAndView.setViewName("vote/votedetail_new");
        
        return modelAndView;
    }
    
    /**
     * 对投票操作进行处理并跳转到投票结果页面
     * 
     * @param modelAndView
     * @param domain
     * @return
     */
    @RequestMapping(value = "/showvoteresult", method = RequestMethod.POST)
    public ModelAndView showVoteResutlPage(ModelAndView modelAndView, VoteDomain domain)
    {
        // 没有投票,直接跳转回总览页面
        if(CommonUtils.isEmpty(domain.getSelectId()) && CommonUtils.isEmpty(domain.getContent()))
        {
            modelAndView.setViewName("redirect:/showvote/showvotepage");
            
            return modelAndView;
        }
        
        if(CommonUtils.isNotEmpty(domain.getSelectId()))
        {
            String[] arr = domain.getSelectId().split("@_@");
            
            // 已经投票,跳转到结果页面
            if(checkHeadVote(request.getRemoteAddr(), arr[0]))
            {
                // TODO
                modelAndView.setViewName("redirect:");
                
                return modelAndView;
            }
            
            
            
        }
        
        
        
        
        return modelAndView;
    }
    
    /**
     * 判断是否已经投过票
     * 
     * @param voteid
     * @return 已经投过票返回true
     */
    private boolean checkHeadVote(String ip, String voteid)
    {
        boolean result = false;
        
        List<VoteIpBean> ipList = dao.queryVoterecordByVoteId(voteid);
        
        for(VoteIpBean bean : ipList)
        {
            if(ip.equals(bean.getIp()))
            {
                result = true;
                break;
            }
        }
        
        return result;
    }
}
