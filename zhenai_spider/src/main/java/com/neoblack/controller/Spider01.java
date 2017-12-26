package com.neoblack.controller;

import com.neoblack.pojo.ErrorId;
import com.neoblack.pojo.User;
import com.neoblack.service.ErrorIdService;
import com.neoblack.service.UserService;
import com.neoblack.utils.SpiderUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * Created by bigjsd on 2017/4/6.
 */
@Controller
public class Spider01 {
    private static final Logger log = Logger.getLogger(Spider01.class);
    private static final String key1 = "spider01";
    private static final String key2 = "spider01qu";
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //    @Autowired
//    private RedisService redisService;
    @Autowired
    private ErrorIdService errorIdService;
    @Autowired
    private UserService userService;

    @RequestMapping("/init")
    public String init() {
        //TODO gender初始化
//2017-04-07 19:10:35 [ INFO] - com.tedu.controller.Spider01 -Spider01.java(63) -关键参数
// ：gender=0,initAgeBegin=18,initWorkProvincesIndex=2,initWorkCityIndex=0initH1=163initPage=1

        int gender = 0;
        int initAgeBegin = 18;
        int initWorkProvincesIndex = 0;
        int initWorkCityIndex = 0;
        int initH1 = 129;
        int initPage = 1;
//        gender = 0;
//        initAgeBegin = 18;
//        initWorkProvincesIndex = 2;
//        initWorkCityIndex = 0;
//        initH1 = 163;
//        initPage = 1;

        int[] workProvinces = {-1};
        int[] workCities = {-1};
        try {
            workProvinces = SpiderUtils.getWorkProvinceCode("");
            //TODO workCity初始化
        } catch (IOException e) {
            log.error("WorkProvince解析错误:" + e.getMessage());
        }
        //循环中断处理

        //遍历年纪，18-99岁
        for (int ageBegin = initAgeBegin; ageBegin <= 99; ageBegin++) {
            //遍历工作地区
            for (int i = initWorkProvincesIndex; i < workProvinces.length; i++) {
                int workProvince = workProvinces[i];
                for (int j = initWorkCityIndex; j < workCities.length; j++) {
                    int workCity = workCities[j];
                    //遍历身高129-211cm
                    for (int h1 = initH1; h1 <= 211; h1++) {
                        //遍历页数1-100，可能不到100就结束
                        for (int page = initPage; page <= 100; page++) {
                            String url = SpiderUtils.getUrl(gender, ageBegin, ageBegin, workProvince, workCity, h1, h1, page);
                            List<Long> listIds = SpiderUtils.getId(url);
                            if (listIds.size() > 0) {
                                for (Long userId : listIds) {
                                    rabbitTemplate.convertAndSend(key1, userId);
                                    log.info("当前遍历位置：userid:" + userId + ",url:" + url + "\r\n关键参数：gender=" + gender + ",initAgeBegin=" + ageBegin + ",initWorkProvincesIndex=" + i + ",initWorkCityIndex=" + j + ",initH1=" + h1 + ",initPage=" + page);
//                                    redisService.set(String.valueOf(userId), String.valueOf(userId));
                                }
                            } else {
                                //页面为空跳出循环
                                break;
                            }
                            if (page == 100 && listIds.size() == 10) {
                                log.error("未完全遍历链接,当前遍历位置：url:" + url + "\r\n关键参数：gender=" + gender + ",initAgeBegin=" + ageBegin + ",initWorkProvincesIndex=" + i + ",initWorkCityIndex=" + j + ",initH1=" + h1 + ",initPage=" + page);
                            }
                        }
                        initPage = 1;
                    }
                    initH1 = 129;
                }
                initWorkCityIndex = 0;
            }
            initWorkProvincesIndex = 0;
        }
        return "success";
    }

    @RequestMapping("fixbyid")
    public String fixErrorByIdInit() {
        List<ErrorId> errorIds = errorIdService.queryAll();
        for (ErrorId errorId : errorIds
                ) {
            rabbitTemplate.convertAndSend(key1, errorId.getUserid());
        }


        return "success";
    }

    @RequestMapping("copy")
    public String CopyDatabase() {
        Integer l1 = 0;
        Integer l2 = 500;
        while (true) {
            List<User> users = userService.queryUserLimit(l1, l2);
            for (User user:users){
                rabbitTemplate.convertAndSend(key2,user);
                log.info(l1);
            }
            l1 += 500;
            if (users.size()<500){
                break;
            }
        }
        return "success";

    }

}
