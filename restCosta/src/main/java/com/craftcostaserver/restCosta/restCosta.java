package com.craftcostaserver.restCosta;

import org.bukkit.plugin.java.JavaPlugin;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import de.hotmail.gurkilein.bankcraft.Bankcraft;

public class restCosta extends JavaPlugin{
	
	Server s;
	@Override
	public void onEnable() {
	
		try {
		    // Create a new Restlet component and add a HTTP server connector to it
			s = new Server(Protocol.HTTP, 8182);
			s.start();
		    Component component = new Component();
		    component.getServers().add(s);

		    // Then attach it to the local host
		    //component.getDefaultHost().attach("/trace", Part03.class);
		    
		    Application app = new RESTCostaApplication(this);
			
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
		try {
			s.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Bankcraft getBankcraft(){
		return (Bankcraft)getServer().getPluginManager().getPlugin("Bankcraft");
	}
}
