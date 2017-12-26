package com.neoblack.utils;

/**
 * Created by Neo on 2017/4/5.
 */


import com.neoblack.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 爬虫工具类
 */
public class SpiderUtils {
//    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger log = Logger.getLogger(SpiderUtils.class);


    public static User getUser(Long userId) throws IOException {
        User user = new User();
        String url = "http://album.zhenai.com/u/" + userId + "?flag=s";
        log.debug("Fetching url:" + url);

        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("table tbody tr td span[field='']");
        user.setUserid(userId);
        String nickname = document.select("article section .brief-top .brief-name .name").get(0).text();
        user.setNickname(nickname);
        user.setGender(elements.get(0).text());

        user.setWorkcity(elements.get(41).text());
        String _high = elements.get(2).text();
        Integer high = null;
        if (isValidated(_high)) {
            high = Integer.parseInt(_high.substring(0, _high.length() - 2));
            user.setHigh(high);
        }
        String constellation = elements.get(3).text();
        if (isValidated(constellation)) {
            user.setConstellation(constellation);
        }
        String animals = elements.get(1).text();
        if (isValidated(animals)) {
            user.setAnimals(animals);
        }
        String occupation = elements.get(7).text();
        if (isValidated(occupation)) {
            user.setOccupation(occupation);
        }
        String house = elements.get(11).text();
        if (isValidated(house)) {
            user.setHouse(house);
        }
        //TODO child个人页面获取不到


        String nation = elements.get(8).text();
        if (isValidated(nation)) {
            user.setNation(nation);
        }


        Elements elements2 = document.select(".brief-center").select(".p20 .brief-table tbody tr td");

        String _age = elements2.get(0).text();
        Integer age = null;
        if (isValidated(_age)) {
            age = Integer.parseInt(_age.substring(3, _age.length() - 1));
        }
        user.setAge(age);
        user.setMarriage(elements2.get(3).text().substring(3));
        user.setEducation(elements2.get(4).text().substring(3));

        //5001-8000元
        String _salary = elements2.get(2).text();
        if (isValidated(_salary)) {
            if (_salary.endsWith("元以下")) {
                user.setSalaryMax(3000);
            } else if (_salary.endsWith("元以上")) {
                user.setSalaryMin(50000);
            } else {
                _salary = _salary.substring(4, _salary.length() - 1);
                int _sindex = _salary.indexOf("-");
                user.setSalaryMin(Integer.parseInt(_salary.substring(0, _sindex)));
                user.setSalaryMax(Integer.parseInt(_salary.substring(_sindex + 1)));
            }
        }
        String homeplace = elements2.get(8).text().substring(3);
        if (isValidated(homeplace)) {
            user.setHomeplace(homeplace);
        }
        Elements elements3 = document.select("#AblumsThumbsListID ul li p img");
        String photourl = elements3.get(0).attr("data-big-img");
        user.setPhoto(photourl);


        return user;
    }

    public static Boolean isValidated(String eleText) {
        return StringUtils.isNotEmpty(eleText) && !eleText.equals("--");
    }



}
