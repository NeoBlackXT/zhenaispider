package com.neoblack.utils;

/**
 * Created by Neo on 2017/4/5.
 */


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫工具类
 */
public class SpiderUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger log = Logger.getLogger(SpiderUtils.class);

    //获取符合特定条件的url链接
    public static String getUrl(int gerder, int ageBegin, int ageEnd, int workProvince, int workCity, int h1, int h2, int page) {
        String url = "http://search.zhenai.com/v2/search/getPinterestData.do?sex=" + gerder + "&agebegin=" + ageBegin + "&ageend=" + ageEnd + "&workcityprovince=" + workProvince + "&workcitycity=" + workCity + "&h1=" + h1 + "&h2=" + h2 + "&condition=66&currentpage=" + page;
        return url;
    }

    //根据url链接获取用户ID
    public static List<Long> getId(String url) {
        Connection connect = Jsoup.connect(url);
        connect.header("Host", "search.zhenai.com");
        connect.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
        connect.header("Accept", "application/json,text/javascript,*/*;q=0.01");
        connect.header("Accept-Language", "zh-CN,zh;q=0.8");
        connect.header("Accept-Encoding", "gzip, deflate, sdch");
        connect.header("Connection", "keep-alive");
        connect.header("Referer", "http://search.zhenai.com/v2/search/pinterest.do?&condition=66");
        connect.header("X-Requested-With", "XMLHttpRequest");
        connect.header("Cache-Control", "no-cache");
        //ignoreContentType(true)忽略内容类别，否则不能接收json
        try {
            String json = connect.ignoreContentType(true).execute().body();
            JsonNode jsonNode = MAPPER.readTree(json);
            JsonNode jsonData = jsonNode.get("data");
            //每个url链接可以获取10条用户信息，截取用户id存入list集合
            List<Long> list = new ArrayList<Long>();
            if (jsonData.size() >= 1) {
                for (int i = 0; i < jsonData.size(); i++) {
                    Long userId = jsonData.get(i).get("memberId").asLong();
                    list.add(userId);
                }
            }
            return list;
        } catch (Exception e) {
            log.error("获取用户ID错误:"+e.getMessage());
            log.error(url);
        }
        return new ArrayList<Long>();
    }

    /**
     * 获取所有省级/直辖市工作地代码
     * 北京 --->10202000
     */
    public static int[] getWorkProvinceCode(String json) throws IOException {
        int[] ints = {10102000, 10104000, 10103000, 10105000, 10118000, 10131000, 10127000, 10107000, 10124000, 10115000, 10112000, 10125000, 10121000, 10120000, 10117000, 10114000, 10106000, 10119000, 10113000, 10116000, 10109000, 10111000, 10110000, 10130000, 10128000, 10126000, 10108000, 10123000, 10122000, 10129000, 10132000, 10133000, 10134000, 10200000};
        return ints;
    }



}
