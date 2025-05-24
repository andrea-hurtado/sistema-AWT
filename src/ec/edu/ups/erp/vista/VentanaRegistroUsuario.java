package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.GestorUsuarios;
import ec.edu.ups.erp.model.Usuario;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaRegistroUsuario extends Frame{
    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtUsuario;
    private TextField txtPassword;
    private TextField txtConfirmPassword;
    private Button botonRegistrar;
    private Button botonCancelar;
    private Panel panelPrincipal;
    private Panel panelBotones;
    private GestorUsuarios gestorUsuarios;

    public VentanaRegistroUsuario(GestorUsuarios gestorUsuarios){
        this.gestorUsuarios = GestorUsuarios.getInstance();

        setTitle("Registro de Usuario");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        panelPrincipal = new Panel();
        panelPrincipal.setLayout(new GridLayout(7, 2, 10, 10));

        Label labelNombre = new Label("Nombre: ");
        Label labelApellido = new Label("Apellido: ");
        Label labelUsuario = new Label("Usuario: ");
        Label labelPassword = new Label("Contraseña: ");
        Label labelConfirmPassword = new Label("Confirmar contraseña: ");

        txtNombre = new TextField();
        txtApellido = new TextField();
        txtUsuario = new TextField();
        txtPassword = new TextField();
        txtConfirmPassword = new TextField();

        panelPrincipal.add(labelNombre);
        panelPrincipal.add(txtNombre);
        panelPrincipal.add(labelApellido);
        panelPrincipal.add(txtApellido);
        panelPrincipal.add(labelUsuario);
        panelPrincipal.add(txtUsuario);
        panelPrincipal.add(labelPassword);
        panelPrincipal.add(txtPassword);
        panelPrincipal.add(labelConfirmPassword);
        panelPrincipal.add(txtConfirmPassword);

        panelBotones= new Panel();
        panelBotones.setLayout((new FlowLayout()));

        botonRegistrar =new Button("Registrar");
        botonCancelar = new Button("Cancelar");

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonCancelar);

        botonRegistrar.addActionListener(e -> {
            if (!validarCampos()) {
                return;
            }

            if (registrarUsuario()) {
                mostrarMensaje("Éxito", "Usuario registrado correctamente");
                new VentanaPrincipal().setVisible(true);
                dispose();
            }
        });


        botonCancelar.addActionListener(e -> {
            dispose();
            new VentanaPrincipal().setVisible(true);
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new VentanaPrincipal().setVisible(true);
            }
        });


        add(panelPrincipal);
        add(panelBotones);


    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtApellido.getText().trim().isEmpty() ||
                txtUsuario.getText().trim().isEmpty() ||
                txtPassword.getText().trim().isEmpty() ||
                txtConfirmPassword.getText().trim().isEmpty()) {
            mostrarMensaje("Error", "Todos los campos son obligatorios");
            return false;
        }

        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            mostrarMensaje("Error", "Las contraseñas no coinciden");
            return false;
        }

        return true;
    }

    private boolean registrarUsuario() {
        Usuario nuevoUsuario = new Usuario(
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtUsuario.getText().trim(),
                txtPassword.getText()
        );

        boolean registroExitoso = gestorUsuarios.registrarUsuario(nuevoUsuario);

        if (!registroExitoso) {
            mostrarMensaje("Error", "El nombre de usuario ya existe");
            return false;
        }

        return true;
    }



    private void mostrarMensaje(String titulo, String mensaje) {
        Dialog dialogo = new Dialog(this, titulo, true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(250, 100);
        dialogo.setLocationRelativeTo(null);

        Label label = new Label(mensaje);
        Button botonOk = new Button("OK");
        botonOk.addActionListener(e -> dialogo.dispose());

        dialogo.add(label);
        dialogo.add(botonOk);

        dialogo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogo.dispose();
            }
        });

        dialogo.setVisible(true);



    }
}
