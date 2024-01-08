package vista;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class AltaMesa {

    private JFrame frmAltaMesa;
    private JTextField textField;
    private Inicial inicial;
    public JFrame getFrame() {    	
	    return frmAltaMesa;
	}
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AltaMesa window = new AltaMesa();
                    window.frmAltaMesa.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AltaMesa() {
        initialize();
        inicial = new Inicial();
    }

    private void initialize() {
        frmAltaMesa = new JFrame();
        frmAltaMesa.setTitle("Alta de mesa");
        frmAltaMesa.setBounds(100, 100, 324, 363);
        frmAltaMesa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAltaMesa.getContentPane().setLayout(null);
        AccesoDatos AD = new AccesoDatos();

        JLabel label1 = new JLabel("Ingrese la capacidad de la nueva mesa:");
        label1.setBounds(10, 10, 288, 14);
        frmAltaMesa.getContentPane().add(label1);

        textField = new JTextField();
        textField.setBounds(10, 35, 288, 20);
        frmAltaMesa.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel labelNroMesa = new JLabel("Ingrese NUMERO de mesa nuevo:");
        labelNroMesa.setBounds(10, 65, 288, 14);
        frmAltaMesa.getContentPane().add(labelNroMesa);

        JTextField textFieldNroMesa = new JTextField();
        textFieldNroMesa.setBounds(10, 90, 288, 20);
        frmAltaMesa.getContentPane().add(textFieldNroMesa);
        textFieldNroMesa.setColumns(10);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 270, 288, 44);
        frmAltaMesa.getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton buttonCancelar = new JButton("Cancelar");
        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana 
                frmAltaMesa.dispose();
                // Mostrar la ventana Inicial
                inicial.getFrame().setVisible(true);
            }
        });
        buttonPanel.add(buttonCancelar);
        JButton botonAgregarMesa = new JButton("Agregar Mesa");
        botonAgregarMesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas agregar la mesa?", "Confirmar adición de mesa", JOptionPane.YES_NO_OPTION);
                
                if (opcion == JOptionPane.YES_OPTION) {
                    
                    try {
                    	
						int capacidadMesa = Integer.parseInt(textField.getText());
						int nroMesa = Integer.parseInt(textFieldNroMesa.getText());
						AD.agregarMesa(nroMesa,capacidadMesa);
						JOptionPane.showMessageDialog(null, "Mesa con capacidad agregada con éxito!(Nro de mesa y estado definidos por default)");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar la mesa. " + e1.getMessage());
					} 
                }
            }
        });
        
        buttonPanel.add(botonAgregarMesa);

        frmAltaMesa.setVisible(true);
    }
}
