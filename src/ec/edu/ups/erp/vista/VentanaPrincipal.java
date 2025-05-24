package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.GestorUsuarios;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal extends Frame {

    private Panel panelSesion;
    private Panel panelContenedor;

    private Button botonSesion;
    private Button botonRegistarUsuario;
    private Button botonsalir;

    private GestorUsuarios gestorUsuarios;


    public VentanaPrincipal() {
        this.gestorUsuarios = GestorUsuarios.getInstance();

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

        botonSesion.addActionListener(e -> {
            VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(gestorUsuarios);
            ventanaInicioSesion.setVisible(true);
            dispose();
        });

        botonRegistarUsuario.addActionListener(e -> {
            VentanaRegistroUsuario ventanaRegistro = new VentanaRegistroUsuario(gestorUsuarios);
            ventanaRegistro.setVisible(true);
            dispose();
        });

        panelSesion.add(botonSesion);
        panelSesion.add(botonRegistarUsuario);
        panelSesion.add(botonsalir);

        panelContenedor.add(panelSesion);
        add(panelContenedor);

        setVisible(true);

        botonsalir.addActionListener(e -> System.exit(0));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

}
