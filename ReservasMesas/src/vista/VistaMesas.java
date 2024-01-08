package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import conectividad.AccesoDatos;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VistaMesas {

    private JFrame frame;
    private Inicial inicial;
    /**
     * Launch the application.
     */
    public JFrame getFrame() {
        return frame;
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaMesas window = new VistaMesas();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public VistaMesas() {
        initialize();
        inicial = new Inicial();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JLabel lblMesasDelRestaurante = new JLabel("Mesas del restaurante:");
        lblMesasDelRestaurante.setFont(new Font("Arial", Font.BOLD, 14));
        frame.getContentPane().add(lblMesasDelRestaurante, BorderLayout.NORTH);

        JTextArea textAreaMesas = new JTextArea();
        frame.getContentPane().add(textAreaMesas, BorderLayout.CENTER);
        
        AccesoDatos AD = new AccesoDatos();
        AD.traeDatosMesas();
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream standardOut = System.out;
        System.setOut(printStream);
        
        AD.mostrarDatosMesas();
        System.out.flush();
        System.setOut(standardOut);

        // agarra la salida del método listarReservas y la establece en el JTextArea
        String output = outputStream.toString();
        textAreaMesas.setText(output);
        
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
          
            frame.dispose();
            inicial.getFrame().setVisible(true);
            
        });

        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnVolver);

        frame.getContentPane().add(panelBoton, BorderLayout.SOUTH);
    }
}
