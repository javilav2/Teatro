package ui;
import java.util.Scanner;

import data.*;
import main.SistemaDeReserva;

public class Consola {

	Scanner teclado = new Scanner(System.in);
	
	public Usuario login() { //Pantalla de inicio para iniciar sesion
		System.out.println("Bienvenido: \nDigite su cedula: ");
		String cedula= teclado.next();
		System.out.println("Digite su nombre: ");
		String nombre= teclado.next();
		for(Usuario usuario:SistemaDeReserva.getUsuarios()) {
			if(usuario.getCedula().equals(cedula)) {
				System.out.println("Inicio de sesión exitoso");
				return usuario;
			}
		}
		Usuario usuario = new Usuario(nombre,cedula);
		SistemaDeReserva.getUsuarios().add(usuario);
		System.out.println("usuario no registrado \n Ha sido registrado\n");
		return usuario;
	}
	
	public boolean menu(Usuario comprador) {//Menu para reservar, consultar, modificar o eliminar la reserva
		System.out.println("Que desea hacer?: \n1-Reservar\n2-Consultar Reserva\n3-Modificar Reserva\n4-Borrar Reserva\n5-Cerrar Sesion\n");
		int opcion = teclado.nextInt();
		
		switch(opcion) {
			case 1:
				reservar(comprador);
				break;
			case 2:
				consultarReserva(comprador);
				break;
			case 3:
				modificarReserva(comprador);
				break;
			case 4:
				eliminarReserva(comprador);
				break;
			case 5:
				return false;
		}
		return true;
	}
	
	public void consultarReserva(Usuario comprador) {//Muestra todas las reservas en el orden que se compraron
		if(comprador.getReservas().isEmpty())
			System.out.println("\nNo tiene reservas\n");
		else
			comprador.mostrarReservas();
	}
	
	public void modificarReserva(Usuario comprador) {//Elimina la reserva que el usuario decida y permite al usuario reservar de nuevo
		if(comprador.getReservas().isEmpty())
			System.out.println("\nNo tiene reservas\n");
		else {
			comprador.mostrarReservas();
			System.out.println("Cual reserva desea modificar: ");
			int numModificar=teclado.nextInt();
			if(numModificar>comprador.getReservas().size()) 
				comprador.eliminarReserva(0);
			else
				comprador.eliminarReserva(numModificar-1);
			reservar(comprador);
		}

	}
	
	public void eliminarReserva(Usuario comprador) {//Elimina la reserva pedida
		if(comprador.getReservas().isEmpty())
			System.out.println("No tiene reservas\n");
		else {
			comprador.mostrarReservas();
			System.out.println("Cual reserva desea eliminar: ");
			int numEliminar=teclado.nextInt();
			if(numEliminar>comprador.getReservas().size()) {
				comprador.eliminarReserva(0);
			}else
				comprador.eliminarReserva(numEliminar-1);
		}
	}
	
	public void reservar(Usuario usuario) {//Reserva una silla para el usuario
		Dia dia = seleccionarDia();
		Pelicula pelicula = seleccionarPelicula(dia);
		Silla silla = seleccionarSilla(pelicula);
		usuario.addReserva(new Reserva(dia,pelicula,pelicula.getSala(),silla));
		usuario.mostrarReservas();
	}
		
	public Dia seleccionarDia() {//Permite al usuario seleccionar el dia de la pelicula
		int numDia= 1;
		
		boolean diasCompletos=true;
		for(Dia dia:SistemaDeReserva.getDias())
			if(!dia.diaCompleto())
				diasCompletos=false;
		if (diasCompletos)
			SistemaDeReserva.addDia();
		
		for(Dia dia:SistemaDeReserva.getDias()) {
			if(dia.diaCompleto())
				System.out.println(numDia+"- "+dia.getFechaDia()+" TODO VENDIDO");
			else
				System.out.println(numDia+"- "+dia.getFechaDia());
    		numDia++;
    	}
		System.out.println("Seleccione el dia: ");
		int selectDia= teclado.nextInt();
		
		if(selectDia>SistemaDeReserva.getDias().size()) {
			for(Dia dia:SistemaDeReserva.getDias())
				if(!dia.diaCompleto())
					return dia;
		}
			
		if(SistemaDeReserva.getDias().get(selectDia-1).diaCompleto()) {
			for(Dia dia:SistemaDeReserva.getDias())
				if(!dia.diaCompleto())
					return dia;
		}
		
		return SistemaDeReserva.getDias().get(selectDia-1);
		
	}


	
	public Pelicula seleccionarPelicula(Dia dia) {//Permite al usuario seleccionar la funcion de entre las peliculas del dia
		dia.mostrarPeliculas();
		System.out.println("Seleccione la función: ");
		int numPelicula= teclado.nextInt();
			
		if(numPelicula>dia.getPeliculas().length)
			return dia.funcionDisponible();
		
		if(dia.getPeliculas()[numPelicula-1].getSala().estaLlena())
			return dia.funcionDisponible();
		
		return dia.getPeliculas()[numPelicula - 1];
		
	}
	
	public Silla seleccionarSilla(Pelicula pelicula) {//Muestra al usuario la sala de la pelicula y permite seleccionar una silla
		pelicula.getSala().mostrarSala();
		System.out.println("Seleccione una silla disponible: ");
		int silla= teclado.nextInt();
		if(silla > pelicula.getSala().getCapacidad() || pelicula.getSala().getSillas()[silla-1].isOcupada())
			return pelicula.getSala().sillaDisponible();
		return pelicula.getSala().ocuparSilla(silla-1);
		
	}
	
}
