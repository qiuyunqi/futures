package com.hongwei.futures.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuContinueContractDao;
import com.hongwei.futures.dao.FuProgramDao;
import com.hongwei.futures.dao.FuSmsLogDao;
import com.hongwei.futures.model.FuContinueContract;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.service.FuContinueContractService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuContinueContractServiceImpl implements FuContinueContractService {
	
	@Autowired
	private FuContinueContractDao fuContinueContractDao;
	@Autowired
	private FuProgramDao fuProgramDao;
	@Autowired
	private FuSmsLogDao fuSmsLogDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuContinueContract get(Long id) {
		return fuContinueContractDao.get(id);
	}
	
	@Override
	public void save(FuContinueContract entity) {
		fuContinueContractDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuContinueContractDao.delete(id);
	}

	@Override
	public Integer countContinue(Map<String, Object> map) {
		return fuContinueContractDao.countContinue(map);
	}

	@Override
	public List<FuContinueContract> getContinueList(int i, int pageSize,
			Map<String, Object> map) {
		return fuContinueContractDao.getContinueList(i,pageSize,map);
	}
	
	@Override
	public void saveContAndPro(FuContinueContract con,String msg){
		FuProgram pro = con.getFuProgram();
		pro.setCycleNum(pro.getCycleNum()+con.getCycle());
		Calendar cal = Calendar.getInstance();
		cal.setTime(pro.getCloseTime());
		if(pro.getProgramType()==1){
			cal.add(Calendar.DAY_OF_MONTH, con.getCycle());
		}else{
			cal.add(Calendar.MONTH, con.getCycle());
		}
		pro.setCloseTime(cal.getTime());
		fuProgramDao.save(pro);
		fuContinueContractDao.save(con);
		
		// 发短信
		String message="";
		try {
			message = URLDecoder.decode(msg,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		FuSmsLog log = new FuSmsLog();
		log.setFuUser(pro.getFuUser());
		log.setContent(message);
		log.setPrio(1);
		log.setReason("续约审核");
		log.setDestination(pro.getFuUser().getPhone());
		log.setPlanTime(new Date());
		log.setType(1);// 短信
		log.setState(0);
		fuSmsLogDao.save(log);
	}
	
	/**
	 * 根据方案id查询续约记录
	 * @param id
	 * @return
	 */
	@Override
	public List<FuContinueContract> findContinueListByProgramId(Long id){
		return fuContinueContractDao.findContinueListByProgramId(id);
	}
	
}

