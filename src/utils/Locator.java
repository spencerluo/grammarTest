package utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Locator {

	public static Document dom;
	
	public static void init() throws ParserConfigurationException, Exception, IOException{
		DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		dom=db.parse(new File("document\\element.xml"));
	}

	public static By getLocator(String pageName, String objectName) throws Exception {
		NodeList pages;
		try {
			pages = dom.getElementsByTagName(pageName);
		} catch (Exception e) {
			Log.error("no such page "+pageName);
			throw(e);
		}
		Node page = pages.item(0);
		NodeList objects = page.getChildNodes();
		Node object = null;
		for (int i = 0; i < objects.getLength(); i++) {
			if (objects.item(i).getNodeName().equals(objectName)){
				object = objects.item(i);
				break;
			}
		}
		String type = null;
		String value = null;
		try {
			type = object.getAttributes().getNamedItem("type").getNodeValue();
			value = object.getAttributes().getNamedItem("value").getNodeValue();
		} catch (Exception e) {
			Log.error("no such object"+objectName);
			throw(e);
		}
		By by = null;
		switch (type) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "className":
			by = By.className(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		}
		return by;
}
}
