package com.craftcostaserver.restCosta.player;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.ServerResource;

import com.craftcostaserver.restCosta.RESTCostaApplication;
import com.craftcostaserver.restCosta.restCosta;
import com.earth2me.essentials.api.Economy;

public class Economysv extends ServerResource{

	 protected restCosta getPlugin()
	 {
	   return ((RESTCostaApplication)getApplication()).getPlugin();
	 }
	
	@Get("json")
	public Representation getEntradaLlistaJSON() {
		EntradaLlista ell = null;
		try {
			ell = getEntradaLlista();
		} catch (ToDoListNoExisteixEntradaLlista e) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Entrada Llista no trobada!",
					MediaType.TEXT_PLAIN);
		} catch (ToDoListNoExisteixLlista e) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Llista no trobada!",
					MediaType.TEXT_PLAIN);
		}
		JSONObject json = EntradaLlistaRESTDecorator.getAsJSON(ell);

		Representation representation = new StringRepresentation(
				json.toString(), MediaType.APPLICATION_JSON);
		return representation;
	}
	
	@Get("xml")
	public Representation getEntradaLlistaXML() {
		EntradaLlista ell = null;
		try {
			ell = getEntradaLlista();
		} catch (ToDoListNoExisteixEntradaLlista e) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Entrada Llista no trobada!",
					MediaType.TEXT_PLAIN);
		} catch (ToDoListNoExisteixLlista e) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return new StringRepresentation("Llista no trobada!",
					MediaType.TEXT_PLAIN);
		}
		Representation representation = new StringRepresentation(
				EntradaLlistaRESTDecorator.getAsXMLDocument(ell),
				MediaType.APPLICATION_XML);
		return representation;
	}
	
	@Options
	public void describe(){
		Set<Method> meths = new HashSet<Method>();
		meths.add(Method.GET);
		meths.add(Method.HEAD);
		meths.add(Method.OPTIONS);
		
		this.getResponse().setAllowedMethods(meths);
	}

}
