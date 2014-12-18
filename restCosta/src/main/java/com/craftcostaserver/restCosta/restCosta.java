package com.craftcostaserver.restCosta;

import org.bukkit.plugin.java.JavaPlugin;

public class restCosta extends JavaPlugin{
	
	@Override
	public void onEnable() {
		try {
			new Part05();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {

	}
}
