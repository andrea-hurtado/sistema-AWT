package ec.edu.ups.erp.vista;

import java.awt.*;

public class VentanaPrincipal extends Frame {
    private Panel panelSesion;

    private Button botonSesion;
    private Button botonRegistarUsuario;
    private Button botonsalir;

    public VentanaPrincipal() {
        setTitle("--Sistema AWT--");
        setSize(500, 500);
        setLocationRelativeTo(null);

        botonSesion= new Button ("Iniciar Sesión");
        botonRegistarUsuario = new Button ("Registrar Usuario");
        botonsalir = new Button ("Salir");

        panelSesion.add(botonSesion);
        panelSesion.add(botonRegistarUsuario);
        panelSesion.add(botonsalir);

        setVisible(true);

    }


}
