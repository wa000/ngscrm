package com.cn.wa000.business.region.util;

import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.cn.wa000.framework.util.CommonUtils;
import com.cn.wa000.framework.util.HttpClientUtils;

/**
 * 路由相关的工具类
 * 
 * @author wa000
 *
 */
public class RegionUtil
{
    public static void main(String[] args) throws Exception
    {
        updateIp();
    }
    
    /**
     * 模拟登陆公云(http://www.pubyun.com/accounts/signin/?next=/user/)
     * 然后修改二级域名到路由器IP的映射
     * 
     * @throws Exception 
     */
    public static void updateIp() throws Exception
    {
        // 获取配置信息
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(new File(CommonUtils.getPath() + "/region.properties"));
        // FileReader fileReader = new FileReader(new File("D:/software/Code/Code_j2ee/ngscrm/src/main/resources/region.properties"));
        properties.load(fileReader);
        String userName = properties.getProperty("identification");
        String password = properties.getProperty("password");
        String updateUrl = properties.getProperty("updateIpUrl");
        
        // 设置超时时间
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)
                .build();
        
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        
        HttpGet httpget = new HttpGet("http://www.pubyun.com/accounts/signin/?next=/user/");
        httpget.setConfig(defaultRequestConfig);
        CloseableHttpResponse response = httpclient.execute(httpget);
        // 获取进入登陆页面的返回报文, 截取信息
        HttpEntity getLoginInfoentity = response.getEntity();
        String contentStr = EntityUtils.toString(getLoginInfoentity, "GBK");
        // 截取csrfmiddlewaretoken
        String csrfmiddlewaretoken = contentStr.split("name=\"csrfmiddlewaretoken\" value=\"")[1].split("\"/>")[0];
        EntityUtils.consume(getLoginInfoentity);
        // 模拟登陆
        HttpUriRequest login = RequestBuilder.post()
                .setUri(new URI("http://www.pubyun.com/accounts/signin/?next=/user/"))
                .addParameter("csrfmiddlewaretoken", csrfmiddlewaretoken)
                .addParameter("identification", userName)
                .addParameter("password", password)
                .setConfig(defaultRequestConfig)
                .build();
        CloseableHttpResponse loginResponse = httpclient.execute(login);
        HttpEntity loginEntry = loginResponse.getEntity();
        String loginContent = EntityUtils.toString(loginEntry, "GBK");
        System.out.println(loginContent);
        List<Cookie> cookies = cookieStore.getCookies();
        for(Cookie one : cookies)
        {
            System.out.println(one.toString());
        }
        EntityUtils.consume(loginEntry);
        
        // 登陆完成后进入URL  http://www.pubyun.com/user/
        // 模拟进入管理页面
        HttpGet showIndexGet = new HttpGet("http://www.pubyun.com/user/");
        showIndexGet.setConfig(defaultRequestConfig);
        CloseableHttpResponse showIndexResponse = httpclient.execute(showIndexGet);
        HttpEntity showIndexEntity = showIndexResponse.getEntity();
        EntityUtils.consume(showIndexEntity);
        
        // 设置路由的公网ip
        HttpUriRequest setIpPost = RequestBuilder.post()
                .setUri(new URI(updateUrl))
                .addParameter("comment", "")
                .addParameter("csrfmiddlewaretoken", csrfmiddlewaretoken)
                .addParameter("ftype", "1")
                .addParameter("ip", getIp())
                .addParameter("isBackMx", "0")
                .addParameter("issetpass", "0")
                .addParameter("isWildCard", "0")
                .addParameter("mx", "")
                .addParameter("password", "")
                .build();
        CloseableHttpResponse resultResponse = httpclient.execute(setIpPost);
        HttpEntity resultEntity = resultResponse.getEntity();
        System.out.println(resultEntity.getContentLength());
        EntityUtils.consume(resultEntity);
        
        httpclient.close();
    }
    
    /**
     * 获取当前路由器的公网ip
     * 
     * @return
     */
    public static String getIp()
    {
        String resultHtml = HttpClientUtils.runWithGit("http://www.ip5.me/");
        String matchOne = "<td height=\"30\"><div id=\"ip_addr\" style=\"color:#191970\">";
        String matchTwo = "</div></td>";
        String ip = resultHtml.split(matchOne)[1].split(matchTwo)[0];
        return ip;
    }
}
