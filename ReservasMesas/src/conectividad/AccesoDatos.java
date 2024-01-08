package conectividad;


	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.Calendar;
	import java.util.Scanner;
	import java.io.ByteArrayOutputStream;
	import java.io.PrintStream;
	import modelo.Mesa;
	import modelo.Reserva;
	import modelo.Restaurante;
	


public class AccesoDatos {
	
	
		Connection con;
		Statement st;
		ResultSet rs;
		Calendar c = Calendar.getInstance();
		int dia = Integer.valueOf(c.get(Calendar.DATE));
		int mes = Integer.valueOf(c.get(Calendar.MONTH));
		int ano = Integer.valueOf(c.get(Calendar.YEAR)-1900);
		@SuppressWarnings("deprecation")
		
		Date fecha = new Date(ano,mes,dia);
		String fechaString = new SimpleDateFormat("dd/MM/yyyy").format(fecha).toString();
		Scanner teclado = new Scanner(System.in);

		public void abrirConexion() {
			try {
				String userName="root";
				String password="";
				String

				url="jdbc:mysql://localhost:3306/gestionrestaurante";
				con = DriverManager.getConnection(url, userName, password);
				
			}
			catch (Exception e) {
				System.out.println("Error en conexión ");
				System.out.println(e.getMessage());
			}
		}

		public void abrirConexionSiNoEstaAbierta() {
			if (con == null) {
				abrirConexion();
			}
		}
		//Para cerrar la conexión una vez terminadas las consultas
		public void cerrarConexion() {
			try {
				con.close();
				System.out.println("Conexión cerrada");
			}
			catch (SQLException e) {
				System.out.println("Error al cerrar conexión");
			}
		}
		//UPDATE Restaurante
		//SET nombre = 'nuevo_nombre', domicilio = 'nuevo_domicilio', localidad = 'nueva_localidad'
		//WHERE idRestaurante = 1;
		public void cargarNuevoRestaurante(String nombre,String direccion,String localidad) {
			abrirConexionSiNoEstaAbierta();
			try {
		        Statement s1 = con.createStatement();
		        String query = "UPDATE restaurante SET nombre = '" + nombre + "', direccion = '" + direccion + "', localidad = '" + localidad + "' WHERE idRestaurante = " + 0;
		        s1.executeUpdate(query);
		        System.out.println("Restaurante actualizado correctamente");
		    } catch (SQLException e) {
		        System.out.println("Error al actualizar restaurante");
		        System.out.println(e.getMessage());
		    }
			//cerrarConexion();
		}
		
		public boolean mesaLibre(int nroMesa) {
			abrirConexionSiNoEstaAbierta();
			boolean mesaEstaLibre = false;
			try {
				st =con.createStatement();
				rs = st.executeQuery("SELECT estado from mesa where nroMesa = "+ nroMesa +";");
				if (rs.next()) {
		            String estadoMesa = rs.getString("estado");
		            if (estadoMesa.equals("LIBRE")) {
		                mesaEstaLibre = true;
		            }
		        }
			} catch (SQLException e) {
				System.out.println("Error al Abrir tabla ");
			}
			return mesaEstaLibre;
			
		}
		public void liberarMesa(int nroMesa) {
			abrirConexionSiNoEstaAbierta();
			 try {
			        // Preparar y ejecutar la sentencia SQL para actualizar el estado de la mesa a "OCUPADO"
			        String sql = "UPDATE mesa SET estado = 'LIBRE' WHERE nroMesa = ?";
			        PreparedStatement pstmt = con.prepareStatement(sql);
			        pstmt.setInt(1, nroMesa);
			        pstmt.executeUpdate();

			        System.out.println("Mesa " + nroMesa + " marcada como libre.");
			    } catch (SQLException e) {
			        System.out.println("Error al ocupar la mesa: " + e.getMessage());
			    }
			 // cerrarConexion();
		}
		public void ocuparMesa(int nroMesa) {
			abrirConexionSiNoEstaAbierta();
			 try {
			        // Preparar y ejecutar la sentencia SQL para actualizar el estado de la mesa a "OCUPADO"
			        String sql = "UPDATE mesa SET estado = 'OCUPADA' WHERE nroMesa = ?";
			        PreparedStatement pstmt = con.prepareStatement(sql);
			        pstmt.setInt(1, nroMesa);
			        pstmt.executeUpdate();

			        System.out.println("Mesa " + nroMesa + " marcada como ocupada.");
			    } catch (SQLException e) {
			        System.out.println("Error al ocupar la mesa: " + e.getMessage());
			    }
			 // cerrarConexion();
		}
		public void reservarMesa(int nroMesa) {
			abrirConexionSiNoEstaAbierta();
			 try {
			        // Preparar y ejecutar la sentencia SQL para actualizar el estado de la mesa a "OCUPADO"
			        String sql = "UPDATE mesa SET estado = 'RESERVADA' WHERE nroMesa = ?";
			        PreparedStatement pstmt = con.prepareStatement(sql);
			        pstmt.setInt(1, nroMesa);
			        pstmt.executeUpdate();

			        System.out.println("Mesa " + nroMesa + " marcada como reservada.");
			    } catch (SQLException e) {
			        System.out.println("Error al ocupar la mesa: " + e.getMessage());
			    }
			 // cerrarConexion();
		}
		
		public void traeDatosRestaurante() {
			 abrirConexionSiNoEstaAbierta();
			try {
				st =con.createStatement();
				rs = st.executeQuery("SELECT * FROM restaurante");
				System.out.println("Tabla abierta");
			} catch (SQLException e) {
				System.out.println("Error al Abrir tabla ");
			}
			//cerrarConexion();
		}
		public void mostrarDatosRestaurante() {
			 abrirConexionSiNoEstaAbierta();
			//String datos = " ";
			try {
				while (rs.next()) {
					String nombreRestaurante = rs.getString("nombre");
					String direccion = rs.getString("direccion");
					int id = rs.getInt("idRestaurante");
					
					
					System.out.println("----RESTAURANTE----");
					System.out.println("Nombre: " + nombreRestaurante + ", " +"Domicilio: "+ direccion +", Id: "+ id);
					 	
				}
			} catch (Exception e) {
				System.out.println("Error al visualizar datos" + e.getMessage());
				//e.printStackTrace();
		       // datos = "Error al visualizar datos\n" ;
				
			}
			//cerrarConexion();
		}
		
		
		public void traeDatosMesas() {
			 abrirConexionSiNoEstaAbierta();
			try {
				st =con.createStatement();
				rs = st.executeQuery("SELECT * FROM mesa");
				System.out.println("Tabla abierta");
			} catch (SQLException e) {
				System.out.println("Error al Abrir tabla ");
			}
			//cerrarConexion();
		}
		
		public void mostrarDatosMesas() {
			 abrirConexionSiNoEstaAbierta();
			//String datos = " ";
			try {
				System.out.println("----Mesas----");
				while (rs.next()) {
					int nroMesa = rs.getInt("nroMesa");
					int capacidad = rs.getInt("capacidad");
					double consumo = rs.getDouble("consumo");
					//Mesa.Estado estado = rs.getString("estado");
					String estadoStr = rs.getString("estado");
					Mesa.Estado estado = Mesa.Estado.valueOf(estadoStr); // Convierto String a Enum

		                Mesa mesa = new Mesa();
		                mesa.setNroMesa(nroMesa);
		                mesa.setCapacidad(capacidad);
		                mesa.setConsumo(consumo);
		                mesa.setEstado(estado);
					
					//System.out.println("Nro de mesa: " + nroMesa + ", " +"Capacidad: "+ capacidad +", Consumo: "+ consumo);
		                System.out.println("Nro de mesa: " + mesa.getNroMesa() + ", " + "Capacidad: " + mesa.getCapacidad() + ", Consumo: " + mesa.getConsumo() + ", Estado: " + mesa.getEstado());	
				}
			} catch (Exception e) {
				System.out.println("Error al visualizar datos" + e.getMessage());
				//e.printStackTrace();
		       // datos = "Error al visualizar datos\n" ;
				
			}
			//cerrarConexion();
		}
		
		public void traerMesasPorEstado(String estado) {
			 abrirConexionSiNoEstaAbierta();
			try {
				st =con.createStatement();
				rs = st.executeQuery("SELECT nroMesa, capacidad, consumo FROM mesa  where estado = '" + estado +"';");
				//rs = st.executeQuery("SELECT e.nombre AS nombre_empleado, e.apellido AS apellido_empleado, e.dni AS dni_empleado,f.nombre AS nombre_funcionario, f.apellido AS apellido_funcionario, f.dni AS dni_funcionario from empleado e INNER JOIN funcionario f ON e.organismo_id = f.organismo_id AND e.idLista = f.idLista WHERE e.idLista = 1");
				System.out.println("Tabla abierta");
			} catch (SQLException e) {
				System.out.println("Error al Abrir tabla ");
			}
			//cerrarConexion();
		}
		
		public void mostrarMesasPorEstado(String estado) {
			 abrirConexionSiNoEstaAbierta();
			try {
				System.out.println("----MESAS EN ESTADO " + estado +" ----");
				while (rs.next()) {
					int nroMesa = rs.getInt("nroMesa");
					int capacidad = rs.getInt("capacidad");
					Double consumo = rs.getDouble("consumo");
					System.out.println("Nro de mesa: " + nroMesa + ", " +"Capacidad: "+ capacidad +", Consumo: "+ consumo );
				}
			} catch (Exception e) {
				System.out.println("Error al visualizar datos" + e.getMessage());
			}
			//cerrarConexion();
		}
		
		public void traerMesasPorFechaReserva(String fechaReservaString) {
		    try {
		        abrirConexionSiNoEstaAbierta();
		        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		        Date fechaReserva = new Date(formatoFecha.parse(fechaReservaString).getTime());
		        
		        String query = "SELECT m.nroMesa, m.capacidad, m.consumo, m.estado " +
		                        "FROM Reserva r " +
		                        "INNER JOIN Mesa m ON r.nroMesa = m.nroMesa " +
		                        "WHERE r.fecha = ?";
		        
		        java.sql.PreparedStatement ps = con.prepareStatement(query);
		        ps.setDate(1, fechaReserva);
		        rs = ps.executeQuery();
		        
		        System.out.println("Mesas reservadas para la fecha " + fechaReservaString + ":");
		        while (rs.next()) {
		            int nroMesa = rs.getInt("nroMesa");
		            int capacidad = rs.getInt("capacidad");
		            double consumo = rs.getDouble("consumo");
		            String estadoStr = rs.getString("estado");
		            Mesa.Estado estado = Mesa.Estado.valueOf(estadoStr); // Convertir String a Enum

		            Mesa mesa = new Mesa();
		            mesa.setNroMesa(nroMesa);
		            mesa.setCapacidad(capacidad);
		            mesa.setConsumo(consumo);
		            mesa.setEstado(estado);

		            System.out.println("Número de mesa: " + mesa.getNroMesa());
		            System.out.println("Capacidad: " + mesa.getCapacidad() + " personas.");
		            System.out.println();
		        }
		    } catch (ParseException | SQLException e) {
		        System.out.println("Error al buscar mesas por fecha de reserva: " + e.getMessage());
		    } 
		    // cerrarConexion();
		}
		
		public void agregarMesa(int nroMesa, int capacidad) {
		    abrirConexionSiNoEstaAbierta();
		    if(capacidad <= 6) {
		  	    try {
		        
		        Statement s1 = con.createStatement();
		        s1.executeUpdate("INSERT INTO mesa (nroMesa, capacidad, consumo, estado) VALUES ('"+ nroMesa +"','"+ capacidad +"', '"+ 0.0 +"', '"+ "LIBRE" +"')");
		        System.out.println("Mesa agregada con éxito");
		     
		    } catch (SQLException e) {
		        System.out.println("Error al agregar la mesa: " + e.getMessage());
		    }
		    
		    } else {
		    	System.out.println("La capacidad debe ser menor o igual a 6.");
		    }
		}
		
		public void eliminarMesa(int nroMesa) {
		    abrirConexionSiNoEstaAbierta();

		    try {
		        String checkStateQuery = "SELECT estado FROM mesa WHERE nroMesa = ?";
		        java.sql.PreparedStatement checkPs = con.prepareStatement(checkStateQuery);
		        checkPs.setInt(1, nroMesa);
		        ResultSet stateResult = checkPs.executeQuery();

		        if (stateResult.next()) {
		            String estado = stateResult.getString("estado");
		           // if (estado.equals()) {
		                String deleteQuery = "DELETE FROM mesa WHERE nroMesa = ?";
		                java.sql.PreparedStatement deletePs = con.prepareStatement(deleteQuery);
		                deletePs.setInt(1, nroMesa);

		                int rowsAffected = deletePs.executeUpdate();
		                if (rowsAffected > 0) {
		                    System.out.println("Mesa eliminada exitosamente.");
		                } else {
		                    System.out.println("No se pudo eliminar la mesa.");
		                }
		          
		        } else {
		            System.out.println("No se encontró una mesa con ese número.");
		        }
		    } catch (SQLException e) {
		        System.out.println("Error al eliminar la mesa: " + e.getMessage());
		    }
		    // cerrarConexion();
		}

		 public void registrarReserva(int idRestaurante, String nombre, String apellido, int comensales, String fechaReservaString, int nroMesa) {
		        abrirConexionSiNoEstaAbierta();

		        try {
		            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		            Date fechaReserva = new Date(formatoFecha.parse(fechaReservaString).getTime());

		            String query = "INSERT INTO Reserva (idRestaurante, nombre, apellido, comensales, fecha, nroMesa) VALUES (?, ?, ?, ?, ?, ?)";
		            PreparedStatement ps = con.prepareStatement(query);
		            ps.setInt(1, idRestaurante);
		            ps.setString(2, nombre);
		            ps.setString(3, apellido);
		            ps.setInt(4, comensales);
		            ps.setDate(5, fechaReserva);
		            ps.setInt(6, nroMesa);

		            int rowsAffected = ps.executeUpdate();
		            reservarMesa(nroMesa);
		            if (rowsAffected > 0) {
		                System.out.println("Reserva registrada con éxito.");
		            } else {
		                System.out.println("No se pudo registrar la reserva.");
		            }
		        } catch (ParseException | SQLException e) {
		            System.out.println("Error al registrar la reserva: " + e.getMessage());
		        }
		        //  cerrarConexion();
		    }
		
		 public void traeDatosReservas() {
			 abrirConexionSiNoEstaAbierta();
			try {
				st =con.createStatement();
				rs = st.executeQuery("SELECT * FROM reserva");
				System.out.println("Tabla RESERVAS abierta");
			} catch (SQLException e) {
				System.out.println("Error al Abrir tabla ");
			}
			//cerrarConexion();
		}
		 
		 public void listarReservas() {
			 abrirConexionSiNoEstaAbierta();
			//String datos = " ";
			try {
				System.out.println("----RESERVAS----");
				while (rs.next()) {
					int idReserva = rs.getInt("idReserva");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					int comensales = rs.getInt("comensales");					
					Date fecha = rs.getDate("fecha");		
					int nroMesa = rs.getInt("nroMesa");
					
					System.out.println("ID: " + idReserva + ", " +"Nombre: "+ nombre +", Apellido: "+ apellido
										+ " \nComensales: "+ comensales + ", Fecha: " + fecha +" Nro mesa: "+ nroMesa);						 	
							}
						} catch (Exception e) {
							System.out.println("Error al visualizar datos" + e.getMessage());
							
							
						}
		
		
		
			//cerrarConexion();
		 }	
}
