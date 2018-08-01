package #package#;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import #dao#;
import #model#;
import com.hongwei.futures.service.#serviceName#;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class #serviceName#Impl implements #serviceName# {
	
	@Autowired
	private #daoName# #daoEntity#;
	
	//====================== 基本 C R U D 方法  ===========================
	public #entityName# get(Long id) {
		return #daoEntity#.get(id);
	}
	
	public void save(#entityName# entity) {
		#daoEntity#.save(entity);
	}
	
	public void delete(Long id) {
		#daoEntity#.delete(id);
	}
	
}