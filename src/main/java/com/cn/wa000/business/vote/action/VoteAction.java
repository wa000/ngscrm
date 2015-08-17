package com.cn.wa000.business.vote.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.wa000.business.vote.bean.VoteBean;
import com.cn.wa000.business.vote.bean.VoteDetailBean;
import com.cn.wa000.business.vote.dao.VoteDao;
import com.cn.wa000.business.vote.domain.VoteDomain;

/**
 * 测试jdbc时的action类
 * 
 * @author wa000
 *
 */
@Controller
@RequestMapping("/vote")
public class VoteAction
{
    @Autowired
    private VoteDao voteDao;
    
    protected HttpServletRequest request;
    
    protected HttpServletResponse response;
    
    protected HttpSession session;
    
    @ModelAttribute
    public void setResAndReq(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }
    
    @RequestMapping(value = "/showlist", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String showVoteList(ModelMap modelMap)
    {
        @SuppressWarnings("deprecation")
        List<VoteBean> queryVoteList = voteDao.queryVoteList();
        
        modelMap.addAttribute("voteList", queryVoteList);
        
        System.out.println(request.getRemoteAddr());
        
        return "vote/votelist";
    }
    
    @RequestMapping(value="/showlist/showvotedetail", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String showVoteDetail(ModelMap modelMap)
    {
        @SuppressWarnings("deprecation")
        List<VoteDetailBean> list = voteDao.queryVoteDetailByVoteId(request.getParameter("voteid"));
        
        VoteDomain domain = new VoteDomain();
        
        domain.setVoteDetailList(list);
        
        modelMap.addAttribute("domain", domain);
        
        return "vote/votedetail";
    }
    
    @SuppressWarnings("deprecation")
    @RequestMapping(value="/showlist/showvoteresult", method = RequestMethod.POST)
    public String showVoteResult(ModelMap modelMap, VoteDomain domain)
    {
        System.out.println(domain.getSelectId());
        
        String[] sqlitArr = domain.getSelectId().split("@_@");
        
        voteDao.updateVoteCount(sqlitArr[0], sqlitArr[1]);
        
        List<VoteDetailBean> list = voteDao.queryVoteDetailByVoteId(sqlitArr[0]);
        
        domain.setVoteDetailList(list);
        
        StringBuilder jsonSB = new StringBuilder();
        jsonSB.append("{root:[");
        
        for(VoteDetailBean oneBean : list)
        {
            jsonSB.append("{name:'").append(oneBean.getContent()).append("',value:'").append(oneBean.getCount()).append("'},");
        }
        String jsonStr = "";
        if(!list.isEmpty())
        {
            jsonStr = jsonSB.substring(0, jsonSB.length() - 1);
        }
        
        jsonStr += "]}";
        
        domain.setJsonResult(jsonStr);
        
        modelMap.addAttribute("domain", domain);
        
        return "vote/voteresult";
    }
    
    @RequestMapping(value="/showlist/showvoteresult2", method = RequestMethod.GET)
    public String showVoteResultInterceptor(ModelMap modelMap)
    {
        VoteDomain domain = new VoteDomain();
        
        @SuppressWarnings("deprecation")
        List<VoteDetailBean> list = voteDao.queryVoteDetailByVoteId(request.getParameter("voteid"));
        
        
        domain.setVoteDetailList(list);
        
        StringBuilder jsonSB = new StringBuilder();
        jsonSB.append("{root:[");
        
        for(VoteDetailBean oneBean : list)
        {
            jsonSB.append("{name:'").append(oneBean.getContent()).append("',value:'").append(oneBean.getCount()).append("'},");
        }
        String jsonStr = "";
        if(!list.isEmpty())
        {
            jsonStr = jsonSB.substring(0, jsonSB.length() - 1);
        }
        
        jsonStr += "]}";
        
        domain.setJsonResult(jsonStr);
        
        modelMap.addAttribute("domain", domain);
        
        return "vote/voteresult";
    }
    
}
