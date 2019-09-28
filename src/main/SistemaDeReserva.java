package main;

import java.util.ArrayList;
import data.*;
import ui.Consola;

public class SistemaDeReserva {

    private static ArrayList<Dia> dias= new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    
    public static void main(String[] args) {
    	
        Consola consola = new Consola();
    	boolean activo=true;
    	
    	while(activo) {
    		if(dias.isEmpty()) {
    			addDia();
    			addDia();
    			addDia();
    		}
    		Usuario comprador = consola.login();
    		
    		boolean activoMenu=true;
    		while(activoMenu)
    			activoMenu=consola.menu(comprador);
    	}
    }

    public static void addDia() {
		dias.add(new Dia());
	}
    
	public static ArrayList<Dia> getDias() {
		return dias;
	}

	public static void setDias(ArrayList<Dia> dias) {
		SistemaDeReserva.dias = dias;
	}

	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(ArrayList<Usuario> usuarios) {
		SistemaDeReserva.usuarios = usuarios;
	}
    
    
    
}
