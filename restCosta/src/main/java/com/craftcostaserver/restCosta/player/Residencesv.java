package com.craftcostaserver.restCosta.player;

import java.util.HashSet;
import java.util.Set;

import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.ServerResource;

public class Residencesv extends ServerResource{
	
	@Get
	public Representation getResidenceDef(){
		
		String user = (String) getRequest().getAttributes().get("Player");

		//Hacer Transformacion a JSON de residences AQUI!!!!!!
		
		Representation representation = new StringRepresentation(json,MediaType.APPLICATION_JSON);
		return representation;
		
	}
	
	@Get("JSON")
	public Representation getResidenceEnJSON(){
		
		String user = (String) getRequest().getAttributes().get("Player");

		//Hacer Transformacion a JSON de residences AQUI!!!!!!
		
		Representation representation = new StringRepresentation(json,MediaType.APPLICATION_JSON);
		return representation;
		
	}
	
	@Get("XML")
	public Representation getResidenceEnXML(){
		
		String user = (String) getRequest().getAttributes().get("Player");

		//Hacer Transformacion a JSON de residences AQUI!!!!!!
		
		Representation representation = new StringRepresentation(json,MediaType.APPLICATION_JSON);
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
