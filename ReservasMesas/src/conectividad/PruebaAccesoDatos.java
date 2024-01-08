package conectividad;

public class PruebaAccesoDatos {
	public static void main(String[] args) {
	AccesoDatos AD = new AccesoDatos();
	AD.abrirConexion();
	AD.traeDatosRestaurante();
	AD.mostrarDatosRestaurante();
	AD.traeDatosMesas();
	AD.mostrarDatosMesas();
	AD.traerMesasPorEstado("LIBRE");
	System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
	AD.ocuparMesa(5);
	AD.mostrarMesasPorEstado("LIBRE");	
	//AD.agregarMesa(12, 6);
	//AD.eliminarMesa(0);
	//AD.registrarReserva(0, "Angeles", "Palud", 4, "15/12/2023", 6);
	//AD.traerMesasPorFechaReserva("2023-12-15");
	AD.traeDatosReservas();
	AD.listarReservas();
	}
}
