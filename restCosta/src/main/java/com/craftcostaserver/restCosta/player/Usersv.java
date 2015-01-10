                         package com.craftcostaserver.restCosta.player;

import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.ServerResource;

import com.craftcostaserver.restCosta.RESTCostaApplication;
import com.craftcostaserver.restCosta.restCosta;
import com.craftcostaserver.restCosta.negocio.PlayerCosta;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;

import de.hotmail.gurkilein.bankcraft.Bankcraft;

public class Usersv extends ServerResource {

	protected restCosta getPlugin() {
		return ((RESTCostaApplication) getApplication()).getPlugin();
	}

	protected String getUser() {
		return (String) getRequest().getAttributes().get("user");
	}

	protected PlayerCosta getPlayerCosta() throws NoExisteUsuarioException {
		String user = getPlugin().getServer().getOfflinePlayer(getUser()).getName();
		if (user == null) {
			throw new NoExisteUsuarioException();
		}
		double pocket=0.0;
		try {
			pocket = Economy.getMoney(getUser());
		} catch (UserDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double bank = ((Bankcraft) getPlugin().getServer().getPluginManager().getPlugin("Bankcraft")).getMoneyDatabaseInterface().getBalance(getUser());

		return new PlayerCosta(user, pocket, bank);
	}
	
	protected PlayerCosta getPlayerCosta(String user){

		double pocket=0.0;
		try {
			pocket = Economy.getMoney(user);
		} catch (UserDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double bank = ((Bankcraft) getPlugin().getServer().getPluginManager().getPlugin("Bankcraft")).getMoneyDatabaseInterface().getBalance(user);

		return new PlayerCosta(user, pocket, bank);
	}

	/*
	@Get("xml")
	public Representation getEntradaLlistaXML() {
		String user = getPlugin().getServer().getOfflinePlayer(getUser()).getName();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		
		if (user == null) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Usuario no encontrado!",MediaType.TEXT_PLAIN);
		}
		double pocket;
		try {
			pocket = Economy.getMoney(getUser());
			double bank = ((Bankcraft) getPlugin().getServer().getPluginManager().getPlugin("Bankcraft")).getMoneyDatabaseInterface().getBalance(getUser());
			PlayerCosta p = new PlayerCosta(getUser(), pocket, bank);
			DOMSource dom = UsersvDecorator.getAsXMLDocument(p);
			
			StringWriter xml = new StringWriter();
			StreamResult result=new StreamResult(xml);
			
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(dom, result);

			Representation representation = new StringRepresentation(xml.toString(), MediaType.APPLICATION_XML);
			return representation;
		} catch (UserDoesNotExistException e) {
			// TODO Auto-generated catch block
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Usuario no encontrado en Essentials!",MediaType.TEXT_PLAIN);
			// e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Tranformacion configuration error!",MediaType.TEXT_PLAIN);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Transformacion Error!",MediaType.TEXT_PLAIN);
		}
	}
	*/

	@Get("json:json")
	public Representation getUserJSON() {
		
		
		String user = getPlugin().getServer().getOfflinePlayer(getUser()).getName();
		if (user == null) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Usuario no encontrado!",MediaType.TEXT_PLAIN);
		}
		double pocket;
		try {
			pocket = Economy.getMoney(getUser());
			double bank = ((Bankcraft) getPlugin().getServer().getPluginManager().getPlugin("Bankcraft")).getMoneyDatabaseInterface().getBalance(getUser());
			PlayerCosta p = new PlayerCosta(getUser(), pocket, bank);
			JSONObject json = UsersvDecorator.getAsJSON(p);

			Representation representation = new StringRepresentation(json.toString(), MediaType.APPLICATION_JSON);
			return representation;
		} catch (UserDoesNotExistException e) {
			// TODO Auto-generated catch block
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Usuario no encontrado en Essentials!",MediaType.TEXT_PLAIN);
			// e.printStackTrace();
		}
	}

	@Options
	public void describe() {
		Set<Method> meths = new HashSet<Method>();
		meths.add(Method.GET);
		meths.add(Method.HEAD);
		meths.add(Method.OPTIONS);

		this.getResponse().setAllowedMethods(meths);
	}

}
