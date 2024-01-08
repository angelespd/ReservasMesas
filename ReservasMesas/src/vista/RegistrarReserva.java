package vista;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import modelo.Principal;
import modelo.Restaurante;
import modelo.Mesa;
import modelo.Reserva;
import conectividad.AccesoDatos;
import conectividad.PruebaAccesoDatos;

public class RegistrarReserva {

	private JFrame frame;
	private JTextField nombreReservaIngresado;
	private JTextField nroComensalesReserva;
	private JTextField fechaReserva;
	private JTextField nroMesaReserva;
	private Inicial inicial;
	public JFrame getFrame() {

		return frame;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarReserva window = new RegistrarReserva();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrarReserva() {
		initialize();
		inicial = new Inicial();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\angel\\Desktop\\iconoRestaurante - copia.jpg"));
		frame.setBounds(100, 100, 541, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(5, 2, 0, 0));
		AccesoDatos AD = new AccesoDatos();
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel labelNombreReserva = new JLabel("  Nombre de quien reserva:");
		panel.add(labelNombreReserva);

		nombreReservaIngresado = new JTextField();
		panel.add(nombreReservaIngresado);
		nombreReservaIngresado.setColumns(10);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel labelFechaReserva = new JLabel("  Fecha de la reserva (aaaa-mm-dd): ");
		panel_1.add(labelFechaReserva);

		fechaReserva = new JTextField();
		panel_1.add(fechaReserva);
		fechaReserva.setColumns(10);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("  Apellido de quien reserva:");
		panel_2.add(lblNewLabel);

		JTextField apellidoIngresadoReserva = new JTextField();
		panel_2.add(apellidoIngresadoReserva);
		apellidoIngresadoReserva.setColumns(10);

		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel nroMesaIngresado = new JLabel("  Numero de mesa a reservar: ");
		panel_3.add(nroMesaIngresado);

		nroMesaReserva = new JTextField();
		panel_3.add(nroMesaReserva);
		nroMesaReserva.setColumns(10);

		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("  Cantidad de comensales:");
		panel_4.add(lblNewLabel_1);

		nroComensalesReserva = new JTextField();
		panel_4.add(nroComensalesReserva);
		nroComensalesReserva.setColumns(10);

		JPanel panel_7 = new JPanel();
		frame.getContentPane().add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_5 = new JPanel();
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_6 = new JPanel();
		frame.getContentPane().add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_8 = new JPanel();
		frame.getContentPane().add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_9 = new JPanel();
		frame.getContentPane().add(panel_9);
		panel_9.setLayout(new GridLayout(2, 2, 0, 0));

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
				inicial.getFrame().setVisible(true);
			}
		});
		panel_9.add(botonCancelar);

		JButton botonGuardar = new JButton("Cargar Reserva");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreIngresado = nombreReservaIngresado.getText();
				String apellidoIngresado = apellidoIngresadoReserva.getText();
				String nroComensales = nroComensalesReserva.getText();

				String fechaIngresada = fechaReserva.getText();
				String nroMesa = nroMesaReserva.getText();

				if (nombreIngresado.isEmpty() || apellidoIngresado.isEmpty() || nroComensales.isEmpty()|| fechaIngresada.isEmpty() || nroMesa.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
				} else {
					try {
						int nroComensalesIngresado = Integer.parseInt(nroComensalesReserva.getText());
						int nroMesaIngresado = Integer.parseInt(nroMesaReserva.getText());
						AD.registrarReserva(0, nombreIngresado, apellidoIngresado, nroComensalesIngresado, fechaIngresada, nroMesaIngresado);
						JOptionPane.showMessageDialog(null, "Reserva para " + nombreIngresado + " cargada con éxito!");
						nombreReservaIngresado.setText(" ");
						apellidoIngresadoReserva.setText(" ");
						nroComensalesReserva.setText("");
						fechaReserva.setText("");
						nroMesaReserva.setText("");
					}catch (NullPointerException ex){
						System.out.println("Error en la carga de la reserva. " + ex.getMessage());
					}
				}
			}
		});
		panel_9.add(botonGuardar);
	}

}
