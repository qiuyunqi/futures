package com.hongwei.futures.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;

public class WeiXinUtil {
	private static final Log logger = LogFactory.getLog(WeiXinUtil.class);
    private static final byte[] encodingTable = {
            (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E',
            (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I', (byte) 'J',
            (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N', (byte) 'O',
            (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T',
            (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y',
            (byte) 'Z', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd',
            (byte) 'e', (byte) 'f', (byte) 'g', (byte) 'h', (byte) 'i',
            (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n',
            (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's',
            (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w', (byte) 'x',
            (byte) 'y', (byte) 'z', (byte) '0', (byte) '1', (byte) '2',
            (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) '+', (byte) '/'
        };
    private static final byte[] decodingTable;
    static {
        decodingTable = new byte[128];
        for (int i = 0; i < 128; i++) {
            decodingTable[i] = (byte) -1;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            decodingTable[i] = (byte) (i - 'A');
        }
        for (int i = 'a'; i <= 'z'; i++) {
            decodingTable[i] = (byte) (i - 'a' + 26);
        }
        for (int i = '0'; i <= '9'; i++) {
            decodingTable[i] = (byte) (i - '0' + 52);
        }
        decodingTable['+'] = 62;
        decodingTable['/'] = 63;
    }
    public static byte[] encode(byte[] data) {
        byte[] bytes;
        int modulus = data.length % 3;
        if (modulus == 0) {
            bytes = new byte[(4 * data.length) / 3];
        } else {
            bytes = new byte[4 * ((data.length / 3) + 1)];
        }
        int dataLength = (data.length - modulus);
        int a1;
        int a2;
        int a3;
        for (int i = 0, j = 0; i < dataLength; i += 3, j += 4) {
            a1 = data[i] & 0xff;
            a2 = data[i + 1] & 0xff;
            a3 = data[i + 2] & 0xff;
            bytes[j] = encodingTable[(a1 >>> 2) & 0x3f];
            bytes[j + 1] = encodingTable[((a1 << 4) | (a2 >>> 4)) & 0x3f];
            bytes[j + 2] = encodingTable[((a2 << 2) | (a3 >>> 6)) & 0x3f];
            bytes[j + 3] = encodingTable[a3 & 0x3f];
        }
        int b1;
        int b2;
        int b3;
        int d1;
        int d2;
        switch (modulus) {
        case 0: /* nothing left to do */
            break;
        case 1:
            d1 = data[data.length - 1] & 0xff;
            b1 = (d1 >>> 2) & 0x3f;
            b2 = (d1 << 4) & 0x3f;
            bytes[bytes.length - 4] = encodingTable[b1];
            bytes[bytes.length - 3] = encodingTable[b2];
            bytes[bytes.length - 2] = (byte) '=';
            bytes[bytes.length - 1] = (byte) '=';
            break;
        case 2:
            d1 = data[data.length - 2] & 0xff;
            d2 = data[data.length - 1] & 0xff;
            b1 = (d1 >>> 2) & 0x3f;
            b2 = ((d1 << 4) | (d2 >>> 4)) & 0x3f;
            b3 = (d2 << 2) & 0x3f;
            bytes[bytes.length - 4] = encodingTable[b1];
            bytes[bytes.length - 3] = encodingTable[b2];
            bytes[bytes.length - 2] = encodingTable[b3];
            bytes[bytes.length - 1] = (byte) '=';
            break;
        }
        return bytes;
    }
    public static byte[] decode(byte[] data) {
        byte[] bytes;
        byte b1;
        byte b2;
        byte b3;
        byte b4;
        data = discardNonBase64Bytes(data);
        if (data[data.length - 2] == '=') {
            bytes = new byte[(((data.length / 4) - 1) * 3) + 1];
        } else if (data[data.length - 1] == '=') {
            bytes = new byte[(((data.length / 4) - 1) * 3) + 2];
        } else {
            bytes = new byte[((data.length / 4) * 3)];
        }
        for (int i = 0, j = 0; i < (data.length - 4); i += 4, j += 3) {
            b1 = decodingTable[data[i]];
            b2 = decodingTable[data[i + 1]];
            b3 = decodingTable[data[i + 2]];
            b4 = decodingTable[data[i + 3]];
            bytes[j] = (byte) ((b1 << 2) | (b2 >> 4));
            bytes[j + 1] = (byte) ((b2 << 4) | (b3 >> 2));
            bytes[j + 2] = (byte) ((b3 << 6) | b4);
        }
        if (data[data.length - 2] == '=') {
            b1 = decodingTable[data[data.length - 4]];
            b2 = decodingTable[data[data.length - 3]];
            bytes[bytes.length - 1] = (byte) ((b1 << 2) | (b2 >> 4));
        } else if (data[data.length - 1] == '=') {
            b1 = decodingTable[data[data.length - 4]];
            b2 = decodingTable[data[data.length - 3]];
            b3 = decodingTable[data[data.length - 2]];
            bytes[bytes.length - 2] = (byte) ((b1 << 2) | (b2 >> 4));
            bytes[bytes.length - 1] = (byte) ((b2 << 4) | (b3 >> 2));
        } else {
            b1 = decodingTable[data[data.length - 4]];
            b2 = decodingTable[data[data.length - 3]];
            b3 = decodingTable[data[data.length - 2]];
            b4 = decodingTable[data[data.length - 1]];
            bytes[bytes.length - 3] = (byte) ((b1 << 2) | (b2 >> 4));
            bytes[bytes.length - 2] = (byte) ((b2 << 4) | (b3 >> 2));
            bytes[bytes.length - 1] = (byte) ((b3 << 6) | b4);
        }
        return bytes;
    }
    public static byte[] decode(String data) {
        byte[] bytes;
        byte b1;
        byte b2;
        byte b3;
        byte b4;
        data = discardNonBase64Chars(data);
        if (data.charAt(data.length() - 2) == '=') {
            bytes = new byte[(((data.length() / 4) - 1) * 3) + 1];
        } else if (data.charAt(data.length() - 1) == '=') {
            bytes = new byte[(((data.length() / 4) - 1) * 3) + 2];
        } else {
            bytes = new byte[((data.length() / 4) * 3)];
        }
        for (int i = 0, j = 0; i < (data.length() - 4); i += 4, j += 3) {
            b1 = decodingTable[data.charAt(i)];
            b2 = decodingTable[data.charAt(i + 1)];
            b3 = decodingTable[data.charAt(i + 2)];
            b4 = decodingTable[data.charAt(i + 3)];
            bytes[j] = (byte) ((b1 << 2) | (b2 >> 4));
            bytes[j + 1] = (byte) ((b2 << 4) | (b3 >> 2));
            bytes[j + 2] = (byte) ((b3 << 6) | b4);
        }
        if (data.charAt(data.length() - 2) == '=') {
            b1 = decodingTable[data.charAt(data.length() - 4)];
            b2 = decodingTable[data.charAt(data.length() - 3)];
            bytes[bytes.length - 1] = (byte) ((b1 << 2) | (b2 >> 4));
        } else if (data.charAt(data.length() - 1) == '=') {
            b1 = decodingTable[data.charAt(data.length() - 4)];
            b2 = decodingTable[data.charAt(data.length() - 3)];
            b3 = decodingTable[data.charAt(data.length() - 2)];
            bytes[bytes.length - 2] = (byte) ((b1 << 2) | (b2 >> 4));
            bytes[bytes.length - 1] = (byte) ((b2 << 4) | (b3 >> 2));
        } else {
            b1 = decodingTable[data.charAt(data.length() - 4)];
            b2 = decodingTable[data.charAt(data.length() - 3)];
            b3 = decodingTable[data.charAt(data.length() - 2)];
            b4 = decodingTable[data.charAt(data.length() - 1)];
            bytes[bytes.length - 3] = (byte) ((b1 << 2) | (b2 >> 4));
            bytes[bytes.length - 2] = (byte) ((b2 << 4) | (b3 >> 2));
            bytes[bytes.length - 1] = (byte) ((b3 << 6) | b4);
        }
        return bytes;
    }
    private static byte[] discardNonBase64Bytes(byte[] data) {
        byte[] temp = new byte[data.length];
        int bytesCopied = 0;
        for (int i = 0; i < data.length; i++) {
            if (isValidBase64Byte(data[i])) {
                temp[bytesCopied++] = data[i];
            }
        }
        byte[] newData = new byte[bytesCopied];
        System.arraycopy(temp, 0, newData, 0, bytesCopied);
        return newData;
    }
    private static String discardNonBase64Chars(String data) {
        StringBuffer sb = new StringBuffer();
        int length = data.length();
        for (int i = 0; i < length; i++) {
            if (isValidBase64Byte((byte) (data.charAt(i)))) {
                sb.append(data.charAt(i));
            }
        }
        return sb.toString();
    }
    private static boolean isValidBase64Byte(byte b) {
        if (b == '=') {
            return true;
        } else if ((b < 0) || (b >= 128)) {
            return false;
        } else if (decodingTable[b] == -1) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param httpSession
     * @param request
     * @param actionContext
     * @param from					分享出去后 微信会自己拼接 from 和 isappinstalled , 但是我们本地的地址是没有的
     * @param isappinstalled
     * @throws Exception
     */
    public static void getToken(HttpSession httpSession, HttpServletRequest request, ActionContext actionContext, String from, String isappinstalled) throws Exception {
    	String access_token = null;
		if (null == httpSession.getAttribute("access_token")) {
			// 第一步获取token存入全局缓存，
			String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Property.getProperty("WEIXIN_APPID") + "&secret=" + Property.getProperty("WEIXIN_APP_SECRET"));
			org.json.JSONObject obj1 = new JSONObject(result1);
			access_token = obj1.get("access_token").toString();
			httpSession.setAttribute("access_token", access_token);
		} else {
			access_token = httpSession.getAttribute("access_token").toString();
		}

		String jsapi_ticket = null;
		if (null == httpSession.getAttribute("jsapi_ticket")) {
			// 第二步根据token得到jsapi_ticket存入全局缓存
			String result2 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi");
			JSONObject obj2 = new JSONObject(result2);
			jsapi_ticket = obj2.get("ticket").toString();
			httpSession.setAttribute("jsapi_ticket", jsapi_ticket);
		} else {
			jsapi_ticket = httpSession.getAttribute("jsapi_ticket").toString();
		}

		// 获取请求的地址
		StringBuffer url = request.getRequestURL();
		String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
		String httpUrl = contextUrl + request.getRequestURI();
		if (from != null && isappinstalled != null) {
			httpUrl = httpUrl + "?from=" + from + "&isappinstalled=" + isappinstalled;
		}
		// 签名算法
		Map<String, String> map = Sign.sign(jsapi_ticket, httpUrl);
		actionContext.put("appId", Property.getProperty("WEIXIN_APPID"));
		actionContext.put("timestamp", map.get("timestamp"));
		actionContext.put("nonceStr", map.get("nonceStr"));
		actionContext.put("signature", map.get("signature"));
		actionContext.put("newDate", new Date().getTime());
    }
    
    public static String getOnlyToken(HttpSession httpSession) throws Exception {
    	String access_token = null;
		if (null == httpSession.getAttribute("access_token")) {
			// 第一步获取token存入全局缓存，
			String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Property.getProperty("WEIXIN_APPID") + "&secret=" + Property.getProperty("WEIXIN_APP_SECRET"));
			org.json.JSONObject obj1 = new JSONObject(result1);
			access_token = obj1.get("access_token").toString();
			httpSession.setAttribute("access_token", access_token);
		} else {
			access_token = httpSession.getAttribute("access_token").toString();
		}
		
		return access_token;
    }
    
    /**
     * 从微信上获取图片并且上传到OSS服务器上
     * @param httpSession
     * @param serverId
     * @return
     * @throws Exception
     */
    public static String downloadPicAndUploadOSS(HttpSession httpSession, String serverId) throws Exception {
    	String token = getOnlyToken(httpSession);
		String downloadUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+token+"&media_id="+serverId;
		byte[] buffer = null;  
    	BufferedInputStream in = new BufferedInputStream(new URL(downloadUrl).openStream());
    	String url = WeiXinUtil.class.getResource("/").getPath();
    	String filePath = url+"cc.jpg";
    	File file =  new File(filePath);
    	if (!file.getParentFile().exists()) {
    		file.getParentFile().mkdirs();
    	}
    	BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
    	buffer = new byte[2048];
    	int length = in.read(buffer);
    	while (length != -1) {
    		out.write(buffer, 0, length);
    		length = in.read(buffer);
    	}
    	in.close();
    	out.close();
    	byte[] imageToArray = imageToArray(filePath);
		String stockImage = OSSUploadUtil.imageFileUpload(imageToArray, "cc.jpg", "_stock");
		logger.info("stock_image_url====>" + stockImage);
		return stockImage;
    }
    
    public static byte[] imageToArray(String filePath) throws FileNotFoundException, IOException {
    	byte[] data = null;
        FileImageInputStream input = null;
        try {
          input = new FileImageInputStream(new File(filePath));
          ByteArrayOutputStream output = new ByteArrayOutputStream();
          byte[] buf = new byte[1024];
          int numBytesRead = 0;
          while ((numBytesRead = input.read(buf)) != -1) {
          output.write(buf, 0, numBytesRead);
          }
          data = output.toByteArray();
          output.close();
          input.close();
        }
        catch (FileNotFoundException ex1) {
          ex1.printStackTrace();
        }
        catch (IOException ex1) {
          ex1.printStackTrace();
        }
        return data;
    }
    public static void main(String[] args) {
        String data = "中华人民共和国";
        byte[] result = WeiXinUtil.encode(data.getBytes());
        System.out.println(data);
        System.out.println(new String(result));
        System.out.println(new String(WeiXinUtil.decode(new String(result))));
    }
}
