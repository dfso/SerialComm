package model;

import jssc.SerialPortList;


public class Porta {
	
	
	
	public Porta() {
		
	}
	
	
	public static String[] obterPortas() {
		
		String[] portas = SerialPortList.getPortNames();
		
		return portas;
	}
		
	
	public static void listarPortas() {
		
		for(String p : obterPortas()) {
			System.out.println(p);
		}
	}
	
	
	
	

}
