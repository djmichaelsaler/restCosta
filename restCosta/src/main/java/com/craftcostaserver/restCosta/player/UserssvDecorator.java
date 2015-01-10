package com.craftcostaserver.restCosta.player;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.bukkit.OfflinePlayer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.craftcostaserver.restCosta.server.ServersvDecorator;

public class UserssvDecorator {

	public static String getRESTURI() {
		return "/server/users";
	}

	public static JSONObject getAsJSON(OfflinePlayer[] offplayers) {
		JSONObject pJSON = new JSONObject();
		try {
			pJSON.put("link", getRESTURI());
			pJSON.put("parentLink", ServersvDecorator.getRESTURI());
			JSONArray pArray = new JSONArray();
			for (int i=0;i<offplayers.length;i++){
				pArray.put(UsersvDecorator.getAsJSON(new Usersv().getPlayerCosta(offplayers[i].getName())));
				System.out.println(offplayers[i].getName());
			}
			pJSON.put("Users",pArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return pJSON;
	}

	public static DOMSource getAsXMLDocument(OfflinePlayer[] offplayers) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		DOMSource source;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Players");
			doc.appendChild(rootElement);
			
			doc = getAsXMLElement(doc, offplayers);
			
			source = new DOMSource(doc);
			return source;
		} catch (ParserConfigurationException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return source=null;
		}
	}

	public static Document getAsXMLElement(Document doc,OfflinePlayer[] offplayers) {		 
		// staff elements
		
		for (int i = 0; i < offplayers.length; i++) {
			doc = UsersvDecorator.getAsXMLElement(doc.getOwnerDocument(), new Usersv().getPlayerCosta(offplayers[i].getName()));
		}
		return doc;
	}

}
