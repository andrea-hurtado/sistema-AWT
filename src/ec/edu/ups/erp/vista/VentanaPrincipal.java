package ec.edu.ups.erp.vista;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal extends Frame {

    private Panel panelSesion;
    private Panel panelContenedor;

    private Button botonSesion;
    private Button botonRegistarUsuario;
    private Button botonsalir;

    public VentanaPrincipal() {
        setTitle("--Sistema AWT--");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        panelContenedor = new Panel();
        panelContenedor.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        panelSesion = new Panel();
        panelSesion.setLayout(new GridLayout(3, 1,0,10));
        panelSesion.setPreferredSize(new Dimension(200, 150));

        botonSesion= new Button ("Iniciar Sesión");
        botonRegistarUsuario = new Button ("Registrar Usuario");
        botonsalir = new Button ("Salir");

        panelSesion.add(botonSesion);
        panelSesion.add(botonRegistarUsuario);
        panelSesion.add(botonsalir);

        panelContenedor.add(panelSesion);
        add(panelContenedor);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

}
