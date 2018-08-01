package com.hongwei.futures.web.action.user_sys;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.SysBranch;
import com.hongwei.futures.model.SysCity;
import com.hongwei.futures.model.SysProvince;
import com.hongwei.futures.service.SysBranchService;
import com.hongwei.futures.service.SysCityService;
import com.hongwei.futures.service.SysProvinceService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserSysAction extends BaseAction {
	@Autowired
	private SysProvinceService provinceService;
	@Autowired
	private SysCityService cityService;
	@Autowired
	private SysBranchService branchService;

	private Long proId;
	private Long cityId;
	private Long bankId;

	/**
	 * 所有省份
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("allProvinceAjax")
	public String allProvinceAjax() {
		try {
			List<SysProvince> proList = provinceService.findAllProvince();
			JSONArray array = new JSONArray();
			for (int i = 0; proList != null && i < proList.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("id", proList.get(i).getId());
				jo.put("name", proList.get(i).getProvinceName());
				array.add(jo);
			}
			write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据省级联市
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("findCityByProvinceIdAjax")
	public String findCityByProvinceIdAjax() {
		try {
			List<SysCity> cityList = cityService.findCityByProvince(proId);
			JSONArray array = new JSONArray();
			for (int i = 0; cityList != null && i < cityList.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("id", cityList.get(i).getId());
				jo.put("name", cityList.get(i).getCityName());
				array.add(jo);
			}
			write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据市级和银行级联银行分支
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("findBranchByCityIdAjax")
	public String findBranchByCityIdAjax() {
		try {
			List<SysBranch> branchList = branchService.findBranchByCity(bankId,
					cityId);
			JSONArray array = new JSONArray();
			for (int i = 0; branchList != null && i < branchList.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("id", branchList.get(i).getId());
				jo.put("name", branchList.get(i).getBranchName());
				array.add(jo);
			}
			write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
}
