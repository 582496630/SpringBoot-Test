package com.zy.many.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.JDOMException;

public class TestXml {

	public static void main(String[] args) throws JDOMException, IOException {
		JDomDemo jDomDemo = new JDomDemo();
		String path = TestXml.class.getResource("/templates/XML/game.xml").getPath();
		String path2 = TestXml.class.getResource("/templates/XML/game.xml").getFile();
		//jDomDemo.createXml(path);
	/*	List<GameProtocolXMLEntity> list = jDomDemo.parserXml2(path);
		System.out.println("****************");
		for (GameProtocolXMLEntity game: list) {
			System.out.println(game.getTitle_head()+game.getTitle_body()+game.toListbody_head(game.getListbody_head())+
					game.tobodylist(game.getListbody())+game.tobody1list(game.getListbody1())+game.tobody2list(game.getListbody2()));
		}*/
		String fileName = "D://zy//java//zyworkspace//SpringBoot-Many//src//main//resources//templates//XML//my.xml";
		//jDomDemo.createXml(fileName);
		//jDomDemo.updateXML(fileName);
		//jDomDemo.removeXML(fileName);
		jDomDemo.appendXML(fileName);
		System.out.println("111");
	}
}
