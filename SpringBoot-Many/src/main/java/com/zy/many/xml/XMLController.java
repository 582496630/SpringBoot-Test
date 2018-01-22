package com.zy.many.xml;

import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/xml")
@Controller
public class XMLController {
	
	@Autowired
	private JDomDemo jDomDemoServices;
	
	@RequestMapping(value = "/xmlOut")
	public String xmlOut(Model model){
		String path = TestXml.class.getResource("/templates/XML/my.xml").getPath();
		List<String> list = jDomDemoServices.parserXml(path);
		model.addAttribute("list", list);
		
		return "XML/XMLout";
	}
	@RequestMapping(value = "/gameProtocol")
	public String gameProtocol(Model model){
		String path = TestXml.class.getResource("/templates/XML/game.xml").getPath();
		List<GameProtocolXMLEntity> list;
		try {
			list = jDomDemoServices.parserXml2(path);
			model.addAttribute("list", list);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return "XML/XMLout";
	}
	

}
