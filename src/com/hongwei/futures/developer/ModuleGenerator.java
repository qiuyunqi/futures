package com.hongwei.futures.developer;

import java.io.File;
import java.util.Map;

import com.hongwei.futures.util.FileOperate;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.StringUtil;

/**
 * 模块生成器
 * 根据实体类自动生成 dao dao.impl service service.impl 以及 spring 中的配置文件    
 * @author 小合
 *
 */
public class ModuleGenerator {
	
	private String entityPath; //实体类路径
	private String daoPath; //dao层路径
	private String daoImplPath; //daoImpl层路径
	private String servicePath; //service层路径
	
	private String daoTemplatePath; //生成dao 模版路径
	private String daoImplTemplatePath; //生成dao.impl 模版路径
	private String serviceTemplatePath; //生成service 模版路径
	private String serviceImplTemplatePath;//生成ServiceImpl模板路径
	private String serviceImplPath;//serviceImpl层路径
	
	//初始化参数 从配置文件中得到
	public void init() {
		String propertyPath = "src/com/hongwei/futures/developer/config.properties"; //使用默认路径
		init(propertyPath);
	}
	
	public void init(String propertyPath) {
		Map<String, String> properties = Property.getProperties(propertyPath);
		entityPath = properties.get("entityPath");
		daoPath = properties.get("daoPath");
		daoImplPath = properties.get("daoImplPath");
		servicePath = properties.get("servicePath");
		serviceImplPath = properties.get("serviceImplPath");
		daoTemplatePath = properties.get("daoTemplatePath");
		daoImplTemplatePath = properties.get("daoImplTemplatePath");
		serviceTemplatePath = properties.get("serviceTemplatePath");
		serviceImplTemplatePath = properties.get("serviceImplTemplatePath");
	}
	
	//根据包的路径得到package XXX中的XXX
	private String getImportPath(String path) {
		StringBuffer result = new StringBuffer();
		String [] strings = path.split("/");
		for(int i = 1, size = strings.length; i < size; i++) {
			result.append(".").append(strings[i]);
		}
		return result.substring(1);
	}
	
	//得到实体名称
	private String getEntityName(File entity) {
		return entity.getName().substring(0, entity.getName().indexOf("."));
	}
	
	@SuppressWarnings("unused")
	public void createFile(boolean daoFlag, boolean daoImplFlag, boolean serviceFlag,boolean serviceImplFlag) {
		try {
			//得到所有实体类文件
			File entityDir = new File(entityPath);
			entityPath = getImportPath(entityPath);
			File[] entities = entityDir.listFiles();
			
			FileOperate fo = new FileOperate(); //文件操作类
			//
			String daoImport = getImportPath(daoPath);
			String daoImplImport = getImportPath(daoImplPath);
			String serviceImport = getImportPath(servicePath);
			String serviceImportPath=getImportPath(serviceImplPath);
			String daoContent = fo.readTxt(daoTemplatePath, "UTF-8");
			String daoImplContent = fo.readTxt(daoImplTemplatePath, "UTF-8");
			String serviceContent = fo.readTxt(serviceTemplatePath, "UTF-8");
			String serviceImplContent = fo.readTxt(serviceImplTemplatePath, "UTF-8");
			String content = ""; //生成文件的类容
			String filePath = ""; //生成文件的位置
			
			//baseDao, baseImplDao 名字和路径
			String baseDaoName = "BaseDao";  
			String baseDaoImplName = "BaseDaoImpl";
			String baseDao = daoImport + "." + baseDaoName;
			String baseDaoImpl = daoImplImport + "." + baseDaoImplName;
			
			for (File entity : entities) {
				//过滤掉配置文件
				if (!entity.getName().endsWith("java")) {
					continue;
				}
				
				String entityName = getEntityName(entity);
				String modelEntity = StringUtil.firstCharLowerCase(getEntityName(entity));
				String daoName = entityName + "Dao";
				String daoEntity = StringUtil.firstCharLowerCase(daoName);
				String daoImplName = entityName + "DaoImpl";
				String serviceName = entityName + "Service";
				String model = entityPath + "." + entityName;
				String dao = daoImport + "." + daoName;
				String daoImpl = daoImplImport + "." + daoImplName;
				String entityId = modelEntity + "Id";
				//生成dao ------------------------------------------------------------------------------
				if (daoFlag) {
					content = daoContent.replaceAll("#package#", daoImport);
					content = content.replaceAll("#baseDao#", baseDao);
					content = content.replaceAll("#model#", model);
					content = content.replaceAll("#daoName#", daoName);
					content = content.replaceAll("#entityName#", entityName);
					filePath = daoPath + "/" + entityName + "Dao.java";
					//如果存在就不创建
					if (!new File(filePath).exists()) {
						fo.createFile(filePath, content);
					}
				}
				//生成daoImpl ------------------------------------------------------------------------------
				if (daoImplFlag) {
					content = daoImplContent.replaceAll("#package#", daoImplImport);
					content = content.replaceAll("#baseDaoImpl#", baseDaoImpl);
					content = content.replaceAll("#dao#", dao);
					content = content.replaceAll("#model#", model);
					content = content.replaceAll("#daoImplName#", daoImplName);
					content = content.replaceAll("#entityName#", entityName);
					content = content.replaceAll("#daoName#", daoName);
					
					filePath = daoImplPath + "/" + entityName + "DaoImpl.java";
					//如果存在就不创建
					if (!new File(filePath).exists()) {
						fo.createFile(filePath, content);
					}
				}
				//生成service ------------------------------------------------------------------------------
				if (serviceFlag) {
					content = serviceContent.replaceAll("#package#", serviceImport);
					content = content.replaceAll("#dao#", dao);
					content = content.replaceAll("#model#", model);
					content = content.replaceAll("#serviceName#", serviceName);
					
					content = content.replaceAll("#daoName#", daoName);
					content = content.replaceAll("#daoEntity#", daoEntity);
					content = content.replaceAll("#entityName#", entityName);
					content = content.replaceAll("#entityId#", entityId);
					
					filePath = servicePath + "/" + entityName + "Service.java";
					//如果存在就不创建
					if (!new File(filePath).exists()) {
						fo.createFile(filePath, content);
					}
				}
				//生成service ------------------------------------------------------------------------------
				if (serviceImplFlag) {
					content = serviceImplContent.replaceAll("#package#", serviceImportPath);
					content = content.replaceAll("#dao#", dao);
					content = content.replaceAll("#model#", model);
					content = content.replaceAll("#serviceName#", serviceName);
					
					content = content.replaceAll("#daoName#", daoName);
					content = content.replaceAll("#daoEntity#", daoEntity);
					content = content.replaceAll("#entityName#", entityName);
					content = content.replaceAll("#entityId#", entityId);
					
					filePath = serviceImplPath+"/" + entityName + "ServiceImpl.java";
					//如果存在就不创建
					if (!new File(filePath).exists()) {
						fo.createFile(filePath, content);
					}
				}					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			ModuleGenerator mg = new ModuleGenerator();
			mg.init();
			mg.createFile(true, true, true,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
