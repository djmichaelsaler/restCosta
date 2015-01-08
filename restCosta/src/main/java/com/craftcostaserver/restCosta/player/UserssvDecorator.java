package com.craftcostaserver.restCosta.player;

import org.bukkit.OfflinePlayer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return pJSON;
	}

	public static String getAsXMLDocument(OfflinePlayer[] offplayers) {
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\"?>");
		xml.append(getAsXMLElement(offplayers));
		return xml.toString();
	}

	public static String getAsXMLElement(OfflinePlayer[] offplayers) {
		StringBuilder xml = new StringBuilder();
		xml.append("<users>");
		StringBuilder xmlSub = new StringBuilder();
		for (int i = 0; i < offplayers.length; i++) {
			xmlSub.append(UsersvDecorator.getAsXMLElement(new Usersv().getPlayerCosta(offplayers[i].getName())));
		}
		xml.append(xmlSub);
		xml.append("</users>");
		return xml.toString();
	}
}
