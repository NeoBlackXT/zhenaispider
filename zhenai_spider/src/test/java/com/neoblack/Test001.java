package com.neoblack;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by bigjsd on 2017/4/5.
 */

public class Test001 {

//    @Test
    public void init() throws IOException {
        String url = "http://search.zhenai.com/v2/search/getPinterestData.do?sex=1&agebegin=23&ageend=23&workcityprovince=10115000&workcitycity=10115008&info=&h1=-1&h2=-1&salaryBegin=-1&salaryEnd=-1&occupation=-1&h=-1&c=-1&workcityprovince1=-1&workcitycity1=-1&constellation=-1&animals=-1&stock=-1&belief=-1&lvBegin=-1&lvEnd=-1&condition=66&orderby=hpf&hotIndex=&online=&currentpage=89&topSearch=false";
        String json = Jsoup.connect(url).ignoreContentType(true).execute().body();

    }
}
