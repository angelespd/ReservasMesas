package vista;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import conectividad.AccesoDatos;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambiarRestaurante {

    private JFrame frame;
    private JTextField nombreRestauranteField;
    private JTextField direccionField;
    private JTextField localidadField;
    private Inicial inicial;

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CambiarRestaurante window = new CambiarRestaurante();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CambiarRestaurante() {
        initialize();
        inicial = new Inicial();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 371, 377);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        AccesoDatos AD = new AccesoDatos();
        JLabel lblNewLabel = new JLabel("Ingrese el nombre del nuevo restaurante:");
        lblNewLabel.setBounds(10, 11, 290, 14);
        frame.getContentPane().add(lblNewLabel);

        nombreRestauranteField = new JTextField();
        nombreRestauranteField.setBounds(10, 30, 290, 20);
        frame.getContentPane().add(nombreRestauranteField);
        nombreRestauranteField.setColumns(10);

        JLabel lblIngreseLaDireccion = new JLabel("Ingrese la dirección:");
        lblIngreseLaDireccion.setBounds(10, 61, 268, 14);
        frame.getContentPane().add(lblIngreseLaDireccion);

        direccionField = new JTextField();
        direccionField.setBounds(10, 80, 290, 20);
        frame.getContentPane().add(direccionField);
        direccionField.setColumns(10);

        JLabel lblIngreseLocalidad = new JLabel("Ingrese Localidad:");
        lblIngreseLocalidad.setBounds(10, 111, 248, 14);
        frame.getContentPane().add(lblIngreseLocalidad);

        localidadField = new JTextField();
        localidadField.setBounds(10, 130, 290, 20);
        frame.getContentPane().add(localidadField);
        localidadField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 260, 335, 67);
        frame.getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnCancelar);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nombreNuevoRestaurante = nombreRestauranteField.getText();
        		String direccionNuevoRestaurante = direccionField.getText();
        		String localidadNuevoRestaurante = localidadField.getText();
        		
        		if (nombreNuevoRestaurante.isEmpty() || direccionNuevoRestaurante.isEmpty() || localidadNuevoRestaurante.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
				} else {
					try {
						
						AD.cargarNuevoRestaurante(nombreNuevoRestaurante, direccionNuevoRestaurante, localidadNuevoRestaurante);
						JOptionPane.showMessageDialog(null, "Restaurante " + nombreNuevoRestaurante + " cargado con éxito!");
						nombreRestauranteField.setText(" ");
						direccionField.setText(" ");
						localidadField.setText("");
						
					}catch (NullPointerException ex){
						System.out.println("Error en la carga del nuevo restaurante. " + ex.getMessage());
					}
				}

        	}
        });
        buttonPanel.add(btnGuardar);

        btnCancelar.addActionListener(e -> {
            frame.dispose();
            inicial.getFrame().setVisible(true);
        });
        
    }
}


