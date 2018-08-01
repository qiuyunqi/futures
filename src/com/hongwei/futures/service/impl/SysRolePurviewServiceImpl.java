package com.hongwei.futures.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.SysPurviewDao;
import com.hongwei.futures.dao.SysRoleDao;
import com.hongwei.futures.dao.SysRolePurviewDao;
import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.model.SysRolePurview;
import com.hongwei.futures.service.SysRolePurviewService;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class SysRolePurviewServiceImpl implements SysRolePurviewService {
	@Autowired
	private SysPurviewDao sysPurviewDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRolePurviewDao sysRolePurviewDao;

	// ====================== 基本 C R U D 方法 ===========================
	public SysRolePurview get(Long id) {
		return sysRolePurviewDao.get(id);
	}

	public void save(SysRolePurview entity) {
		sysRolePurviewDao.save(entity);
	}

	public void delete(Long id) {
		sysRolePurviewDao.delete(id);
	}

	public List<SysRolePurview> findList(Map<String, Object> map) {
		return sysRolePurviewDao.findList(map);
	}

	public String findPurviewList(Long roleId) {
		return sysRolePurviewDao.findPurviewList(roleId);
	}

	public List<SysPurview> findPurviewListByRoleId(Long roleId) {
		return sysRolePurviewDao.findPurviewListByRoleId(roleId);
	}

	public void saveRolePurview(Long roleId, String purviewArray) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		List<SysRolePurview> list = sysRolePurviewDao.findList(map);
		if (list != null && list.size() > 0) {
			for (SysRolePurview sysRolePurview : list) {
				sysRolePurviewDao.delete(sysRolePurview.getId());// 先删除此角色的所有权限
			}
		}

		if (purviewArray != null && purviewArray != "") {
			String[] pArray = purviewArray.split(",");
			for (int i = 0; i < pArray.length; i++) {
				SysRolePurview rolePurview = new SysRolePurview();
				rolePurview.setSysPurview(sysPurviewDao.get(Long.parseLong(pArray[i])));
				rolePurview.setSysRole(sysRoleDao.get(roleId));
				sysRolePurviewDao.save(rolePurview);// 再给此角色配置选中的权限
			}
		}
	}

}
