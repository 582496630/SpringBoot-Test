package com.zy.many.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.stereotype.Service;

/**
 * 
 * @author hongliang.dinghl JDOM 生成与解析XML文档
 * 
 */
@Service
public class JDomDemo implements XmlDocument {
	/**
	 * 创建XML文档元素
	 */
	public void createXml(String fileName) {
		// 创建文档
		Document document = new Document();
		// 创建根元素
		Element people = new Element("people");
		// 把根元素加入到document中
		document.addContent(people);

		// 创建注释
		Comment rootComment = new Comment("将数据从程序输出到XML中！");
		people.addContent(rootComment);

		// 创建父元素
		Element person1 = new Element("person");
		// 把元素加入到根元素中
		people.addContent(person1);
		// 设置person1元素属性
		person1.setAttribute("id", "001");

		Attribute person1_gender = new Attribute("gender", "male");
		person1.setAttribute(person1_gender);

		Element person1_name = new Element("name");
		person1_name.setText("刘德华");
		person1.addContent(person1_name);

		Element person1_address = new Element("address");
		person1_address.setText("香港");
		person1.addContent(person1_address);

		Element person2 = new Element("person");
		people.addContent(person2);

		person2.setAttribute("id", "002").setAttribute("gender", "male");// 添加属性，可以一次添加多个属性

		Element person2_name = new Element("name");
		person2_name.setText("林志颖");
		person2.addContent(person2_name);

		Element person2_address = new Element("address");
		person2_address.setText("台湾");
		person2.addContent(person2_address);

		// 设置xml输出格式
		Format format = Format.getPrettyFormat();
		format.setEncoding("utf-8");// 设置编码
		format.setIndent("    ");// 设置缩进
		// 得到xml输出流
		XMLOutputter out = new XMLOutputter(format);
		// 把数据输出到xml中
		try {
			out.output(document, new FileOutputStream(fileName));// 或者FileWriter
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 追加xml文档元素
	 * 
	 */
	public void appendXML(String fileName) {
		SAXBuilder saxBuilder = new SAXBuilder();

		InputStream in;
		try {
			// 2.创建一个输入流，将xml文件加载到输入流
			in = new FileInputStream(fileName);
			// 3.通过SAXBuilder的build方法将输入流加载到SAXBuilder中
			Document document = saxBuilder.build(in);
			// 4.通过Document对象获取xml文件的根节点
			Element rootElement = document.getRootElement();
			// 5.根据根节点获取子节点的List集合
			List<Element> bookList = rootElement.getChildren();
			// 6.追加节点
			Element person = new Element("person");
			rootElement.addContent(person);

			// 7.追加子节点
			Element person_name = new Element("name");
			person_name.setText("爱德华");
			person.addContent(person_name);

			Element person_address = new Element("address");
			person_address.setText("米国");
			person.addContent(person_address);
			Element language = new Element("language");
			language.setText("中文");
			person.addContent(language);
			// 7.设置输出流和输出格式
			Format format = Format.getCompactFormat();
			format.setEncoding("utf-8");// 设置编码方式为gb2312
			format.setIndent("  ");// 设置缩进（此处为一个tab键）
			XMLOutputter outputter = new XMLOutputter(format);
			outputter.output(document, new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改xml文档元素
	 */
	public void updateXML(String fileName) {
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			doc = sb.build(fileName);
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren("person");
			for (Element el : list) {
				if (el.getAttributeValue("id").equals("001")) {
					Element name = el.getChild("name");
					name.setText("xingoo---update");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		saveXML(doc, fileName);
	}

	/**
	 * 删除XML文档元素
	 * 
	 */
	public void removeXML(String fileName) {
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			doc = sb.build(fileName);
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren("person");
			// 直接遍历list会报错（ConcurrentModificationException）；因为移除root的子元素，list的长度就变了，就无法继续遍历
			List<Element> list2 = new ArrayList<Element>();
			list2.addAll(list);
			for (Element el : list2) {
				if (el.getAttributeValue("id").equals("001")) {
					root.removeContent(el);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		saveXML(doc, fileName);
	}

	/**
	 * 写入内容到xml中
	 */
	public static void saveXML(Document doc, String fileName) {
		// 将doc对象输出到文件
		try {
			// 创建xml文件输出流
			XMLOutputter xmlopt = new XMLOutputter();

			// 创建文件输出流
			FileWriter writer = new FileWriter(fileName);

			// 指定文档格式
			Format fm = Format.getPrettyFormat();
			// fm.setEncoding("GB2312");
			xmlopt.setFormat(fm);
			// 将doc写入到指定的文件中
			xmlopt.output(doc, writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 解析XML方法一
	public List<String> parserXml(String fileName) {
		SAXBuilder builder = new SAXBuilder(false);
		List<String> list = new ArrayList<String>();
		try {
			Document document = builder.build(fileName);
			Element protocals = document.getRootElement();
			@SuppressWarnings("rawtypes") // 压制警告
			List titleList = protocals.getChildren("title");
			for (int i = 0; i < titleList.size(); i++) {
				Element employee = (Element) titleList.get(i);
				@SuppressWarnings("rawtypes")
				List titleHeadList = employee.getChildren();
				for (int j = 0; j < titleHeadList.size(); j++) {
					System.out.println(((Element) titleHeadList.get(j)).getName() + ":"
							+ ((Element) titleHeadList.get(j)).getValue());
					String string = ((Element) titleHeadList.get(j)).getValue();
					list.add(string);
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<GameProtocolXMLEntity> parserXml2(String fileName) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder(false);
		List<GameProtocolXMLEntity> list = new ArrayList<GameProtocolXMLEntity>();
		Document document = builder.build(fileName);
		Element protocals = document.getRootElement();
		List<Element> titlelList = protocals.getChildren();
		for (Element title : titlelList) {
			GameProtocolXMLEntity game = new GameProtocolXMLEntity();
			List<Element> titleHeadlList = title.getChildren("title_head");
			List<Element> titleBodylList = title.getChildren("title_body");
			for (Element titleHead : titleHeadlList) {
				game.setTitle_head(titleHead.getText());
				System.out.println(titleHead.getText());
			}
			List<String> listbody = new ArrayList<String>();
			List<String> listbodyHead = new ArrayList<String>();
			List<String> listbody1 = new ArrayList<String>();
			List<String> listbody2 = new ArrayList<String>();
			for (Element titleBody : titleBodylList) {
				game.setTitle_body(titleBody.getText());
				listbodyHead.add(titleBody.getChildText("body_head"));
				List<Element> bodylList = titleBody.getChildren("body");
				System.out.println(titleBody.getText());
				System.out.println(titleBody.getChildText("body_head"));
				for (Element body : bodylList) {
					listbody.add(body.getText());
					System.out.println(body.getText());

					List<Element> body1List = body.getChildren("body_1");
					for (Element body1 : body1List) {
						listbody1.add(body1.getText());
						System.out.println(body1.getText());
						List<Element> body2List = body1.getChildren("body_2");
						for (Element body2 : body2List) {
							listbody2.add(body2.getText());
							System.out.println(body2.getText());
						}
					}

				}
				game.setListbody_head(listbodyHead);
				game.setListbody(listbody);
				game.setListbody1(listbody1);
				game.setListbody2(listbody2);
			}
			list.add(game);
		}
		return list;
	}

}