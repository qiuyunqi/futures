package com.hongwei.futures.developer;

import java.io.File;

public class RemoveSvn {

	/**去除文件夹中的SVN文件夹
	 * @param args
	 */
	public static void main(String[] args) {
		String path="D:\\管理社区";
		File file=new File(path);
		deleteSvn(file);
	}
	
	public static void deleteSvn(File file){
		if(!file.isDirectory())return;
		if(file.getName().equals(".svn")){
			//System.out.println(file.getName());
			deleteFile(file);
		}else{
			File[] files=file.listFiles();
			for(int i=0;i<files.length;i++){
				//System.out.println(files[i].getName());
				deleteSvn(files[i]);
			}
		}
	}
	public static void deleteFile(File file){
		if(file.isDirectory()){
			File[] files=file.listFiles();
			for(int i=0;i<files.length;i++){
				//System.out.println(files[i].getName());
				deleteFile(files[i]);
			}
			file.delete();
		}
		else{
			file.delete();
			return;
		}
	}

}
