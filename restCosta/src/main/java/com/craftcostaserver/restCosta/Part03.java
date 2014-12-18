package com.craftcostaserver.restCosta;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class Part03 extends ServerResource{
	public void arrancar() throws Exception{
	    // Create a new Restlet component and add a HTTP server connector to it
		Server s = new Server(Protocol.HTTP, 8183);
		s.start();
	    Component component = new Component();
	    component.getServers().add(s);

	    // Then attach it to the local host
	    component.getDefaultHost().attach("/trace", Part03.class);

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
