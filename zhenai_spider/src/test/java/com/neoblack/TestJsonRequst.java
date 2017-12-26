package com.neoblack;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * Created by Neo on 2017/4/5.
 */
public class TestJsonRequst {
//    @Test
    public void test01() throws IOException {
        HttpGet httpGet = new HttpGet("http://search.zhenai.com/v2/search/getPinterestData.do?sex=1&agebegin=22&ageend=22&workcityprovince=10101000&workcitycity=10101022&info=&h1=-1&h2=-1&condition=66&orderby=hpf&currentpage=101");

        httpGet.setHeader("Host", "search.zhenai.com");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
        httpGet.setHeader("Referer", "http://search.zhenai.com/v2/search/pinterest.do?&condition=66");

        CloseableHttpResponse response = new DefaultHttpClient().execute(httpGet);

        String result = "";

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);//取出应答字符串
            // 一般来说都要删除多余的字符
            result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
        } else {
            System.err.println("错误！！！！！");
        }

        System.out.println(result);


    }
}
