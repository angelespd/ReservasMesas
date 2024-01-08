package vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import conectividad.AccesoDatos;

public class ListadoReservas {

    private JFrame frame;
    private Inicial inicial;

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListadoReservas window = new ListadoReservas();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ListadoReservas() {
        initialize();
        inicial = new Inicial();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usando FlowLayout para organizar los componentes
        frame.getContentPane().setLayout(new FlowLayout());

        JLabel lblReservas = new JLabel("Listado de Reservas");
        frame.getContentPane().add(lblReservas);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(400, 200));

        JTextArea listadoReservas = new JTextArea();
        scrollPane.setViewportView(listadoReservas);
        frame.getContentPane().add(scrollPane);

        AccesoDatos AD = new AccesoDatos();

        // Se obtienen los datos de las reservas
        AD.traeDatosReservas();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream standardOut = System.out;
        System.setOut(printStream);

        // Llama al método, la salida se guarda en outputStream
        AD.listarReservas();

        System.out.flush();
        System.setOut(standardOut);

        // agarra la salida del método listarReservas y la establece en el JTextArea
        String output = outputStream.toString();
        listadoReservas.setText(output);

        JButton btnSalir = new JButton("Salir");
        frame.getContentPane().add(btnSalir);

        btnSalir.addActionListener(e -> {
            frame.dispose();
            inicial.getFrame().setVisible(true);
        });

        frame.pack(); // Ajustar el tamaño del JFrame para que los componentes se muestren correctamente
    }
}
