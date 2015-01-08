package com.craftcostaserver.restCosta;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.craftcostaserver.restCosta.player.Economysv;
import com.craftcostaserver.restCosta.player.Petsv;
import com.craftcostaserver.restCosta.player.Residencesv;
import com.craftcostaserver.restCosta.player.Userssv;
import com.craftcostaserver.restCosta.player.Usersv;
import com.craftcostaserver.restCosta.server.Serversv;
import com.craftcostaserver.restCosta.world.Worldssv;
import com.craftcostaserver.restCosta.world.Worldsv;

public class RESTCostaApplication extends Application{
	
	public restCosta plugin;
	
	public RESTCostaApplication(restCosta plugin){
		this.plugin=plugin;
	}
	
	@Override
	public Restlet createInboundRoot(){
		Router router = new Router(getContext());
		//router.attach("/trace",Part03.class);
		//router.attach("/",Infosv.class);
		router.attach("/server",Serversv.class);
		router.attach("/server/users",Userssv.class);
		router.attach("/server/user/{user}",Usersv.class);
		router.attach("/server/user/{user}/economy",Economysv.class);
		router.attach("/server/user/{user}/pet",Petsv.class);
		//router.attach("/server/user/{user}/residence",Residencesv.class);
		router.attach("/server/worlds",Worldssv.class);
		router.attach("/server/world/{world}",Worldsv.class);
		return router;
	}
	
	public restCosta getPlugin(){
		return this.plugin;
	}

}
