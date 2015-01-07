package com.craftcostaserver.restCosta;

import java.util.HashSet;
import java.util.Set;

import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.ServerResource;

public class Infosv extends ServerResource{
	
	@Get
	public Representation getResidenceDef(){
		
		return new StringRepresentation(json,MediaType.APPLICATION_JSON);;
		
	}
	
	@Get("JSON")
	public Representation getResidenceEnJSON(){

		return new StringRepresentation(json,MediaType.APPLICATION_JSON);;
		
	}
	
	@Get("XML")
	public Representation getResidenceEnXML(){

		return new StringRepresentation(RESTCostaDecorator.getAsXMLDocument(this.getRESTCosta()),MediaType.APPLICATION_XML);;
		
	}
	
	private Object getRESTCosta() {
		// TODO Auto-generated method stub
		return null;
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
