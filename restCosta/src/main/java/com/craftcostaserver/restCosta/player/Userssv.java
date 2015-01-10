package com.craftcostaserver.restCosta.player;

import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bukkit.OfflinePlayer;
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

public class Userssv extends ServerResource{
	
	protected restCosta getPlugin() {
		return ((RESTCostaApplication) getApplication()).getPlugin();
	}

	protected OfflinePlayer[] getUsers() {
		return getPlugin().getServer().getOfflinePlayers();
	}

	/*
	protected PlayerCosta getPlayerCosta() throws NoExisteUsuarioException {
		String user = getPlugin().getServer().getOfflinePlayer(getUser())
				.getName();
		if (user == null) {
			throw new NoExisteUsuarioException();
		}
		double pocket = Economy.getMoney(getUser());
		double bank = ((Bankcraft) getPlugin().getServer().getPluginManager()
				.getPlugin("Bankcraft")).getMoneyDatabaseInterface()
				.getBalance(getUser());

		return new PlayerCosta(user, pocket, bank);
	}
	*/

	@Get("json")
	public Representation getUsersJSON() {
		OfflinePlayer[] offplayers = getUsers();
		if (offplayers.length == 0) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("No hay usuarios en el server!",MediaType.TEXT_PLAIN);
		}

		JSONObject json = UserssvDecorator.getAsJSON(offplayers);

		Representation representation = new StringRepresentation(json.toString(), MediaType.APPLICATION_JSON);
		return representation;

	}

	/*
	@Get("xml")
	public Representation getEntradaLlistaXML(){
		OfflinePlayer[] offplayers = getUsers();
		if (offplayers.length == 0) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("No hay usuarios en el server!",MediaType.TEXT_PLAIN);
		}
		
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			DOMSource dom = UserssvDecorator.getAsXMLDocument(offplayers);
			
			StringWriter xml = new StringWriter();
			StreamResult result=new StreamResult(xml);
			
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(dom, result);

			Representation representation = new StringRepresentation(xml.toString(), MediaType.APPLICATION_XML);
			return representation;
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

	@Options
	public void describe() {
		Set<Method> meths = new HashSet<Method>();
		meths.add(Method.GET);
		meths.add(Method.HEAD);
		meths.add(Method.OPTIONS);

		this.getResponse().setAllowedMethods(meths);
	}

}
