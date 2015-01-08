package com.craftcostaserver.restCosta.player;

import org.bukkit.OfflinePlayer;
import org.json.JSONException;
import org.json.JSONObject;

import com.craftcostaserver.restCosta.negocio.PlayerCosta;

public class UsersvDecorator {

	public static String getRESTURI(String user) {
		return "/server/user/" + user;
	}

	public static JSONObject getAsJSON(PlayerCosta p) {
		JSONObject pJSON = new JSONObject();
		try {
			pJSON.put("Name", p.getName());
			pJSON.put("Pocket", p.getEconomyCosta().getPocketMoney());
			pJSON.put("Bank", p.getEconomyCosta().getBankMoney());
			pJSON.put("link", getRESTURI(p.getName()));
			pJSON.put("parentLink", UserssvDecorator.getRESTURI());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return pJSON;
	}

	public static String getAsXMLDocument(PlayerCosta p) {
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\"?>");
		xml.append(getAsXMLElement(p));
		return xml.toString();
	}

	public static String getAsXMLElement(PlayerCosta p) {
		StringBuilder xml = new StringBuilder();
		xml.append("<user>");
		xml.append("<Name>" + p.getName() + "</name>");
		xml.append("<Pocket>" + p.getEconomyCosta().getPocketMoney()
				+ "</Pocket>");
		xml.append("<Bank>" + p.getEconomyCosta().getBankMoney() + "</Bank>");
		xml.append("<link>" + getRESTURI(p.getName()) + "</link>");
		xml.append("<parentLink>" + UserssvDecorator.getRESTURI()
				+ "</parentLink>");
		xml.append("</user>");
		return xml.toString();
	}

}
