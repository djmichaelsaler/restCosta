package com.craftcostaserver.restCosta.negocio;

public class PlayerCosta {
	private String name;
	private EconomyCosta eco;
	
	public PlayerCosta (String name,double pocket,double bank){
		this.name = name;
		this.eco = new EconomyCosta(pocket, bank);
	}
	
	public String getName(){
		return this.name;
	}
	
	public EconomyCosta getEconomyCosta(){
		return this.eco;
	}
	
}
