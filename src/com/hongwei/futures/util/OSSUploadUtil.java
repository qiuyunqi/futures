package com.hongwei.futures.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ServiceException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSErrorCode;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.CannedAccessControlList;
import com.aliyun.openservices.oss.model.ObjectMetadata;

public class OSSUploadUtil {
	private static String[] types = new String[] { ".bmp", ".png", ".gif", ".jpeg", ".pjpeg", ".jpg" };

	// private static final String ACCESS_ID = "VyeVZti9TY3t2NOU";
	// private static final String ACCESS_KEY =
	// "lR05tqefMFeEShbPvg7CbsAxtBGPka";

	public static String imageFileUpload(byte[] fileByte, String user_avatar, String suffix) throws Exception {
		String fileType = ".jpg";
		for (int i = 0; i < types.length; i++) {
			if (types[i].equalsIgnoreCase(user_avatar.substring(user_avatar.lastIndexOf(".")))) {
				if (types[i].endsWith(".gif"))
					fileType = ".gif";
				if (types[i].endsWith(".png"))
					fileType = ".png";
			}
		}
		String fileName = (System.currentTimeMillis() + (new Random(999999).nextLong())) + suffix + fileType;
		try {
			InputStream input = new ByteArrayInputStream(fileByte);
			String bucketName = "hhr360oss";
			// 使用默认的OSS服务器地址创建OSSClient对象。
			OSSClient client = new OSSClient(Property.getProperty("OSS_ACCESS_ID"), Property.getProperty("OSS_ACCESS_KEY"));
			ensureBucket(client, bucketName);
			ObjectMetadata objectMeta = new ObjectMetadata();
			objectMeta.setContentLength(fileByte.length);
			client.putObject(bucketName, fileName, input, objectMeta);
			String saveUrl = Property.getProperty("OSS_URL") + fileName;
			// response.put("fileName", user_avatar);
			// response.put("fileSize", (int) size / 1024);
			// response.put("error", 0);
			// response.put("url", saveUrl);
			// response.put("saveDir", saveUrl);
			return saveUrl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void ensureBucket(OSSClient client, String bucketName) throws OSSException, ClientException {
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
}
