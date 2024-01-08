package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import conectividad.AccesoDatos;
public class VistaPorFecha {

    private JFrame frame;
    private JTextField fechaIngresada;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaPorFecha window = new VistaPorFecha();
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
    public VistaPorFecha() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 419, 397);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel topPanel = new JPanel(); 

        JLabel lblIngreseFechaAVer = new JLabel("Ingrese fecha a ver (aaaa-mm-dd):");
        topPanel.add(lblIngreseFechaAVer);

        fechaIngresada = new JTextField(10);
        topPanel.add(fechaIngresada);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        frame.getContentPane().add(textArea, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
           
            frame.dispose();
        
        });

        AccesoDatos AD = new AccesoDatos();
        
        JButton btnCargar = new JButton("Cargar");
        btnCargar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(outputStream);

                PrintStream standardOut = System.out;
                System.setOut(printStream);
        		AD.traerMesasPorFechaReserva(fechaIngresada.getText());
        		System.out.flush();
                System.setOut(standardOut);

                // agarra la salida del método listarReservas y la establece en el JTextArea
                String output = outputStream.toString();
                textArea.setText(output);
        	}
        });
        topPanel.add(btnCargar);

       

        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnVolver);

        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }
}
