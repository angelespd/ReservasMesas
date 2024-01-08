package vista;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import modelo.Principal;
import modelo.Restaurante;
import modelo.Mesa;
import modelo.Reserva;
import conectividad.AccesoDatos;
import conectividad.PruebaAccesoDatos;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Inicial {
	//---------------------------------------------------------541x364
	private JFrame frmGestionDeReservas;
	public JFrame getFrame() {
        return frmGestionDeReservas;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicial window = new Inicial();
					window.frmGestionDeReservas.setVisible(true);
					window.frmGestionDeReservas.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionDeReservas = new JFrame();
		frmGestionDeReservas.setTitle("GESTION DE RESERVAS RESTAURANTE");
		frmGestionDeReservas.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\angel\\Desktop\\iconoRestaurante - copia.jpg"));
		frmGestionDeReservas.setBounds(100, 100, 541, 364);
		frmGestionDeReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDeReservas.getContentPane().setLayout(new BorderLayout(0, 0));
		Restaurante inmaculada = new Restaurante("Paucke ATENEO Inmaculada", "General Lopez 2545", "Santa Fe", 4,4,3);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\angel\\Desktop\\iconoRestaurante.jpg"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmGestionDeReservas.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmGestionDeReservas.setJMenuBar(menuBar);
		
		JMenu menuReserva = new JMenu("Reserva");
		menuReserva.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(menuReserva);
		
		JMenuItem menuRegistrarReserva = new JMenuItem("Registrar");
		menuRegistrarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva reserva = new Reserva();
				frmGestionDeReservas.setVisible(false);
				RegistrarReserva registrarReserva = new RegistrarReserva();
				registrarReserva.getFrame().setVisible(true);
			}
		});
		menuReserva.add(menuRegistrarReserva);
		
		JMenuItem menuListadoReservas = new JMenuItem("Listado Reservas");
		menuListadoReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionDeReservas.setVisible(false);
				ListadoReservas listadoReservas = new ListadoReservas();
				listadoReservas.getFrame().setVisible(true);
				
			}
		});
		menuReserva.add(menuListadoReservas);
		
		
		JMenu menuGestion = new JMenu("Gesti\u00F3n");
		menuGestion.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(menuGestion);
		
		JMenuItem menuAltaMesa = new JMenuItem("Alta Mesa");
		menuAltaMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionDeReservas.setVisible(false);
				AltaMesa altaMesa = new AltaMesa();
				altaMesa.getFrame().setVisible(true);
			}
		});
		menuGestion.add(menuAltaMesa);
		
		JMenuItem menuBajaMesa = new JMenuItem("Baja Mesa");
		menuBajaMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionDeReservas.setVisible(false);
				BajaMesa bajaMesa = new BajaMesa();
				bajaMesa.getFrame().setVisible(true);
			}
		});
		menuGestion.add(menuBajaMesa);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cambiar Restaurante");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionDeReservas.setVisible(false);
				CambiarRestaurante cambiarRestaurante = new CambiarRestaurante();
				cambiarRestaurante.getFrame().setVisible(true);
			}
		});
		menuGestion.add(mntmNewMenuItem);
		
		
		JMenu menuInfo = new JMenu("Info");
		menuInfo.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(menuInfo);
		
		JMenuItem menuVistaActual = new JMenuItem("Vista actual");
		menuVistaActual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionDeReservas.setVisible(false);
				VistaMesas vistaMesas = new VistaMesas();
				vistaMesas.getFrame().setVisible(true);
				
			}
		});
		menuInfo.add(menuVistaActual);
		
		JMenuItem menuVistaFecha = new JMenuItem("Vista por fecha");
		menuInfo.add(menuVistaFecha);
		
		JMenuItem menuAcercaDe = new JMenuItem("Acerca de..");
		menuAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaFormateada = fechaActual.format(formatter);

                JOptionPane.showMessageDialog(frmGestionDeReservas,
                        "Restaurante: "+ inmaculada.getNombre() +"\nDesarrollador: Angeles Palud\nFecha: " + fechaFormateada,
                        "Acerca de",
                        JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuInfo.add(menuAcercaDe);
		
		
		JMenu menuSalir = new JMenu("Salir");		
		menuSalir.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(menuSalir);
		
		JMenuItem menuSalirOpcion = new JMenuItem("Salir del sistema");
		menuSalirOpcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmGestionDeReservas.setVisible(false);
				System.exit(0);
			}
		});
		
		menuSalir.add(menuSalirOpcion);
		
	}

}
