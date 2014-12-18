package com.craftcostaserver.restCosta;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.util.ServerList;

public class Part05 extends ServerResource{
	public Part05() throws Exception{
	    // Create a new Restlet component and add a HTTP server connector to it
		//new Server(Protocol.HTTP, 8182, Part05.class).start();
		Server s = new Server(Protocol.HTTP, 8182);
	    Component component = new Component();
	    component.getServers().);

	    // Then attach it to the local host
	    component.getDefaultHost().attach("/trace", Part05.class);

	    // Now, let's start the component!
	    // Note that the HTTP server connector is also automatically started.
	    component.start();
	    
	    
	}

	@Get
	public String toString() {
	    // Print the requested URI path
	    return "Resource URI  : " + getReference() + '\n' + "Root URI      : "
	            + getRootRef() + '\n' + "Routed part   : "
	            + getReference().getBaseRef() + '\n' + "Remaining part: "
	            + getReference().getRemainingPart();
	}
}
