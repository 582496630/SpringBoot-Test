package com.zy.many.xml;

import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;

/**
 * 
 * @author hongliang.dinghl 定义XML文档建立与解析的接口
 */
public interface XmlDocument {
	/**
	 * 建立XML文档
	 * 
	 * @param fileName
	 *            文件全路径名称
	 */
	public void createXml(String fileName);

	/**
	 * 解析XML文档
	 * 
	 * @param fileName
	 *            文件全路径名称
	 */
	public List<String> parserXml(String fileName);

	public List<GameProtocolXMLEntity> parserXml2(String fileName) throws JDOMException, IOException;
}
