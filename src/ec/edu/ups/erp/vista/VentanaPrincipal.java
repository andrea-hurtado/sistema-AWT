package ec.edu.ups.erp.vista;

import java.awt.*;

public class VentanaPrincipal extends Frame {
    private Panel panelSesion;

    private Button botonSecion;
    private Button botonRegistarUsuario;
    private Button botonsalir;

    public VentanaPrincipal() {
        setTitle("--Sistema AWT--");
        setSize(500, 500);
        setLocationRelativeTo(null);


        panelSesion = new Panel();
        panelSesion.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(panelSesion);

        botonSecion= new Button ("Iniciar Sesión");
        botonRegistarUsuario = new Button ("Registrar Usuario");
        botonsalir = new Button ("Salir");

        panelSesion.add(botonSecion);
        panelSesion.add(botonRegistarUsuario);
        panelSesion.add(botonsalir);

        setVisible(true);

    }


    private Button iniciarSesion;
    private Button registrarSesion;
    private Button salirSesion;

    public VentanaPrincipal() {
        setTitle("--SISTEMA AWT--");
        setSize(500, 500);
        setLocationRelativeTo(null);

    }
}
