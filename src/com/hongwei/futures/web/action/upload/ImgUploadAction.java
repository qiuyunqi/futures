package com.hongwei.futures.web.action.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ServiceException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSErrorCode;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.CannedAccessControlList;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.hongwei.futures.util.Constants;
import com.hongwei.futures.util.ImageUtil;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage(value = "upload")
public class ImgUploadAction extends BaseAction {

	private static final long serialVersionUID = -7959400006990415368L;
	
	private File imgFile;
	private List<File> files;
	private List<String> filesFileName;
	private List<String> filesFileContentType;
	private String imgFileFileName;
	private String imgFileContentType;
	private String savePath = Constants.DIR_TEMP;
	private String saveUrl;
	private Double width = 800.0;
	private Double height = 800.0;
	private Boolean largePic = false;
	private String[] types = new String[] { ".bmp", ".png", ".gif", ".jpeg", ".pjpeg", ".jpg" };
	private String[] fileType = new String[] { ".exe", ".jar", ".dll", ".jsp", ".class", ".sh", ".bat" };
	private long maxSize = 10000000;
	private long maxFileSize = 20000000;

	private Long fileId;// 文件ID

	// private static final String ACCESS_ID = "VyeVZti9TY3t2NOU";
	// private static final String ACCESS_KEY =
	// "lR05tqefMFeEShbPvg7CbsAxtBGPka";

	@Action(value = "img")
	public String execute() {
		try {
			if (imgFile != null) {
				long size = getImgFile().length();
				if (size > maxSize) {
					alert(1, "图片应小于10M!");
					return null;
				}
				boolean admit = true;
				String fileType = ".jpg";
				for (int i = 0; i < types.length; i++) {
					if (types[i].equalsIgnoreCase(imgFileFileName.substring(imgFileFileName.lastIndexOf(".")))) {
						admit = false;
						if (types[i].endsWith(".gif"))
							fileType = ".gif";
						if (types[i].endsWith(".png"))
							fileType = ".png";
					}
				}
				if (admit) {
					alert(1, "上传图片类型不正确!");
					return null;
				}
				String fileName = (System.currentTimeMillis() + (new Random(999999).nextLong())) + fileType;
				String path = ServletActionContext.getServletContext().getRealPath(getSavePath()) + File.separator + fileName;
				try {
					if (Constants.OSSEnable) {
						if (!imgFile.exists()) // 文件不存在时
							return null;
						// String picFrom = imgFile.getAbsolutePath();
						// int quality = (int) ((largePic ? 204800d :
						// 102400d)/imgFile.length() * 100);
						// if (largePic && quality >= 100) {
						// } else {
						// if (quality >= 100) {
						// quality = 0;
						// } else {
						// quality = 90;
						// }
						// ImageUtil.resize(picFrom, path, width.intValue(),
						// height
						// .intValue(), quality);
						// imgFile=new File(path);
						// }
						String bucketName = "hhr360oss";
						// 使用默认的OSS服务器地址创建OSSClient对象。
						OSSClient client = new OSSClient(Property.getProperty("OSS_ACCESS_ID"), Property.getProperty("OSS_ACCESS_KEY"));
						ensureBucket(client, bucketName);
						ObjectMetadata objectMeta = new ObjectMetadata();
						objectMeta.setContentLength(imgFile.length());
						InputStream input = new FileInputStream(imgFile);
						client.putObject(bucketName, fileName, input, objectMeta);
						String saveUrl = Property.getProperty("OSS_URL") + fileName;
						JSONObject response = new JSONObject();
						response.put("fileName", imgFileFileName);
						response.put("fileSize", (int) size / 1024);
						response.put("error", 0);
						response.put("url", saveUrl);
						response.put("saveDir", saveUrl);
						write(response.toString());
						return null;
					} else {
						if (!imgFile.exists()) // 文件不存在时
							return null;
						String picFrom = imgFile.getAbsolutePath();
						int quality = (int) ((largePic ? 204800d : 102400d) / imgFile.length() * 100);
						if (largePic && quality >= 100) {
							saveFile(getImgFile(), path);
						} else {
							if (quality >= 100) {
								quality = 0;
							} else {
								quality = 90;
							}
							ImageUtil.resize(picFrom, path, width.intValue(), height.intValue(), quality);
						}
						String saveUrl = savePath + "/" + fileName;
						String url = getHttpServletRequest().getContextPath() + saveUrl;
						JSONObject response = new JSONObject();
						response.put("fileName", imgFileFileName);
						response.put("fileSize", (int) size / 1024);
						response.put("error", 0);
						response.put("url", url);
						response.put("saveDir", saveUrl);
						write(response.toString());
						return null;
					}
				} catch (Exception e) {
					e.printStackTrace();
					alert(1, "上传图片异常!");
					return null;
				}
			} else {
				alert(1, "请选择上传图片!");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void ensureBucket(OSSClient client, String bucketName) throws OSSException, ClientException {

		try {
			// 创建bucket
			client.createBucket(bucketName);
			client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
		} catch (ServiceException e) {
			if (!OSSErrorCode.BUCKES_ALREADY_EXISTS.equals(e.getErrorCode())) {
				// 如果Bucket已经存在，则忽略
				throw e;
			}
		}
	}

	@Action(value = "upFile")
	public String fileUpload() throws Exception {
		if (imgFile != null) {
			if (imgFileFileName.lastIndexOf(".") == -1) {
				alert(1, "上传文件类型不正确！");
				return null;
			}
			String fileName = ((new Date()).getTime() + Math.random() * (1000)) + imgFileFileName.substring(imgFileFileName.lastIndexOf("."));
			String path = ServletActionContext.getServletContext().getRealPath(getSavePath()) + File.separator + fileName;
			long size = getImgFile().length();
			if (size > maxFileSize) {
				alert(1, "上传文件过大!");
				return null;
			}
			boolean admit = false;
			for (int i = 0; i < types.length; i++) {
				if (fileType[i].equalsIgnoreCase(imgFileFileName.substring(imgFileFileName.lastIndexOf(".")))) {
					admit = true;
				}
				if (admit)
					break;
			}
			if (admit) {
				alert(1, "上传文件类型不正确!");
				return null;
			}
			try {
				saveFile(getImgFile(), path);
				String saveUrl = savePath + "/" + fileName;
				String url = getHttpServletRequest().getContextPath() + saveUrl;
				getHttpServletResponse().setCharacterEncoding("utf-8");
				getHttpServletResponse().setContentType("text/html");
				JSONObject array = new JSONObject();
				array.put("fileName", imgFileFileName);
				DecimalFormat df = new DecimalFormat(".#");
				array.put("fileSize", size < 1024 ? (size + "Bytes") : (df.format((double) size / 1024) + "KB"));
				array.put("error", 0);
				array.put("url", url);
				array.put("saveDir", saveUrl);
				getHttpServletResponse().getWriter().write(array.toString());
				getHttpServletResponse().getWriter().flush();
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				alert(1, "上传文件异常!");
				return null;
			}
		} else {
			alert(1, "请选择上传文件!");
			return null;
		}
	}

	/**
	 * 图片压缩
	 * 
	 * @param picFrom
	 *            待压缩的图片保存路径
	 * @param picTo
	 *            压缩后的图片保存路径
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @throws Exception
	 */
	public static void comPress(String picFrom, String picTo, double width, double height) throws Exception {
		ImageUtil.resize(picFrom, picTo, (int) width, (int) height);
	}

	/**
	 * 图片压缩 作者：漆传涛
	 * 
	 * @param savePath
	 *            文件保存的真实路径
	 * @param oldFile
	 *            压缩文件
	 * @param width
	 *            文件压缩宽
	 * @param height
	 *            文件压缩高
	 * @throws Exception
	 */
	public void comPress(String savePath, File oldFile, double width, double height, boolean largePic) {
		try {
			if (!oldFile.exists()) // 文件不存在时
				return;
			String picFrom = oldFile.getAbsolutePath();
			int quality = (int) ((largePic ? 200000d : 80000d) / oldFile.length() * 100);
			if (quality >= 100) {
				quality = 0;
			} else {
				if (quality < 70) {
					quality = 50;
				}
			}
			ImageUtil.resize(picFrom, savePath, (int) width, (int) height, quality);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alert(int error, String msg) {
		try {
			getHttpServletResponse().setCharacterEncoding("utf-8");
			getHttpServletResponse().setContentType("text/html");
			JSONObject array = new JSONObject();
			array.put("error", error);
			array.put("message", msg);
			getHttpServletResponse().getWriter().write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveFile(File file, String path) throws Exception {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		bis = new BufferedInputStream(new FileInputStream(file));
		bos = new BufferedOutputStream(new FileOutputStream(path));
		try {
			byte[] buf = new byte[1024 * 1024];
			int len = 0;
			while (((len = bis.read(buf)) != -1)) {
				bos.write(buf, 0, len);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			bis.close();
			bos.close();
		}
	}

	// ///////////////////////////////////////**************************************///////////////////////

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileContentType(List<String> filesFileContentType) {
		this.filesFileContentType = filesFileContentType;
	}

	public List<String> getFilesFileContentType() {
		return filesFileContentType;
	}

	public void setFileType(String[] fileType) {
		this.fileType = fileType;
	}

	public String[] getFileType() {
		return fileType;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getWidth() {
		return width;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getHeight() {
		return height;
	}

	public void setLargePic(Boolean largePic) {
		this.largePic = largePic;
	}

	public Boolean getLargePic() {
		return largePic;
	}
}
