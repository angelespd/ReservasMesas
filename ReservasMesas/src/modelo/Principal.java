package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import modelo.Mesa.Estado;

public class Principal {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;
		Restaurante resto = new Restaurante("Paucke ATENEO Inmaculada", "General Lopez 2545", "Santa Fe", 4,4,3);
		while (opcion != 11) {

			System.out.println("------ RESTAURANTE ------");
			System.out.println("0. Info del restó");
			System.out.println("1. Ver todas las mesas");
			System.out.println("2. Ver mesas por ESTADO");
			System.out.println("3. Ver mesas reservadas por FECHA (dd/mm/aaaa). ");
			System.out.println("4. Ver capacidad total, mesas libres, ocupadas y reservadas. ");
			System.out.println("5. Agregar una mesa");
			System.out.println("6. Eliminar una mesa");
			System.out.println("7. Reservar una mesa");
			System.out.println("8. Liberar una mesa");
			System.out.println("9. Ocupar una mesa");
			System.out.println("10. Listado de reservas");
			System.out.println("11. Salir del sistema.");
			System.out.print("Ingrese una opción: ");


			opcion = teclado.nextInt();
			teclado.nextLine(); 

			switch (opcion) {
			case 0:
				/*System.out.println("--------- RESTAURANTE -----------");
				System.out.println("Nombre: "+ resto.getNombre());
				System.out.println("Domicilio: "+ resto.getDomicilio());
				System.out.println("Localidad: "+ resto.getLocalidad());
				System.out.println("Cantidad de mesas para 2: " + resto.getMesasParaDos().size());
				System.out.println("Cantidad de mesas para 4: " + resto.getMesasParaDos().size());
				System.out.println("Cantidad de mesas para 6: " + resto.getMesasParaDos().size());*/
				
				resto.mostrarDatosResto();

				teclado.nextLine();

				break;
			case 1:
				System.out.println("--------- Mesas -----------");
				resto.mostrarMesas();
				teclado.nextLine();
				break;
			case 2:
				System.out.println("--------- Mesas por ESTADO -----------");
				System.out.println("Ingrese mesas en qué estado desea ver. (L)IBRE, (O)CUPADA, o (R)ESERVADA: ");
				String estadoElegido = teclado.nextLine();
				resto.mostrarTodasLasMesasPorEstado(estadoElegido);
				teclado.nextLine();
				break;
			case 3:
				System.out.println("--------- Mesas por FECHA -----------");
				System.out.println("Ingrese una fecha para ver mesas: (dd/mm/aaaa)");
				String fechaIngresada = teclado.nextLine();				
				resto.mostrarMesasReservadasPorFecha(fechaIngresada);				
				teclado.nextLine();
				break;

			case 4:				
				resto.mostrarInfoCapacidadMesas();
				teclado.nextLine(); 
				break;
				
			case 5:
		        resto.agregarNuevaMesa();
				teclado.nextLine(); 
				break;
				
			case 6:	     
				resto.eliminarMesa();				
		        teclado.nextLine();
		        break;
				
			case 7:
				System.out.println("------RESERVA DE MESA------");
				System.out.println("Cree un ID de reserva: ");
				int idReservaIngresado = teclado.nextInt();
				System.out.println("Ingrese el NUMERO de mesa a reservar: ");
				int nroMesaIngresado = teclado.nextInt();
				System.out.println("Ingrese la cantidad de comensales: ");
				int comensalesIngresados = teclado.nextInt();

				 Mesa mesaReservada = null;//new Mesa(nroMesaIngresado, comensalesIngresados);
				try {		
					mesaReservada =  resto.buscarMesaPorNumero(nroMesaIngresado);				
					//validar que se cambien los datos de la mesa que tenga el mismo nroMesaIngresado
					if(mesaReservada!= null) {
						if(comensalesIngresados <= 2 || comensalesIngresados == 3 || comensalesIngresados == 4 || comensalesIngresados <=6) {		
							mesaReservada.reservar(mesaReservada);							
						
						}			
					} 
				}catch (Exception e) {
					
					System.out.println("Error al reservar la mesa: " + e.getMessage());
				}
				//mesaReservada.reservar(mesaReservada);
				System.out.println("Ingrese nombre para la reserva: ");
				teclado.nextLine();
				String nombreReserva = teclado.nextLine();
				
				System.out.println("Ingrese apellido: ");
				String apellidoReserva = teclado.nextLine();
				System.out.println("Ingrese la fecha de la reserva(dd/mm/aaaa): ");
				String fechaReservaString = teclado.nextLine();
				SimpleDateFormat formatoFecha = new SimpleDateFormat( "dd/MM/yyyy");
				formatoFecha.setLenient(false); 				
				Date fechaReserva = null;
				try {
					fechaReserva = formatoFecha.parse(fechaReservaString);
					System.out.println("RESERVA REALIZADA CON EXITO!");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("Error al cargar la fecha.");
					e.printStackTrace();
				}

				Reserva nuevaReserva = new Reserva(nombreReserva, apellidoReserva, comensalesIngresados, idReservaIngresado, fechaReserva, mesaReservada);
				resto.getListadoReservas().add(nuevaReserva);
				//System.out.println("Reserva de " + nuevaReserva.getNombre() + " realizada.");
				teclado.nextLine();
				
				break;
			case 8:
				System.out.println("-----Liberación de mesa-----");
				System.out.println("Ingrese el nro de mesa a LIBERAR: ");
				int nroMesaLiberada = teclado.nextInt();
				System.out.println("Ingrese el nro de comensales: ");
				int nroComensales = teclado.nextInt();
				System.out.println("Ingrese la cantidad de consumo de la mesa: ");
				double consumo = teclado.nextDouble();
				Mesa mesaLiberada = resto.buscarMesaPorNumero(nroMesaLiberada);
				if(mesaLiberada != null) {
					
					mesaLiberada.liberar(mesaLiberada);
					System.out.println("Mesa Liberada con exito!");
					teclado.nextLine();
				}
				break;
			case 9:
				System.out.println("-----Ocupación de mesa-----");
				System.out.println("Ingrese el nro de mesa a OCUPAR: ");
				int nroMesaOcupada = teclado.nextInt();
				System.out.println("Ingrese el nro de comensales: ");
				int nroComensalesOcupada = teclado.nextInt();
				Mesa mesaOcupada = resto.buscarMesaPorNumero(nroMesaOcupada);
				if(mesaOcupada != null && !mesaOcupada.getEstado().equals(Estado.OCUPADA)) {
					mesaOcupada.ocupar(mesaOcupada);
					System.out.println("Mesa Ocupada con exito!");
					teclado.nextLine();
				} else {
					System.out.println("No se puede ocupar la mesa porque no se encuentra o está ocupada.");
				}
				break;
			case 10:
				System.out.println("--------- Listado de reservas -----------");
				 for (Reserva reserva : resto.getListadoReservas()) {
				        System.out.println("ID de Reserva: " + reserva.getIdReserva());
				        System.out.println("Fecha de Reserva: " + reserva.getFecha());
				        System.out.println("Nombre: " + reserva.getNombre());
				        System.out.println("----------------------------------------");
				    }
				teclado.nextLine();
				break;
			case 11:
				break;
			}

		}

	}

}
