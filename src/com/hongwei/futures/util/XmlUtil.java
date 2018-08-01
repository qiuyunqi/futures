package com.hongwei.futures.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @description 读取xml配置文件信息
 * @author 邱云奇
 */
public class XmlUtil {

	/**
	 * WebRoot目录的绝对路径
	 * 
	 * @return
	 */
	public static String getWebRootAbsolutePath() {
		String path = null;
		String folderPath = XmlUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (folderPath.indexOf("WEB-INF") > 0) {
			path = folderPath.substring(0, folderPath.indexOf("WEB-INF/classes"));
		}
		return path;
	}

	/**
	 * 获取Document对象
	 * 
	 * @return
	 */
	public static Document getDocument(String xmlPath) {
		Document document = null;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			FileInputStream inputStream = new FileInputStream(new File(xmlPath));
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			document = builder.parse(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * 根据xml路径返回xml文件集合
	 * 
	 * @param xmlPath
	 * @return
	 */
	public static NodeList getNodeList(String xmlPath, String tagName) {
		// 首先我们需要通过DocumentBuilderFactory获取xml文件的工厂实例。
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		NodeList sonlist = null;
		try {
			// 创建文档对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlPath); // 使用dom解析xml文件
			sonlist = doc.getElementsByTagName(tagName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sonlist;
	}

	/**
	 * 获取目标节点，进行修改，完成后，保存文件。
	 * 
	 * @param express
	 * @param source
	 * @return
	 */
	public static void updateNode(Document document, String xmlPath, String parentTag, String attribute, String attrValue, String sonTag, String sonValue) {
		Element root = document.getDocumentElement();
		NodeList nodeList = root.getElementsByTagName(parentTag);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			if (element.getAttribute(attribute).equals(attrValue)) {
				NodeList sonList = element.getChildNodes();

				for (int j = 0; j < sonList.getLength(); j++) {
					if (sonList.item(j) instanceof Node) {
						Node sonNode = sonList.item(j);
						if (sonNode.getNodeName().equals(sonTag)) {
							sonNode.setTextContent(sonValue);
						}
					}
				}
			}
		}
		saveXml(document, xmlPath);
	}

	/**
	 * 根据目标节点属性值删除节点，完成后，保存文件。
	 * 
	 * @param express
	 * @param xmlPath
	 */
	public static void delNode(Document document, String xmlPath, String tagName, String attribute, String attrValue) {
		Element root = document.getDocumentElement();
		NodeList nodeList = root.getElementsByTagName(tagName);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getAttributes().getNamedItem(attribute).getNodeValue().equals(attrValue)) {
				node.getParentNode().removeChild(node);
			}
		}
		saveXml(document, xmlPath);
	}

	/**
	 * 保存xml文件
	 */
	public static void saveXml(Document document, String xmlPath) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			FileOutputStream outputStream = new FileOutputStream(new File(xmlPath));
			StreamResult streamResult = new StreamResult(outputStream);
			transformer.transform(domSource, streamResult);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
