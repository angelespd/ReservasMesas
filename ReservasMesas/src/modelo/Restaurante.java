package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Restaurante {
	private String nombre;
	private String domicilio;
	private String localidad;
	private ArrayList<Mesa> mesasParaDos;
	private ArrayList<Mesa> mesasParaCuatro;
	private ArrayList<Mesa> mesasParaSeis;
	private ArrayList<Reserva> listadoReservas;
	
	public Restaurante(String nombre, String domicilio, String localidad, int cantidadMesasParaDos, 
			int cantidadMesasParaCuatro, int cantidadMesasParaSeis) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.localidad = localidad;
		mesasParaDos = new ArrayList<>(cantidadMesasParaDos);
        mesasParaCuatro = new ArrayList<>(cantidadMesasParaCuatro);
        mesasParaSeis = new ArrayList<>(cantidadMesasParaSeis); 
        this.listadoReservas = new ArrayList<Reserva>();
        inicializarMesas(cantidadMesasParaDos, cantidadMesasParaCuatro, cantidadMesasParaSeis);
	}
	public Restaurante() {
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public ArrayList<Mesa> getMesasParaDos() {
		return mesasParaDos;
	}
	public void setMesasParaDos(ArrayList<Mesa> mesasParaDos) {
		this.mesasParaDos = mesasParaDos;
	}
	public ArrayList<Mesa> getMesasParaCuatro() {
		return mesasParaCuatro;
	}
	public void setMesasParaCuatro(ArrayList<Mesa> mesasParaCuatro) {
		this.mesasParaCuatro = mesasParaCuatro;
	}
	public ArrayList<Mesa> getMesasParaSeis() {
		return mesasParaSeis;
	}
	public void setMesasParaSeis(ArrayList<Mesa> mesasParaSeis) {
		this.mesasParaSeis = mesasParaSeis;
	}
	public ArrayList<Reserva> getListadoReservas() {
		return listadoReservas;
	}
	public void setListadoReservas(ArrayList<Reserva> listadoReservas) {
		this.listadoReservas = listadoReservas;
	}
	Scanner teclado = new Scanner(System.in);
	
	public void mostrarDatosResto() {
		System.out.println("--------- RESTAURANTE -----------");
		System.out.println("Nombre: "+ this.getNombre());
		System.out.println("Domicilio: "+ this.getDomicilio());
		System.out.println("Localidad: "+ this.getLocalidad());
		System.out.println("Cantidad de mesas para 2: " + this.getMesasParaDos().size());
		System.out.println("Cantidad de mesas para 4: " + this.getMesasParaDos().size());
		System.out.println("Cantidad de mesas para 6: " + this.getMesasParaDos().size());
		
	}
	
	public void mostrarMesas() {
		
		 System.out.println("Mesas para Dos Personas:");
		    if (mesasParaDos != null) {
		        for (Mesa mesa : mesasParaDos) {
		            System.out.println("Nro de mesa: " + mesa.getNroMesa());
		            System.out.println("Capacidad: " + mesa.getCapacidad() + " personas.");
		            System.out.println("Estado: " + mesa.getEstado());
		            System.out.println();
		        }
		    }

		    // Mesas para Cuatro Personas
		    System.out.println("Mesas para Cuatro Personas:");
		    if (mesasParaCuatro != null) {
		        for (Mesa mesa : mesasParaCuatro) {
		            System.out.println("Nro de mesa: " + mesa.getNroMesa());
		            System.out.println("Capacidad: " + mesa.getCapacidad() + " personas.");
		            System.out.println("Estado: " + mesa.getEstado());
		            System.out.println();
		        }
		    }

		    // Mesas para Seis Personas
		    System.out.println("Mesas para Seis Personas:");
		    if (mesasParaSeis != null) {
		        for (Mesa mesa : mesasParaSeis) {
		            System.out.println("Nro de mesa: " + mesa.getNroMesa());
		            System.out.println("Capacidad: " + mesa.getCapacidad() + " personas.");
		            System.out.println("Estado: " + mesa.getEstado());
		            System.out.println();
		        }
		    }
	}
	
	   public void mostrarTodasLasMesasPorEstado(String opcion) {
	        if(opcion.equals("L")) {
	        System.out.println("Mesas LIBRES:");
	        mostrarMesasPorEstado(mesasParaDos, Mesa.Estado.LIBRE);
	        mostrarMesasPorEstado(mesasParaCuatro, Mesa.Estado.LIBRE);
	        mostrarMesasPorEstado(mesasParaSeis, Mesa.Estado.LIBRE);
	        }else if(opcion.equals("R")){	      
	        System.out.println("Mesas RESERVADAS:");
	        mostrarMesasPorEstado(mesasParaDos, Mesa.Estado.RESERVADA);
	        mostrarMesasPorEstado(mesasParaCuatro, Mesa.Estado.RESERVADA);
	        mostrarMesasPorEstado(mesasParaSeis, Mesa.Estado.RESERVADA);
	        } else if (opcion.equals("O")) {
	        
	        System.out.println("Mesas OCUPADAS:");
	        mostrarMesasPorEstado(mesasParaDos, Mesa.Estado.OCUPADA);
	        mostrarMesasPorEstado(mesasParaCuatro, Mesa.Estado.OCUPADA);
	        mostrarMesasPorEstado(mesasParaSeis, Mesa.Estado.OCUPADA);
	        } else {
	        	System.out.println("Opcion ingresada incorrecta.");
	        }
	    }

	    private void mostrarMesasPorEstado(ArrayList<Mesa> mesas, Mesa.Estado estado) {
	        for (Mesa mesa : mesas) {
	            if (mesa.getEstado().equals(estado)) {
	                System.out.println("Nro de mesa: " + mesa.getNroMesa());
	                System.out.println("Capacidad: " + mesa.getCapacidad());
	                System.out.println();
	            }
	        }
	    }
	    
	    public void mostrarMesasReservadasPorFecha(String fechaReservaString) {
	        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	        try {
	            Date fechaReservaBuscada = formatoFecha.parse(fechaReservaString);

	            System.out.println("Mesas reservadas para la fecha " + fechaReservaString + ":");

	            for (Reserva reserva : listadoReservas) {
	                if (reserva.getFecha().equals(fechaReservaBuscada)) {
	                    Mesa mesaReservada = reserva.getMesaReservada();
	                    System.out.println("Número de mesa: " + mesaReservada.getNroMesa());
	                    System.out.println("Capacidad: " + mesaReservada.getCapacidad() + " personas.");
	                    System.out.println();
	                }
	            }
	        } catch (java.text.ParseException e) {
	            System.out.println("Error al analizar la fecha. Formato incorrecto.");
	        }
	    }
	    
	    public void inicializarMesas(int cantidadMesasParaDos, int cantidadMesasParaCuatro, int cantidadMesasParaSeis) {
	        
	        for (int i = 1; i <= cantidadMesasParaDos; i++) {
	            Mesa mesa = new Mesa(i, 2); 
	            mesasParaDos.add(mesa);
	        }

	       
	        int numeroMesa = cantidadMesasParaDos + 1;
	        for (int i = 1; i <= cantidadMesasParaCuatro; i++) {
	            Mesa mesa = new Mesa(numeroMesa, 4);
	            mesasParaCuatro.add(mesa);
	            numeroMesa++;
	        }

	        
	        for (int i = 1; i <= cantidadMesasParaSeis; i++) {
	            Mesa mesa = new Mesa(numeroMesa, 6);
	            mesasParaSeis.add(mesa);
	            numeroMesa++;
	        }
	    }
	    
	    public void mostrarInfoCapacidadMesas() {
	        System.out.println("-----CAPACIDAD TOTAL------");
	        int totalMesasDos = mesasParaDos.size();
	        int totalMesasCuatro = mesasParaCuatro.size();
	        int totalMesasSeis = mesasParaSeis.size();
	        int totalMesas = totalMesasDos + totalMesasCuatro + totalMesasSeis;

	        System.out.println("Mesas para 2 personas: " + totalMesasDos);
	        System.out.println("Mesas para 4 personas: " + totalMesasCuatro);
	        System.out.println("Mesas para 6 personas: " + totalMesasSeis);
	        System.out.println("Total de mesas: " + totalMesas);

	        cantidadMesasPorEstado();
	    }
	    
	    public void cantidadMesasPorEstado() {
	    	int mesasLibres = 0;
	    	int mesasOcupadas = 0;
	    	int mesasReservadas = 0;

	    	//  mesas para dos personas
	    	for (Mesa mesa : mesasParaDos) {
	    	    if (mesa.getEstado() == Mesa.Estado.LIBRE) {
	    	        mesasLibres++;
	    	    } else if (mesa.getEstado() == Mesa.Estado.OCUPADA) {
	    	        mesasOcupadas++;
	    	    } else if (mesa.getEstado() == Mesa.Estado.RESERVADA) {
	    	        mesasReservadas++;
	    	    }
	    	}
	    	for (Mesa mesa : mesasParaCuatro) {
	    	    if (mesa.getEstado() == Mesa.Estado.LIBRE) {
	    	        mesasLibres++;
	    	    } else if (mesa.getEstado() == Mesa.Estado.OCUPADA) {
	    	        mesasOcupadas++;
	    	    } else if (mesa.getEstado() == Mesa.Estado.RESERVADA) {
	    	        mesasReservadas++;
	    	    }
	    	}
	    	for (Mesa mesa : mesasParaSeis) {
	    	    if (mesa.getEstado() == Mesa.Estado.LIBRE) {
	    	        mesasLibres++;
	    	    } else if (mesa.getEstado() == Mesa.Estado.OCUPADA) {
	    	        mesasOcupadas++;
	    	    } else if (mesa.getEstado() == Mesa.Estado.RESERVADA) {
	    	        mesasReservadas++;
	    	    }
	    	}
	    	System.out.println("Cantidad mesas LIBRES: " + mesasLibres);
	    	System.out.println("Cantidad mesas OCUPADAS: " + mesasOcupadas);
	    	System.out.println("Cantidad mesas RESERVADAS: " + mesasReservadas);
	    	
	    }
	    
	    public  Mesa buscarMesaPorNumero(int numeroMesa) {
	    	for (Mesa mesa : mesasParaDos) {
	            if (mesa.getNroMesa() == numeroMesa) {
	                return mesa;
	            }
	        }
	        for (Mesa mesa : mesasParaCuatro) {
	            if (mesa.getNroMesa() == numeroMesa) {
	                return mesa;
	            }
	        }
	        for (Mesa mesa : mesasParaSeis) {
	            if (mesa.getNroMesa() == numeroMesa) {
	                return mesa;
	            }
	        }
	        
	        return null; //SI NO ENCONTRAS mesa deolveme null
	    }
	    
	    
	    
	    public void eliminarMesa() {
	        boolean mesaEncontrada = false;
	        System.out.println("-----ELIMINACIÓN DE MESA------");
			System.out.println("Ingrese el nro de mesa que desea eliminar: ");
			int nroMesaAEliminar = teclado.nextInt();
	        System.out.println("Realmente desea eliminar la mesa nro " + nroMesaAEliminar + "? Elija 1(Si) o 2(No)");
	        int opcionElegida = teclado.nextInt();
	        
	        if(opcionElegida == 1) {
	        
	            mesaEncontrada = buscarYEliminarMesa(nroMesaAEliminar, mesasParaDos);
	            if (!mesaEncontrada) {
	                mesaEncontrada = buscarYEliminarMesa(nroMesaAEliminar, mesasParaCuatro);
	            }
	            if (!mesaEncontrada) {
	                mesaEncontrada = buscarYEliminarMesa(nroMesaAEliminar, mesasParaSeis);
	            }

	            if (!mesaEncontrada) {
	                System.out.println("La mesa número " + nroMesaAEliminar + " está reservada/ocupada/no existe.");
	            } else {
	                System.out.println("Mesa eliminada con éxito.");
	            }      
	           }
	    }
	    private boolean buscarYEliminarMesa(int nroMesaAEliminar, ArrayList<Mesa> mesas) {
	        for (Mesa mesa : mesas) {
	            if (mesa.getNroMesa() == nroMesaAEliminar && mesa.getEstado() == Mesa.Estado.LIBRE) {
	                mesas.remove(mesa);
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    public boolean mesaExiste(int numeroMesa) {
	       
	        for (Mesa mesa : mesasParaDos) {
	            if (mesa.getNroMesa() == numeroMesa) {
	                return true;
	            }
	        }

	        for (Mesa mesa : mesasParaCuatro) {
	            if (mesa.getNroMesa() == numeroMesa) {
	                return true;
	            }
	        }

	        for (Mesa mesa : mesasParaSeis) {
	            if (mesa.getNroMesa() == numeroMesa) {
	                return true;
	            }
	        }

	        return false;
	    }

	    
	    public void agregarNuevaMesa() {	    	
	    	System.out.println("------ALTA DE MESA------");
	        System.out.println("Ingrese el nro de la mesa nueva: ");
	        int nroMesaNueva = teclado.nextInt();
	        System.out.println("Ingrese la capacidad máxima de la mesa nueva: ");
	        int capacidadMesaNueva = teclado.nextInt();
	        boolean mesaExiste = mesaExiste(nroMesaNueva);
	        if(!mesaExiste) {
	        if (capacidadMesaNueva == 2 || capacidadMesaNueva == 4 || capacidadMesaNueva == 6) {
	            Mesa mesaNueva = new Mesa(nroMesaNueva, capacidadMesaNueva);
	            agregarMesaSegunCapacidad(mesaNueva, capacidadMesaNueva);
	        } else {
	            System.out.println("Las capacidades máximas para las mesas son 2, 4 o 6");
	        }
	        } else {
	        	System.out.println("La mesa con número " + nroMesaNueva + " ya existe en el restaurante.");
	        }
	  
	    }

	    private void agregarMesaSegunCapacidad(Mesa mesa, int capacidad) {
	        if (capacidad <= 2) {
	            mesasParaDos.add(mesa);
	        } else if (capacidad == 4) {
	            mesasParaCuatro.add(mesa);
	        } else if (capacidad == 6) {
	            mesasParaSeis.add(mesa);
	        }
	        
	        System.out.println("Mesa agregada con éxito.");
	    }
	    
	    
}
