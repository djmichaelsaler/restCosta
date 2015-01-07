package com.craftcostaserver.restCosta;

import org.bukkit.plugin.java.JavaPlugin;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

import com.craftcostaserver.restCosta.player.Economysv;
import com.craftcostaserver.restCosta.player.Petsv;
import com.craftcostaserver.restCosta.player.Residencesv;
import com.craftcostaserver.restCosta.player.Userssv;
import com.craftcostaserver.restCosta.player.Usersv;
import com.craftcostaserver.restCosta.server.Serversv;
import com.craftcostaserver.restCosta.world.Worldssv;
import com.craftcostaserver.restCosta.world.Worldsv;

public class restCosta extends JavaPlugin{
	
	
	
	@Override
	public void onEnable() {
		
		
		try {
		    // Create a new Restlet component and add a HTTP server connector to it
			Server s = new Server(Protocol.HTTP, 8182);
			s.start();
		    Component component = new Component();
		    component.getServers().add(s);

		    // Then attach it to the local host
		    //component.getDefaultHost().attach("/trace", Part03.class);
		    
		    Application app = new RESTCostaApplication();
			
			component.getDefaultHost().attach(app);

		    // Now, let's start the component!
		    // Note that the HTTP server connector is also automatically started.
		    component.start();
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {

	}
}
