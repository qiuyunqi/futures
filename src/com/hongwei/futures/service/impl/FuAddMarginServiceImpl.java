package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuAddMarginDao;
import com.hongwei.futures.dao.FuProgramDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuAddMargin;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuAddMarginService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuAddMarginServiceImpl implements FuAddMarginService {

	@Autowired
	private FuAddMarginDao fuAddMarginDao;
	@Autowired
	private FuProgramDao fuProgramDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;

	// ====================== 基本 C R U D 方法 ===========================
	@Override
	public FuAddMargin get(Long id) {
		return fuAddMarginDao.get(id);
	}

	@Override
	public void save(FuAddMargin entity) {
		fuAddMarginDao.save(entity);
	}

	@Override
	public void delete(Long id) {
		fuAddMarginDao.delete(id);
	}

	@Override
	public Integer countSafeMoney(Map<String, Object> map) {
		return fuAddMarginDao.countSafeMoney(map);
	}

	@Override
	public List<FuAddMargin> findSafeMoneyList(int i, int pageSize, Map<String, Object> map) {
		return fuAddMarginDao.findSafeMoneyList(i, pageSize, map);
	}

	@Override
	public List<FuAddMargin> findSafeMoneyByProgramId(Long id) {
		return fuAddMarginDao.findSafeMoneyByProgramId(id);
	}

	/**
	 * 保存审批通过保证金
	 */
	@Override
	public void saveConfirmProfits(FuAddMargin addMargin) {
		FuUser fuUser = addMargin.getFuUser();
		FuProgram pro = addMargin.getFuProgram();

		// 个人账户中的数据变化
		fuUser.setAccountTotalMoney(fuUser.getAccountTotalMoney().add(pro.getSafeMoney()));  //账户总资产  + 保证金
		fuUser.setSafeMoney(fuUser.getSafeMoney() == null ? addMargin.getMoney() : fuUser.getSafeMoney().add(addMargin.getMoney()));  //用户风险保证金总金额+保证金
		fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(addMargin.getMoney()));  //用户账户余额-保证金
		fuUserDao.save(fuUser);

		pro.setAddSafeMoney(pro.getAddSafeMoney() == null ? addMargin.getMoney() : pro.getAddSafeMoney().add(addMargin.getMoney()));  //方案保证金+保证金
		fuProgramDao.save(pro);

		moneyDetailUtil.saveNewFuMoneyDetail(fuUser, pro, null, null, 28, addMargin.getMoney(), fuUser.getAccountBalance(), false);
	}

}
