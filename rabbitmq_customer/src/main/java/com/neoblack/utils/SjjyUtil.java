package com.neoblack.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neoblack.pojo.User;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SjjyUtil {
	
	private static final Logger LOG = Logger.getLogger(SjjyUtil.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 获取所有用户的id
	 * @return
	 */
	public static List<Long> getWomenUserId(){
		try {
			List<Long> userIdList=new ArrayList<Long>();
			
			//获取性别为女的所有用户id
			String url = "http://search.jiayuan.com/v2/search_v2.php?sex=f&p=1";
			String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
			JsonNode node = MAPPER.readTree(json);
			int pageNum = node.get("pageTotal").asInt();
			System.out.println("女的页数--"+pageNum);
			for (int i = 1; i <= pageNum; i++) {
				int userInfoNum = getUserInfoNum(i);
				for (int j = 0; j < userInfoNum; j++) {
					getUserIdList(j ,i, userIdList);
					//System.out.println(userId);
				}
			}
			return userIdList;

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}
	public static List<Long> getManUserId(){
		try{
			List<Long> userIdList=new ArrayList<Long>();
			//获取性别为男的所有用户id
			String url1 = "http://search.jiayuan.com/v2/search_v2.php?sex=m&p=1";
			String json1 = Jsoup.connect(url1).ignoreContentType(true).execute().body();
			JsonNode node1 = MAPPER.readTree(json1);
			int pageNum1 = node1.get("pageTotal").asInt();
			System.out.println("男的页数--"+pageNum1);
			for (int i = 1; i <= pageNum1; i++) {
				int userInfoNum = getUserInfoNum(i);
				for (int j = 0; j < userInfoNum; j++) {
					getUserIdList(j ,i, userIdList);
					//System.out.println(userId);
				}
			}
			return userIdList;
		}catch (Exception e){
			LOG.error(e.getMessage());
		}
		return null;
	}
	public static int getUserInfoNum(int i){
		try{
			String url = "http://search.jiayuan.com/v2/search_v2.php?sex=m&p=" + i;
			String userjson = Jsoup.connect(url).ignoreContentType(true).execute().body();
			JsonNode userInfoNode = MAPPER.readTree(userjson).get("userInfo");
			return userInfoNode.size();
		}catch (Exception e){
			LOG.error(e.getMessage());
		}
		return 0;
	}
	public static void getUserIdList(int j ,int i,List<Long> userIdList){
		try {
			String url = "http://search.jiayuan.com/v2/search_v2.php?sex=f&p=" + i;
			String userjson = Jsoup.connect(url).ignoreContentType(true).execute().body();
			JsonNode userInfoNode = MAPPER.readTree(userjson).get("userInfo");
			JsonNode jsonNode = userInfoNode.get(j);
			Long userId = jsonNode.get("realUid").asLong();
			userIdList.add(userId);
		}catch (Exception e){
			LOG.error(e.getMessage());
		}
	}
	
//	// 获取用户的工作地点
//	public String getCity(Long realUid) {
//		try {
//			String url = "http://www.jiayuan.com/" + realUid;
//			Document doc = Jsoup.connect(url).get();
//			Elements eles = doc.select(".member_info_r").select(".yh .member_name a");
//			String province = eles.get(0).text();
//			return province;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	//获取用户
	private User getUser1(Long realUid){
		Map<String,String> map=new HashMap<String,String>();
		map.put("Cookie", "ip_loc=11; save_jy_login_name=15934211485; stadate1=161790901; myloc=11%7C1112; myage=23; mysex=m; myuid=161790901; myincome=40; last_login_time=1491528196; user_attr=000000; PHPSESSID=76f5d6b4139965151fe0d37cf72191b2; view_m=1; main_search:162790901=%7C%7C%7C00; date_pop_162790901=1; IM_S=%7B%22IM_CID%22%3A4542305%2C%22svc%22%3A%7B%22code%22%3A0%2C%22nps%22%3A0%2C%22unread_count%22%3A%225%22%2C%22ocu%22%3A0%2C%22ppc%22%3A0%2C%22jpc%22%3A0%2C%22regt%22%3A%221491358597%22%2C%22using%22%3A%2251%2C%22%2C%22user_type%22%3A%2210%22%2C%22uid%22%3A162790901%7D%2C%22IM_SV%22%3A%22123.59.161.3%22%2C%22m%22%3A0%2C%22f%22%3A0%2C%22omc%22%3A0%7D; IM_CS=2; IM_ID=14; IM_TK=1491563079396; IM_M=%5B%5D; IM_CON=%7B%22IM_TM%22%3A1491528202616%2C%22IM_SN%22%3A4%7D; pop_time=1491562755874; user_access=1; pclog=%7B%22162790901%22%3A%221491494427930%7C1%7C0%22%7D; DATE_SHOW_LOC=11; DATE_SHOW_SHOP=17; DATE_FROM=tuichu; REG_REF_URL=http://www.jiayuan.com/; PROFILE=162790901%3A%25E5%258B%2587%25E6%2595%25A2%25E7%2588%25B1%3Am%3Aat2.jyimg.com%2F94%2Fd5%2F6c6c34dcea5dddea71138faa8dbc%3A1%3A%3A1%3A6c6c34dce_1_avatar_p.jpg%3A1%3A1%3A50%3A10; RAW_HASH=gOerNNfljPWv282kFQyGPFoaNblRzwS5ItqgeDKSkEwDmAXw78EFhiQXwhjYlTG-uFPOmyGFYgnPX%2AKCQLFE7FLO87kU630jJMJmuMV8PpbxszU.; COMMON_HASH=946c6c34dcea5dddea71138faa8dbcd5; upt=cgiISzm276vTTTp6QwrLa4hR6Yu-pR1ZqyqNgo4tgSaHJiYHVHCp3tBZuwog7KqzbkStBo0hocuiIM-%2AQg..; SESSION_HASH=f01f0c4f2ecadebbd152199c08ae7fd23bd6baea");
		try {
			String url = "http://www.jiayuan.com/" + realUid;
			Document doc = Jsoup.connect(url).cookies(map).get();
			
			//获取用户的昵称
			String nickName=doc.select(".member_info_r").select(".yh h4").text();
			nickName=nickName.substring(0, nickName.indexOf("I", 1));
			
			//获取用户的性别
			String sex=doc.select(".main_1000").select(".fn-clear .nav_l .cur a").text();
			sex=sex.substring(0, 1);
			sex="她".equals(sex)?"女":"男";
			
			// 获取用户的工作地点
			Elements provinceEles = doc.select(".member_info_r").select(".yh .member_name a");
			String province = provinceEles.get(0).text();
			
			//获取用户的信息
			String userInfo=doc.select(".member_info_r").select(".yh .member_name").text();
			//System.out.println(userInfo);
			
			Integer age=Integer.parseInt(userInfo.substring(0, userInfo.indexOf("岁", 1)));
			//System.out.println(age);
			
			String marriage=userInfo.substring(userInfo.indexOf("岁")+2, 6);
			//System.out.println(marriage);
			
			Elements eles=doc.select(".member_info_list").select(".fn-clear em");
			//System.out.println(eles);
			
			//身高
			Integer high=Integer.parseInt(eles.get(1).text().substring(0, 3));
			
			//星座
			String constellation=eles.get(6).text();
			//System.out.println(constellation);
			
			//属相
			String animals=eles.get(8).text();
			//System.out.println(animals);
			
			//学历
			String education=eles.get(0).text();
			//System.out.println(education);
			
			//工作信息
			Elements jobEles=doc.select(".js_list").select(".fn-clear .fn-clear .ifno_r_con em");
			//System.out.println(jobEles);
			
			//职业
			String occupation=jobEles.get(12).text();
			//System.out.println(occupation);
			
			Integer salaryMin=null;
			Integer salaryMax=null;
			String salary=eles.get(3).text();
			int indexOf = salary.indexOf("～");
			String[] split = salary.split("～");
			if(indexOf==-1){
				if("上".equals(salary.lastIndexOf(salary.length()-1))){
					salaryMin=Integer.parseInt(salary.substring(0, salary.indexOf("元")));
				}else{
					salaryMax=Integer.parseInt(salary.substring(0, salary.indexOf("元")));
				}
			}else{
				salaryMin=Integer.parseInt(split[0]);
				salaryMax=Integer.parseInt(split[1].substring(0, split[1].indexOf("元")));
			}
//			System.out.println(salary);
//			System.out.println(salaryMin);
//			System.out.println(salaryMax);
//			
			
			String house=eles.get(4).text();
			
			String child="未婚".equals(marriage)?"没有小孩":"离异".equals(marriage)?userInfo.substring(7, 10):"";
			//System.out.println(child);
			
			//民族
			String nation=eles.get(7).text();
			//System.out.println(nation);
			
			//籍贯
			String homeplace=jobEles.get(22).text();
			//System.out.println(homeplace);
			
			Element pEle = doc.select("#bigImg").select(".img_absolute").get(1);
			String photo=pEle.attr("_src");
			System.out.println(photo);
			
			User user=new User();
			user.setAge(age);
			user.setAnimals(animals);
			user.setChild(child);
			user.setConstellation(constellation);
			user.setEducation(education);
			user.setGender(sex);
			user.setHigh(high);
			user.setHomeplace(homeplace);
			user.setHouse(house);
			user.setMarriage(marriage);
			user.setNation(nation);
			user.setNickname(nickName);
			user.setOccupation(occupation);
			user.setPhoto(photo);
			user.setSalaryMax(salaryMax);
			user.setSalaryMin(salaryMin);
			user.setUserid(Long.parseLong("002"+realUid));
			user.setWorkcity(province);
			System.out.println(user);
			return user;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
//	@Test
//	public void test(){
////		List<Long> userIdList=getAllUserId();
////		for (Long userId : userIdList) {
////			System.out.println(userId);
////		}
////		System.out.println(getCity(17330583L));
//		getUser1(95405049L);
//	}
	
	
}
