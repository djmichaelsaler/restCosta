package com.craftcostaserver.restCosta.server;

import java.util.Collection;

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

import com.craftcostaserver.restCosta.player.UserssvDecorator;
import com.craftcostaserver.restCosta.player.Usersv;
import com.craftcostaserver.restCosta.player.UsersvDecorator;

public class ServersvDecorator {


	public static String getRESTURI() {
		return "/server";
	}

	public static JSONObject getAsJSON(String name_server,String bukkit_version, String iP) {
		JSONObject pJSON = new JSONObject();
		try {
			pJSON.put("Name Server", name_server);
			pJSON.put("Bukkit Version", bukkit_version);
			pJSON.put("IP", iP);
			pJSON.put("Players link", UserssvDecorator.getRESTURI());
			pJSON.put("link", getRESTURI());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return pJSON;
	}

	public static DOMSource getAsXMLDocument(String name_server,String bukkit_version, String iP) {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		DOMSource source;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			
			doc = getAsXMLElement(doc,name_server,bukkit_version,iP);
			
			source = new DOMSource(doc);
			return source;
		} catch (ParserConfigurationException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return source=null;
		}
	}

	private static Document getAsXMLElement(Document doc,String name_server,String bukkit_version, String iP) {
		StringBuilder xml = new StringBuilder();
		xml.append("<server>");
		xml.append("<Name_Server>" + name_server + "</Name_Server>");
		xml.append("<Bukkit_Version>" + bukkit_version + "</Bukkit_Version>");
		xml.append("<IP>" + iP + "</IP>");
		xml.append("<link>" + getRESTURI() + "</link>");
		xml.append("<link_players>" + UserssvDecorator.getRESTURI() + "</link_players>");
		xml.append("</server>");
		//return xml.toString();
		
		Element rootElement = doc.createElement("Server");
		doc.appendChild(rootElement);
		 
		// staff elements
		
		Element firstname = doc.createElement("Name_Server");
		firstname.appendChild(doc.createTextNode(name_server));
		rootElement.appendChild(firstname);
		
		Element pocket = doc.createElement("Bukkit_Version");
		pocket.appendChild(doc.createTextNode(bukkit_version));
		rootElement.appendChild(pocket);
		
		Element bank = doc.createElement("IP");
		bank.appendChild(doc.createTextNode(iP));
		rootElement.appendChild(bank);

		Element link = doc.createElement("Link");
		link.appendChild(doc.createTextNode(getRESTURI()));
		rootElement.appendChild(link);
		
		Element parent_link = doc.createElement("Link_Players");
		parent_link.appendChild(doc.createTextNode(UserssvDecorator.getRESTURI()));
		rootElement.appendChild(parent_link);

		return doc;
	}



}
