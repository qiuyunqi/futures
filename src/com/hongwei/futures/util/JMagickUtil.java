package com.hongwei.futures.util;

import java.awt.Rectangle;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;

import magick.CompositeOperator;
import magick.ImageInfo;
import magick.MagickImage;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;

/**
 * JMagick 处理图片
 * 
 * @author
 * @since 2010-5-20
 */
public class JMagickUtil {

	public static String imageMagickPath = null;

	static {
		System.setProperty("jmagick.systemclassloader", "no");
		imageMagickPath = "C:\\Program Files\\ImageMagick-6.7.9-Q8";
	}

	/**
	 * 是否是合法图片
	 * 
	 * @param suffix
	 *            图片文件后缀
	 * @param imageContent
	 *            图片内容
	 * @return
	 */
	public static boolean isImage(String suffix, byte[] imageContent) {
		try {
			MagickImage image = new MagickImage(new ImageInfo(suffix),
					imageContent);
			if (image == null || image.getDimension().getWidth() <= 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是合法图片
	 * 
	 * @param imageFullPath
	 *            图片本地绝对路径
	 * @return
	 */
	public static boolean isImage(String localImagePath) {
		if (localImagePath == null || !new File(localImagePath).isFile()) {
			return false;
		}
		try {
			MagickImage image = new MagickImage(new ImageInfo(localImagePath));
			if (image.getDimension() == null
					|| image.getDimension().getWidth() <= 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 根据要求的坐标截取图片
	 * 
	 * @param source
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void cropPart(String imageFullPath, int x, int y, int width,
			int height) throws Exception {
		MagickImage image = null;
		ImageInfo info = null;
		// 取得原文件
		try {
			info = new ImageInfo(imageFullPath);
			// 获取图片
			image = new MagickImage(info);
			// 原始尺寸
			int beforeScaleX = image.getDimension().width;
			int beforeScaleY = image.getDimension().height;

			// 是否需要这个约束
			int cropWidth = (x + width > beforeScaleX) ? (beforeScaleX - x)
					: width;
			int cropHeight = (y + height > beforeScaleY) ? (beforeScaleY - y)
					: height;

			MagickImage small = image.cropImage(new Rectangle(x, y, cropWidth,
					cropHeight));
			small.setFileName(imageFullPath);
			small.writeImage(new ImageInfo());
			small.destroyImages();
		} finally {
			if (image != null) {
				image.destroyImages();
			}
		}
	}

	/**
	 * 将imageFullPath指定的图片进行等比缩放，最长的边为<t>maxEdgeLength</t>
	 * 
	 * @param imageFullPath
	 *            ：需要裁剪的图片绝对路径
	 * @param edgeLength
	 *            : 边长
	 * @return
	 */
	public static boolean resizeImage(String imageFullPath, int maxEdgeLength)
			throws Exception {
		// 取得原文件
		MagickImage image = new MagickImage(new ImageInfo(imageFullPath));
		String suffix = image.getImageFormat();
		if (suffix.equalsIgnoreCase("gif")) {
			image = extractFirstFrame(image);
		}
		// 原始尺寸
		int width = image.getDimension().width;
		int height = image.getDimension().height;

		boolean isWidthLonger = width > height ? true : false;

		// 得到调整后的尺寸及缩小的比例,如果{width,height}都小于等于maxEdgeLength，直接返回
		if (width > maxEdgeLength || height > maxEdgeLength) {
			double ratio;

			if (isWidthLonger) {
				ratio = ((double) maxEdgeLength) / width;
				width = maxEdgeLength;
				height = ((Double) Math.floor(ratio * height)).intValue();
			} else {
				ratio = ((double) maxEdgeLength) / height;
				width = ((Double) Math.floor(ratio * width)).intValue();
				height = maxEdgeLength;
			}

			MagickImage small = image.scaleImage(width, height);
			small.setFileName(imageFullPath);
			small.writeImage(new ImageInfo());
			small.destroyImages();
		}
		return true;
	}

	/**
	 * 截取第一帧
	 * 
	 * @param image
	 *            gif动画
	 * @return
	 */
	private static MagickImage extractFirstFrame(MagickImage image)
			throws Exception {
		MagickImage[] frames = image.breakFrames();
		return frames[0];
	}

	/**
	 * 对imageFullPath 指定的文件按要求的质量quality进行压缩(gif将不会进行压缩)。quality的范围是(0-100)
	 * 
	 * @param imageFullPath
	 *            文件的绝对路径
	 * @param quality
	 *            压缩的质量,范围是(0-100)
	 * @param maxFileSize
	 *            文件超过该大小才进行质量有损压缩,单位:byte
	 * @return 文件大小,单位:byte
	 */
	public static int compressImage(String imageFullPath, int quality,
			long maxFileSize) throws Exception {
		// 1. entry validation
		if (StringUtil.isBlank(imageFullPath) || quality <= 0 || quality >= 100) {
			return -1;
		}
		int i = imageFullPath.lastIndexOf(".");
		if (i < 0) {
			return -1;
		}

		// 2. compress
		String fileName = imageFullPath.substring(0, i);
		File fileSrc = new File(imageFullPath);

		ImageInfo info = null;
		MagickImage image = null;
		try {
			info = new ImageInfo(imageFullPath);
			image = new MagickImage(info);

			if (null == image || image.getDimension() == null
					|| image.getDimension().getWidth() <= 0) {
				return -1;
			}

			String type = image.getImageFormat();

			if ("gif".equalsIgnoreCase(type)) {
				// 解决 trojan.giframe 病毒 问题，gif 也通过MagickImage另存一下图片
				fileName = fileName + ".gif";
				image.setFileName(fileName);
				image.writeImage(new ImageInfo());
			} else {
				if (fileSrc.length() > maxFileSize) {// 大于指定文件大小,进行压缩
					// 调整图片品质 最佳为40~50
					info.setQuality(quality);
					image.profileImage("*", null);
					image.setImageAttribute("comment", null);
					image.setImageAttribute("JPEG-Sampling-factors", null);
					image.setFileName(imageFullPath);
					image.writeImage(info);
				}
			}
			return (int) fileSrc.length();
		} finally {
			if (image != null) {
				image.destroyImages();
			}
		}
	}

	/**
	 * 获取图片信息,包括宽／高／大小／类型，如果取不到会抛异常
	 * 
	 * @param imageFullPath
	 * @return
	 * @throws Exception
	 */
	public static ImageFile getImageInfo(String imageFullPath) throws Exception {
		return getImageInfo(imageFullPath, false);
	}

	/**
	 * 获取图片信息+EXIF信息,如果非图片格式会抛异常
	 * 
	 * @param localImagePath
	 *            本地图片路径
	 * @param isReadExif
	 *            是否需要读取exif信息
	 * @return
	 * @throws Exception
	 */
	public static ImageFile getImageInfo(String localImagePath,
			boolean isReadExif) throws Exception {
		File file = new File(localImagePath);
		if (!file.isFile()) {
			throw new Exception("file not exists or not a file, file="
					+ localImagePath);
		}
		ImageFile imageFile = new ImageFile();
		MagickImage image = new MagickImage(new ImageInfo(localImagePath));
		if (image.getDimension() == null
				|| image.getDimension().getWidth() <= 0) {
			throw new Exception("get image's dimension error, file="
					+ localImagePath);
		}
		imageFile.setFile(new File(localImagePath));
		imageFile.setHeight(image.getDimension().getHeight());
		imageFile.setWidth(image.getDimension().getWidth());
		imageFile.setType(ImageType.toImageType(image.getImageFormat()));
		imageFile.setSize(file.length());
		if (isReadExif) {
			imageFile.setExif(readImageExif(image));
		}
		return imageFile;
	}

	private static Method[] exifMethods = ImageExif.class.getMethods();

	private static ImageExif readImageExif(MagickImage image) {
		ImageExif exif = new ImageExif();
		if (image == null) {
			return exif;
		}
		try {
			for (Method method : exifMethods) {
				if ("set".equals(method.getName().substring(0, 3))) {
					if (method.getName().length() > 3) {
						method.invoke(exif, image.getImageAttribute("EXIF:"
								+ method.getName().substring(3)));
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("read image exif error", e);
		}
		return exif;
	}

	/**
	 * 给图片打水印(本地图片会被替换成有水印的图片)，如果图片宽高要小于水印宽高+x或y，则不会打水印
	 * 
	 * @param localImage
	 *            源图的本地绝对路径
	 * @param markImage
	 *            水印图片的绝对路径
	 * @param maskType
	 *            0-右下角, 1-左下角, 2-正中间, 3-左上角, 4-右上角, 5-自定义
	 * @param x
	 *            离横向边间隔距离,如左对齐则左边距,右对齐则是右边距,居中传0,自定义则为左边距,单位:px
	 * @param y
	 *            离纵向边距离,如上对齐则上边距,下对齐则是下边距,居中传0,自定义则为上边距,单位:px
	 * @return
	 * @throws Exception
	 */
	public static boolean mask(String localImage, String markImage,
			int maskType, int x, int y) throws Exception {
		return mask(localImage, localImage, markImage, maskType, x, y);
	}

	/**
	 * 给图片打水印(生成目标图片会带水印)，如果图片宽高要小于水印宽高+endX或endY，则不会打水印
	 * 
	 * @param imageBytes
	 *            源图的byte数组
	 * @param markImage
	 *            水印图片的绝对路径
	 * @param maskType
	 *            0-右下角, 1-左下角, 2-正中间, 3-左上角, 4-右上角, 5-自定义
	 * @param x
	 *            离横向边间隔距离,如左对齐则左边距,右对齐则是右边距,居中传0,自定义则为左边距,单位:px
	 * @param y
	 *            离纵向边距离,如上对齐则上边距,下对齐则是下边距,居中传0,自定义则为上边距,单位:px
	 * @return 处理后图片的byte数组
	 * @throws Exception
	 */
	public static byte[] mask(byte[] imageBytes, String markImage,
			int maskType, int x, int y) throws Exception {
		ImageInfo info = new ImageInfo();
		MagickImage src = null;
		MagickImage logo = null;
		try {
			src = new MagickImage(info, imageBytes);
			logo = new MagickImage(new ImageInfo(markImage));
			maskCore(src, src, info, logo, maskType, x, y);
			return src.imageToBlob(info);
		} finally {
			if (src != null) {
				src.destroyImages();
			}
			if (logo != null) {
				logo.destroyImages();
			}
		}
	}

	/**
	 * 给图片打水印(生成目标图片会带水印)，如果图片宽高要小于水印宽高+x或y，则不会打水印
	 * 
	 * @param localImage
	 *            源图的本地绝对路径
	 * @param destImage
	 *            目标图片的本地绝对路径
	 * @param markImage
	 *            水印图片的绝对路径
	 * @param maskType
	 *            0-右下角, 1-左下角, 2-正中间, 3-左上角, 4-右上角, 5-自定义
	 * @param x
	 *            离横向边间隔距离,如左对齐则左边距,右对齐则是右边距,居中传0,自定义则为左边距,单位:px
	 * @param y
	 *            离纵向边距离,如上对齐则上边距,下对齐则是下边距,居中传0,自定义则为上边距,单位:px
	 * @return
	 * @throws Exception
	 */
	public static boolean mask(String localImage, String destImage,
			String markImage, int maskType, int x, int y) throws Exception {
		MagickImage src = null;
		MagickImage logo = null;
		MagickImage dest = null;
		try {
			ImageInfo info = new ImageInfo(localImage);
			src = new MagickImage(info);
			logo = new MagickImage(new ImageInfo(markImage));
			dest = new MagickImage(info);
			dest.setFileName(destImage);
			return maskCore(src, dest, info, logo, maskType, x, y);
		} finally {
			if (src != null) {
				src.destroyImages();
			}
			if (logo != null) {
				logo.destroyImages();
			}
			if (dest != null) {
				dest.destroyImages();
			}
		}
	}

	/**
	 * 打水印主逻辑
	 * 
	 * @param src
	 *            源图
	 * @param dest
	 *            目标图
	 * @param writeInfo
	 *            目标图的ImageInfo
	 * @param logo
	 *            水印
	 * @param maskType
	 *            0-右下角, 1-左下角, 2-正中间, 3-左上角, 4-右上角, 5-自定义
	 * @param marginX
	 *            离横向边间隔距离,如左对齐则左边距,右对齐则是右边距,居中传0,自定义则为左边距,单位:px
	 * @param marginY
	 *            离纵向边距离,如上对齐则上边距,下对齐则是下边距,居中传0,自定义则为上边距,单位:px
	 * @return
	 * @throws Exception
	 */
	private static boolean maskCore(MagickImage src, MagickImage dest,
			ImageInfo writeInfo, MagickImage logo, int maskType, int marginX,
			int marginY) throws Exception {
		// 校验图片合法性
		if (src == null || src.getDimension() == null || dest == null
				|| dest.getDimension() == null || logo == null
				|| logo.getDimension() == null) {
			return false;
		}
		// gif图片处理,gif多桢不处理,单桢则处理
		String suffix = src.getImageFormat();
		if (suffix.equalsIgnoreCase("gif")) {
			MagickImage[] frames = src.breakFrames();
			if (frames.length > 1) {
				return false;
			}
			src = frames[0];
		}
		double srcW = src.getDimension().getWidth();
		double srcH = src.getDimension().getHeight();
		double logoW = logo.getDimension().getWidth();
		double logoH = logo.getDimension().getHeight();
		if (srcW <= 0 || dest.getDimension().getWidth() <= 0 || logoW <= 0) {
			return false;
		}
		int x = 0, y = 0;

		switch (maskType) {
		// 左下角
		case 1:
			x = marginX;
			y = (int) (srcH - logoH - marginY);
			break;
		// 正中间
		case 2:
			x = (int) ((srcW - logoW) / 2);
			y = (int) ((srcH - logoH) / 2);
			break;
		// 左上角
		case 3:
			x = marginX;
			y = marginY;
			break;
		// 右上角
		case 4:
			x = (int) (srcW - logoW - marginX);
			y = marginY;
			break;
		// 自定义
		case 5:
			x = marginX;
			y = marginY;
			break;
		// 右下角
		case 0:
			// 其它值默认右下角
		default:
			x = (int) (srcW - logoW - marginX);
			y = (int) (srcH - logoH - marginY);
		}

		// 校验水印是否全部落在图片中
		if (x <= 0 || y <= 0 || x > srcW - logoW || y > srcH - logoH) {
			return false;
		}

		dest.compositeImage(CompositeOperator.AtopCompositeOp, logo, x, y);
		dest.writeImage(writeInfo);
		return true;
	}

	public static void main(String[] args) {
		try {
			cutImage(100, 100, "D:/imagetest/IMG_0803.jpg",
					"D:/imagetest/test.jpg");
			File jpegFile = new File("D:/imagetest/IMG_0803.jpg");
			Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
			Directory exif = metadata.getDirectory(ExifDirectory.class);
			Iterator<Tag> tags = exif.getTagIterator();
			while (tags.hasNext()) {
				Tag tag=tags.next();
				System.out.println(tag.getTagName() + ":" + tag.getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据尺寸缩放图片
	 * 
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @param srcPath
	 *            源图片路径
	 * @param newPath
	 *            缩放后图片的路径
	 */
	public static void cutImage(int width, int height, String srcPath,
			String newPath) throws Exception {
		IMOperation op = new IMOperation();
		op.addImage(srcPath);
		op.resize(width, height);
		op.addImage(newPath);
		ConvertCmd convert = new ConvertCmd();
		convert.setSearchPath(imageMagickPath);
		convert.run(op);
	}
}
