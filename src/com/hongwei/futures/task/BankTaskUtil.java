    package com.hongwei.futures.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.SysBank;
import com.hongwei.futures.model.SysBranch;
import com.hongwei.futures.model.SysCity;
import com.hongwei.futures.model.SysProvince;
import com.hongwei.futures.service.SysBankService;
import com.hongwei.futures.service.SysBranchService;
import com.hongwei.futures.service.SysCityService;
import com.hongwei.futures.service.SysProvinceService;

public class BankTaskUtil extends TimerTask{
	@Autowired
	private SysProvinceService provinceService;
	@Autowired
	private SysCityService cityService;
	@Autowired
	private SysBranchService branchService;
	@Autowired
	private SysBankService bankService;
	

	@Override
	public void run(){
		String netLink = "http://www.cardbaobao.com/wangdian/bankwdsearch.asp?search_word=&sheng=$1&shi=$2&qu=0&bankid=$3&servertypes=";
		String cityLink = "http://www.cardbaobao.com/wangdian/getbankcity1.asp?rnd=$1&code=$2&bankid=0&sid=shi";
		Document document;
		try {
			document = Jsoup.connect("http://www.cardbaobao.com/wangdian/").get();
			Element element = document.getElementById("s-wdhid1");
			Element pro_element = document.getElementById("s-wdhid2");
			String regx ="ssqcity1\\((\\d+)\\)";//正则表达式
			Pattern p = Pattern.compile(regx);
			Elements banklis = element.select("li");
			Map<String,String> bankMap=new HashMap<String,String>();
			for(int i=2;i<19;i++){
				String bankName  =banklis.get(i).text();
				Matcher m = p.matcher(banklis.get(i).toString());
				if(m.find()){
//					System.out.println(m.group(1)+bankName);
					bankMap.put(m.group(1), bankName);
				}
			}
			regx="ssqcity\\('(\\d+)','shi',''\\)";
			p=Pattern.compile(regx);
			Elements prolis = pro_element.select("li");
			Map<String,String> proMap=new HashMap<String,String>();
			Map<String,List> proCityMap=new HashMap<String,List>();
			for(int i=1;i<prolis.size()-1;i++){
				String province = prolis.get(i).text();
				province = province.substring(1,province.length());
				Matcher m=p.matcher(prolis.get(i).toString());
				if(m.find()){
//					System.out.println(m.group(1)+province);
					proMap.put(m.group(1), province);
					String city_link=cityLink.replace("$1", String.valueOf(Math.random())).replace("$2", m.group(1));
					Document cityDoc=Jsoup.connect(city_link).get();
//					System.out.println(cityDoc.text());
					String[] citys=cityDoc.text().split("#");
					List<Map> citylist=new ArrayList<Map>();
					for(int j=2;j<citys.length;j+=2){
						String cityId=citys[j];
						String cityName=citys[j+1].replaceAll("[a-zA-Z]", "");
//						System.out.println(cityId+":"+cityName);
						Map<String,String> map=new HashMap<String,String>();
						map.put(cityId, cityName);
						citylist.add(map);
					}
					proCityMap.put(m.group(1), citylist);
				}
			}
			for(String bankId:bankMap.keySet()){
				//保存银行
				if("邮政储蓄银行".equals(bankMap.get(bankId))||"平安银行".equals(bankMap.get(bankId))||"华夏银行".equals(bankMap.get(bankId))||"兴业银行".equals(bankMap.get(bankId))){
					continue;
				}
				SysBank bank = bankService.findBy(bankMap.get(bankId));
				if(bank==null){
					bank = new SysBank();
					bank.setBankName(bankMap.get(bankId));
					bank.setState(1);
					bankService.save(bank);
				}
				for(String proId:proMap.keySet()){
					//保存省
					SysProvince province = provinceService.findByName(proMap.get(proId));
					if(province==null){
						province =  new SysProvince();
						province.setProvinceName(proMap.get(proId));
						provinceService.save(province);
					}
					List<Map> cityList = proCityMap.get(proId);
					for(Map<String,String> cityMap:cityList){
						for(String cityId:cityMap.keySet()){
							//保存城市
							SysCity city = cityService.findByCityName(cityMap.get(cityId));
							if(city==null){
								city = new SysCity();
								city.setProvinceId(province.getId());
								city.setCityName(cityMap.get(cityId));
								cityService.save(city);
							}
							//http://www.cardbaobao.com/wangdian/bankwdsearch.asp?search_word=&sheng=$1&shi=$2&qu=0&bankid=$3&servertypes=
							//查找网点
                            String net_link = netLink.replace("$1",proId).replace("$2",cityId).replace("$3",bankId);
                            String netPageLink = "http://www.cardbaobao.com/wangdian/bankwdsearch.asp?page=$4&search_word=&sheng=$1&shi=$2&qu=0&bankid=$3&servertypes=";
                            Document netDoc = Jsoup.connect(net_link).timeout(10000000).get();
                            int totalCount  = Integer.valueOf(netDoc.getElementById("wdoq0").select("div").first().select("span").text().split("个")[0]);
                            if(totalCount>0){//有网点
	                            String pageInfo = netDoc.select(".sspages").first().text();//共46条   15条/页   当前第1/4页   首页 上一页 
	                            int totalPage = Integer.valueOf(pageInfo.split("当前第")[1].split("页")[0].split("/")[1]);
	                            for(int k=1;k<=totalPage;k++){
	                                 String net_page_link = netPageLink.replace("$1",proId).replace("$2",cityId).replace("$3",bankId).replace("$4",String.valueOf(k));
	                                 Document netPageDoc = Jsoup.connect(net_page_link).timeout(10000000).get();
	                                 Elements netlis = netPageDoc.getElementById("wdoq0").select("dd");
	                                 for(int j=0;j<netlis.size()-1;j++){
	                                	 //保存网点
	                                	 Element net = netlis.get(j);
	                                	 String branchName = net.select(".t1").text();
	                                	 String branchAddress = net.select(".t2").text();
	                                	 String branchPhone = net.select(".t3").text();
	                                	 SysBranch branch = branchService.findBy(bank.getId(),city.getId(),branchName,branchAddress,branchPhone);
	                                	 if(branch==null){
	                                		 branch =  new SysBranch();
	                                		 branch.setBankId(bank.getId());
		                                     branch.setCityId(city.getId());
		                                     branch.setBranchName(branchName);
		                                     branch.setBranchAddress(branchAddress);
		                                     branch.setBranchPhone(branchPhone);
		                                     branchService.save(branch);
		                                     System.out.println(branch.getBranchName());
	                                	 }
	                                 }
	                            }
                           }
						}
					}
				}
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
