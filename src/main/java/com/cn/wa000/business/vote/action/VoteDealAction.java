package com.cn.wa000.business.vote.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cn.wa000.business.vote.bean.PairBean;
import com.cn.wa000.business.vote.bean.PairResultBean;
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
            VoteDomain domain = new VoteDomain();
            
            domain.setVoteId(voteId);
            
            // 已经投过票直接跳转到结果页面
            domain.setJsonResult(generateJsonResult(domain.getVoteId()));
            
            domain.setVoteContentList(dao.queryVoteContentList(domain.getVoteId()));
            
            modelAndView.getModelMap().addAttribute("domain", domain);
            
            modelAndView.setViewName("vote/voteresult_new");
            
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
        
        // 已经投票,跳转到结果页面
        if(checkHeadVote(request.getRemoteAddr(), domain.getVoteId()))
        {
            domain.setJsonResult(generateJsonResult(domain.getVoteId()));
            
            domain.setVoteContentList(dao.queryVoteContentList(domain.getVoteId()));
            
            modelAndView.getModelMap().addAttribute("domain", domain);
            
            modelAndView.setViewName("vote/voteresult_new");
            
            return modelAndView;
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(CommonUtils.isNotEmpty(domain.getSelectId()))
        {
            String[] arr = domain.getSelectId().split("@_@");
            
            map.put("voteid", arr[0]);
            
            map.put("itemid", arr[1]);
            
            dao.updateVoteNumber(map);
            
            map.put("ip", request.getRemoteAddr());
            
            dao.logIpforVote(map);
        }
        
        // 记录文本框中的信息
        if(CommonUtils.isNotEmpty(domain.getContent()))
        {
            map.clear();
            
            map.put("voteid", domain.getVoteId());
            
            map.put("content", domain.getContent());
            
            dao.addVoteContent(map);
        }
        
        domain.setJsonResult(generateJsonResult(domain.getVoteId()));
        
        domain.setVoteContentList(dao.queryVoteContentList(domain.getVoteId()));
        
        modelAndView.getModelMap().addAttribute("domain", domain);
        
        modelAndView.setViewName("vote/voteresult_new");
        
        return modelAndView;
    }
    
    /**
     * 组织返回到投票结果页面的返回json报文
     * 
     * @param voteid
     * @return
     */
    private String generateJsonResult(String voteid)
    {
        List<VoteDetailBean> voteDetailList = dao.queryVoteDetailList(voteid);
        
        StringBuilder jsonSB = new StringBuilder();
        
        jsonSB.append("{root:[");
        
        for(VoteDetailBean oneBean : voteDetailList)
        {
            if("1".equals(oneBean.getType()))
            {
                jsonSB.append("{name:'").append(oneBean.getContent()).append("',value:'").append(oneBean.getCount()).append("'},");
            }
        }
        
        String jsonStr = "";
        
        if(!voteDetailList.isEmpty())
        {
            jsonStr = jsonSB.substring(0, jsonSB.length() - 1);
        }
        
        jsonStr += "]}";
        
        return jsonStr;
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
    
    /**
     * 随机生成pair
     * 
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/generatePair", method = RequestMethod.GET)
    public ModelAndView generatePair(ModelAndView modelAndView)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        String currentTime = sdf.format(new Date());
        
        List<PairResultBean> resultBeanList = new ArrayList<PairResultBean>();
        
        List<PairBean> pairInfoList = dao.queryPairInfoList();
        
        List<String> firstList = new ArrayList<String>();
        List<String> secondList = new ArrayList<String>();
        
        for(PairBean oneBean : pairInfoList)
        {
            if("1".equals(oneBean.getStatus()))
            {
                firstList.add(oneBean.getName());
            }
            if("0".equals(oneBean.getStatus()))
            {
                secondList.add(oneBean.getName());
            }
        }
        
        final int size = secondList.size();
        
        for(int i = 0; i < size; i+=2)
        {
            int tempsize = secondList.size();
            
            int number = new Random().nextInt(tempsize);
            
            String one = secondList.get(number);
            
            secondList.remove(number);
            
            tempsize = secondList.size();
            
            number = new Random().nextInt(tempsize);
            
            String two = secondList.get(number);
            
            secondList.remove(number);
            
            PairResultBean bean = new PairResultBean();
            
            bean.setPair(one + "," + two);
            
            bean.setTime(currentTime);
            
            resultBeanList.add(bean);
        }
        
        if(!firstList.isEmpty())
        {
            PairResultBean bean = new PairResultBean();
            
            bean.setPair(firstList.get(0) + "," + firstList.get(1));
            
            bean.setTime(currentTime);
            
            resultBeanList.add(bean);
        }

        
        String id = dao.queryMaxPairId();
        
        for(PairResultBean bean : resultBeanList)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            
            map.put("id", id);
            
            map.put("pair", bean.getPair());
            
            map.put("time", bean.getTime());
            
            dao.addPair(map);
        }
        
        modelAndView.setViewName("redirect:/showvote/showPairResult");
        
        return modelAndView;
    }
    
    /**
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/showPairResult", method = RequestMethod.GET)
    public ModelAndView showPairResult(ModelAndView modelAndView)
    {
        List<PairResultBean> pairResultlist = dao.queryPairResultlist();
        
        modelAndView.getModelMap().addAttribute("time", pairResultlist.get(0).getTime());
        
        modelAndView.getModelMap().addAttribute("pairResultlist", pairResultlist);
        
        modelAndView.setViewName("vote/pairresult");
        
        return modelAndView;
    }
}
