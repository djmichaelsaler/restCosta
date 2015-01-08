package com.craftcostaserver.restCosta.negocio;

public class EconomyCosta {
	private double pocket;
	private double bank;
	
	public EconomyCosta(double pocket, double bank){
		this.pocket = pocket;
		this.bank = bank;
	}
	
	public double getPocketMoney(){
		return this.pocket;
	}
	
	public double getBankMoney(){
		return this.bank;
	}

}
