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
import com.earth2me.essentials.api.Economy;

public class Economysv extends ServerResource{
	
	@Get
	public Representation getEconomyJSON (){
		
		JSONObject json = new JSONObject();
		json.put("Pocket", getMoney(getRequest().));

		return = new StringRepresentation(EconomysvDecorator.getAsJSONText(this.get)) 
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
