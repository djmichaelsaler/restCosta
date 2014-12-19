package com.craftcostaserver.restCosta;


import java.util.HashSet;
import java.util.Set;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

public class Part03 extends ServerResource{
	public void arrancar() throws Exception{
	    // Create a new Restlet component and add a HTTP server connector to it
		Server s = new Server(Protocol.HTTP, 8182);
		s.start();
	    Component component = new Component();
	    component.getServers().add(s);

	    // Then attach it to the local host
	    //component.getDefaultHost().attach("/trace", Part03.class);
	    
	    Application app = new Application() {
	    	@Override
	    	public Restlet createInboundRoot(){
	    		Router router = new Router(getContext());
	    		router.attach("/trace",Part03.class);
	    		return router;
	    	}
	    	
		};
		
		component.getDefaultHost().attach(app);

	    // Now, let's start the component!
	    // Note that the HTTP server connector is also automatically started.
	    component.start();
	}

	@Get
	public String toJSON() {
	    // Print the requested URI path
		return "Resource URI  : " + getReference() + '\n' + "Root URI      : "
        + getRootRef() + '\n' + "Routed part   : "
        + getReference().getBaseRef() + '\n' + "Remaining part: "
        + getReference().getRemainingPart();
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
