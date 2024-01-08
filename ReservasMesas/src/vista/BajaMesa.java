package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Principal;
import modelo.Restaurante;
import modelo.Mesa;
import modelo.Reserva;
import conectividad.AccesoDatos;
import conectividad.PruebaAccesoDatos;

public class BajaMesa {

    private JFrame frmEliminacionDeMesa;
    private JTextField textField;
    private Inicial inicial;

    public JFrame getFrame() {
        return frmEliminacionDeMesa;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BajaMesa window = new BajaMesa();
                    window.frmEliminacionDeMesa.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BajaMesa() {
        initialize();
        // Inicializar la ventana Principal
        inicial = new Inicial();
    }

    private void initialize() {
        frmEliminacionDeMesa = new JFrame();
        frmEliminacionDeMesa.setTitle("Eliminacion de mesa");
        frmEliminacionDeMesa.setBounds(100, 100, 324, 363);
        frmEliminacionDeMesa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmEliminacionDeMesa.getContentPane().setLayout(null);
        AccesoDatos AD = new AccesoDatos();
        JLabel label1 = new JLabel("Ingrese el número de mesa a eliminar:");
        label1.setBounds(10, 10, 288, 14);
        frmEliminacionDeMesa.getContentPane().add(label1);

        textField = new JTextField();
        textField.setBounds(10, 35, 288, 20);
        frmEliminacionDeMesa.getContentPane().add(textField);
        textField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 270, 288, 44);
        frmEliminacionDeMesa.getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton buttonCancelar = new JButton("Cancelar");
        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana 
                frmEliminacionDeMesa.dispose();
                // Mostrar la ventana Inicial
                inicial.getFrame().setVisible(true);
            }
        });
        buttonPanel.add(buttonCancelar);

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas eliminar la mesa?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    int nroMesa = Integer.parseInt(textField.getText());
                    if(AD.mesaLibre(nroMesa)) {
                    AD.eliminarMesa(nroMesa);
                    JOptionPane.showMessageDialog(null, "Mesa nro " + nroMesa + " eliminada con éxito!");
                    } else {
                    	JOptionPane.showMessageDialog(null, "No se puede eliminar la mesa nro " + nroMesa + " porque esta ocupada o reservada!");
                    }
                }
            }
        });

        buttonPanel.add(botonEliminar);
    }
}
