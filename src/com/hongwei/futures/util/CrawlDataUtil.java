package com.hongwei.futures.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hongwei.futures.model.HhrOrgInfo;
import com.hongwei.futures.model.HhrOrgPerson;

public class CrawlDataUtil {
	
    /**
     * 抽取全部期货机构信息
     * @return
     */
    public static List<HhrOrgInfo> getFuturesOrgList() {
        List<HhrOrgInfo> list = new ArrayList<HhrOrgInfo>();
        Map<String, Object> argsMap = new HashMap<String, Object>();
        argsMap.put("all", "personinfo");
        argsMap.put("pageSize", "1000");
        String html = null;
        try {
            html = HttpRemoteUtil.POSTMethod("http://www.cfachina.org/cfainfo/organbaseinfoServlet", argsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (html != null && !"".equals(html)) {
            Document doc = Jsoup.parse(html);
            if(doc != null){
            	Elements tables = doc.getElementsByTag("table");
            	if(tables != null && tables.size()>0){
            		Elements tbodyElements1 = tables.get(1).select("tbody>tr");
                    Elements tbodyElements2 = tables.get(2).select("tbody>tr");
                    tbodyElements1.addAll(tbodyElements2);
                    for (Element ele : tbodyElements1) {
                        if (!"".equals(ele.select("td").toString())) {
                            String orgId = ele.select("td").first().text().trim();
                            String orgName = ele.select("td").get(1).text().trim();
                            String cyryNum = ele.select("td").get(2).text().trim();
                            if (!"机构编号".equals(orgId)) {
                                HhrOrgInfo org = new HhrOrgInfo(0, orgId, orgName, cyryNum);
                                list.add(org);
                            }
                        }
                    }
            	}
            }
        }
        return list;
    }
    
    /**
     * 根据期货机构编码抽取期货从业人员信息
     * @param orgId 期货机构编码
     * @return
     */
    public static List<HhrOrgPerson> getFuturesOrgPersonById(String orgId) {
        List<HhrOrgPerson> list = new ArrayList<HhrOrgPerson>();
        Map<String, Object> argsMap = new HashMap<String, Object>();
        argsMap.put("organid", orgId);
        argsMap.put("selectType", "personinfo");
        argsMap.put("pageSize", "10000");
        String html = null;
        try {
            html = HttpRemoteUtil.POSTMethod("http://www.cfachina.org/cfainfo/organbaseinfoOneServlet", argsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (html != null && !"".equals(html)) {
            Document doc = Jsoup.parse(html);
            if(doc != null){
            	Elements tables = doc.getElementsByTag("table");
            	if(tables != null && tables.size()>0){
            		Elements tbodyElements1 = tables.get(0).select("tbody>tr");
                    for (Element ele : tbodyElements1) {
                        if (!"".equals(ele.select("td").toString())) {
                            String user_name = ele.select("td").first().text().trim();
                            String cer_num = ele.select("td").get(2).text().trim();
                            String zx_num = ele.select("td").get(3).text().trim();
                            if (!"姓名".equals(user_name)) {
                                HhrOrgPerson orgPerson = new HhrOrgPerson(0, user_name, cer_num, zx_num, "1");
                                list.add(orgPerson);
                            }
                        }
                    }
            	}
            }
        }
        return list;
    }

    /**
     * 根据证券机构类别抽取证券机构信息
     * @param orgType[] 10:证券公司  11:证券资产管理公司  12:证券投资咨询机构  13:证券市场资信评级机构  14:投资管理公司(直投公司)
     * @return
     */
    public static List<HhrOrgInfo> getSecurityOrgList(String[] orgType) {
    	List<HhrOrgInfo> list = new ArrayList<HhrOrgInfo>();
    	for(int i=0; i<orgType.length; i++){
    		Map<String, Object> argsMap = new HashMap<String, Object>();
            argsMap.put("filter_EQS_OTC_ID", orgType[i]);
            argsMap.put("ORDERNAME", "AOI#AOI_NAME");
            argsMap.put("ORDER", "ASC");
            argsMap.put("sqlkey", "registration");
            argsMap.put("sqlval", "SELECT_LINE_PERSON");
            String html = null;
            try {
                html = HttpRemoteUtil.POSTMethod("http://person.sac.net.cn/pages/registration/train-line-register!orderSearch.action", argsMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(html != null && !"".equals(html)) {
            	JSONArray arr = JSONArray.fromObject(html);
            	for(int j=0; j<arr.size(); j++){
            		JSONObject obj = JSONObject.fromObject(arr.get(j));
            		HhrOrgInfo org = new HhrOrgInfo(0, obj.getString("AOI_ID"), obj.getString("AOI_NAME"), obj.getString("PR_COUNT_PERSON"));
                    list.add(org);
            	}
            }
    	}
        return list;
    }
    
    /**
     * 根据证券机构id抽取证券从业人员信息
     * @param orgId 
     * @param sqlkey 
     * @return
     */
    public static List<HhrOrgPerson> getSecurityOrgPersonById(String orgId, String[] sqlkey) {
    	List<HhrOrgPerson> list = new ArrayList<HhrOrgPerson>();
    	for(int i=0; i<sqlkey.length; i++){
    		Map<String, Object> argsMap = new HashMap<String, Object>();
            argsMap.put("filter_EQS_PTI_ID", "");        
            argsMap.put("filter_EQS_AOI_ID", orgId);        
            argsMap.put("ORDERNAME", "PP#PTI_ID,PP#PPP_NAME"); 
            argsMap.put("ORDER", "ASC");
            argsMap.put("sqlkey", "registration");
            argsMap.put("sqlval", sqlkey[i]);
            String html = null;
            try {
                html = HttpRemoteUtil.POSTMethod("http://person.sac.net.cn/pages/registration/train-line-register!search.action", argsMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (html != null && !"".equals(html)) {
            	JSONArray arr = JSONArray.fromObject(html);
            	for(int j=0; j<arr.size(); j++){
            		JSONObject obj = JSONObject.fromObject(arr.get(j));
            		HhrOrgPerson orgPerson = new HhrOrgPerson(0, obj.getString("RPI_NAME"), obj.getString("CER_NUM"), null, "2");
                    list.add(orgPerson);
            	}
            }
    	}
        return list;
    }
}
