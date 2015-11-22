package com.cn.wa000.framework.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * httpclient工具类
 * 
 * @author wa000
 *
 */
public class HttpClientUtils
{
    /**
     * 以get方式发送请求并返回返回报文
     * 
     * @param url
     * @return
     */
    public static String runWithGit(String url)
    {
        // 设置超时时间
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)
                .build();
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(defaultRequestConfig);
        String result = "";
        CloseableHttpResponse response = null;
        try
        {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "GBK");
            // 清除返回信息
            EntityUtils.consume(entity);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(null != response)
            {
                try
                {
                    response.close();
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        return result;
    }
    
    
}
