
package data;

import java.util.ArrayList;

/**
 *
 * @author User
 */


public class Usuario {
    private String nombre;
    private String cedula;
    private String credito;
    private ArrayList<Reserva> reservas=new ArrayList<>();
    
     
	public Usuario(String nombre, String cedula) {
		this.nombre = nombre;
		this.cedula = cedula;
	}
	
	public void addReserva(Reserva reserva) {
		reservas.add(reserva);
	}
	
	public void mostrarReservas() {
		int numReserva=1;
		for(Reserva reserva:reservas) {
			System.out.println(numReserva+" "+reserva.toString());
			numReserva++;
		}
	}
	
	public void eliminarReserva(int num) {
		reservas.get(num).getSilla().setOcupada(false);
		reservas.remove(num);
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCredito() {
		return credito;
	}

	public void setCredito(String credito) {
		this.credito = credito;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
    
    
}
