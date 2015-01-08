package com.craftcostaserver.restCosta.player;

public class EconomysvDecorator {
	public static String getRESTURI(String user) {
		return "/server/user/" + user + "/" + "economy";
	}

	public static JSONObject getAsJSON(EntradaLlista ell) {
		JSONObject ellJSON = new JSONObject();
		try {
			ellJSON.put("Pocket", ell.getText());
			ellJSON.put("Bank", ell.getText());
			ellJSON.put("link", getRESTURI(ell.getLlista()));
			ellJSON.put("parentLink",LlistaRESTDecorator.getRESTURI(ell.getLlista()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ellJSON;
	}

	public static String getAsJSONText(EntradaLlista ell) {
		return getAsJSON(ell).toString();
	}

	public static String getAsXMLDocument(EntradaLlista ell) {
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\"?>");
		xml.append(getAsXMLElement(ell));
		return xml.toString();
	}

	public static String getAsXMLElement(EntradaLlista ell) {
		StringBuilder xml = new StringBuilder();
		xml.append("<entrada>");
		xml.append("<id>" + ell.getID() + "</id>");
		xml.append("<text>" + ell.getText() + "</text>");
		xml.append("<link>" + getRESTURI(ell.getLlista(), ell.getID())
				+ "</link>");
		xml.append("<parentLink>"
				+ LlistaRESTDecorator.getRESTURI(ell.getLlista())
				+ "</parentLink>");
		xml.append("</entrada>");
		return xml.toString();
	}
}
