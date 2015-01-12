package com.craftcostaserver.restCosta.player;

import java.text.DecimalFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.bukkit.OfflinePlayer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.craftcostaserver.restCosta.negocio.PlayerCosta;

public class UsersvDecorator {

	public static String getRESTURI(String user) {
		return "/server/user/" + user;
	}

	public static JSONObject getAsJSON(PlayerCosta p) {
		JSONObject pJSON = new JSONObject();
		JSONObject player = new JSONObject();
		JSONArray pArray = new JSONArray();
		try {
			pJSON.put("Name", p.getName());
			pJSON.put("Pocket", p.getEconomyCosta().getPocketMoney());
			pJSON.put("Bank", p.getEconomyCosta().getBankMoney());
			pJSON.put("link", getRESTURI(p.getName()));
			pJSON.put("parentLink", UserssvDecorator.getRESTURI());
			pArray.put(pJSON);
			player.put("User", pArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return player;
	}

	public static DOMSource getAsXMLDocument(PlayerCosta p) {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		DOMSource source;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
						
			doc = getAsXMLElement(doc, p);
			
			source = new DOMSource(doc);
			return source;
		} catch (ParserConfigurationException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return source=null;
		}
	}

	public static Document getAsXMLElement(Document doc,PlayerCosta p) {
		DecimalFormat df = new DecimalFormat("#.##");
		
		Element rootElement = doc.createElement("Player");
		doc.appendChild(rootElement);
		 
		// staff elements
		
		Element firstname = doc.createElement("Name");
		firstname.appendChild(doc.createTextNode(p.getName()));
		rootElement.appendChild(firstname);
		
		Element pocket = doc.createElement("Pocket");
		pocket.appendChild(doc.createTextNode(df.format(p.getEconomyCosta().getPocketMoney())));
		rootElement.appendChild(pocket);
		
		Element bank = doc.createElement("Bank");
		bank.appendChild(doc.createTextNode(df.format(p.getEconomyCosta().getBankMoney())));
		rootElement.appendChild(bank);

		Element link = doc.createElement("Link");
		link.appendChild(doc.createTextNode(getRESTURI(p.getName())));
		rootElement.appendChild(link);
		
		Element parent_link = doc.createElement("Parent_Link");
		parent_link.appendChild(doc.createTextNode(UserssvDecorator.getRESTURI()));
		rootElement.appendChild(parent_link);

		return doc;
	}

}
