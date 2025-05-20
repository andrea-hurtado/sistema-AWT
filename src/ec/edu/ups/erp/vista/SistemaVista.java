package ec.edu.ups.erp.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaVista {
    private JFrame frame;
    private JTextArea textArea;
    private JButton iniciarSesionButton;
    private JPanel panel;

    public SistemaVista() {

        frame = new JFrame("Sistema de Gestión");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(50, 100, 500, 200);
        textArea.setEditable(true);
        textArea.setVisible(false);

        // Crear un botón para iniciar sesión
        iniciarSesionButton = new JButton("Iniciar sesión");
        iniciarSesionButton.setBounds(50, 50, 150, 30);
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setVisible(true);
            }
        });


        panel.add(iniciarSesionButton);
        panel.add(textArea);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SistemaVista();
    }
}

