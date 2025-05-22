package ec.edu.ups.erp.vista;

import java.awt.*;

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

    public VentanaRegistroUsuario(){
        setTitle("Inicio de Sesión");
        setSize(300, 200);
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

    }


}
