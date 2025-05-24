package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.GestorUsuarios;

import java.awt.*;
import java.awt.event.*;

public class VentanaInicioSesion extends Frame{
    private TextField txtUsuario;
    private TextField txtContraseña;
    private Button botonIngresar;
    private Button botonSalir;
    private Panel panelPrincipal;
    private Panel panelBotones;
    private Label labelUsuario;
    private Label labelContraseña;
    private GestorUsuarios gestorUsuarios;

    public VentanaInicioSesion(GestorUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;

        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        panelPrincipal = new Panel();
        panelPrincipal.setLayout(new GridLayout(2, 2, 10, 10));

        labelUsuario = new Label("Usuario: ");
        labelContraseña = new Label("Contraseña: ");
        txtUsuario = new TextField();
        txtContraseña = new TextField();

        panelPrincipal.add(labelUsuario);
        panelPrincipal.add(txtUsuario);
        panelPrincipal.add(labelContraseña);
        panelPrincipal.add(txtContraseña);

        panelBotones= new Panel();
        panelBotones.setLayout((new FlowLayout()));

        botonIngresar =new Button("Ingresar");
        botonSalir = new Button("Salir");

        panelBotones.add(botonIngresar);
        panelBotones.add(botonSalir);

        botonIngresar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String contraseña = txtContraseña.getText().trim();

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                mostrarMensaje("Error", "Por favor, complete todos los campos");
                return;
            }

            if (gestorUsuarios.validarCredenciales(usuario, contraseña)) {
                VentanaMenu menu = new VentanaMenu();
                menu.setVisible(true);
                dispose();
            } else {
                mostrarMensaje("Error", "Usuario o contraseña incorrectos");
                txtContraseña.setText("");
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal().setVisible(true);
            }
        });

        panelBotones.add(botonIngresar);
        panelBotones.add(botonSalir);

        add(panelPrincipal);
        add(panelBotones);

        setVisible(true);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

        });
    }
    private void mostrarMensaje(String titulo, String mensaje) {
        Dialog dialogo = new Dialog(this, titulo, true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(250, 100);
        dialogo.setLocationRelativeTo(null);

        Label label = new Label(mensaje);
        Button btnOk = new Button("OK");

        btnOk.addActionListener(e -> dialogo.dispose());

        dialogo.add(label);
        dialogo.add(btnOk);
        dialogo.setVisible(true);
    }

}
