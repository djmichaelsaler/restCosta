package com.craftcostaserver.restCosta.player;

import java.util.HashSet;
import java.util.Set;

import org.restlet.data.Method;
import org.restlet.resource.Options;
import org.restlet.resource.ServerResource;

public class Userssv extends ServerResource{
	
	@Options
	public void describe(){
		Set<Method> meths = new HashSet<Method>();
		meths.add(Method.GET);
		meths.add(Method.HEAD);
		meths.add(Method.OPTIONS);
		
		this.getResponse().setAllowedMethods(meths);
	}

}
