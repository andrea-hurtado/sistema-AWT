package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.controllers.GestorCompras;
import ec.edu.ups.erp.model.Proveedor;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaAgregarProveedor extends Frame{
    private TextField txtId;
    private TextField txtNombre;
    private Button botonAgregar;
    private Button botonCancelar;
    private Panel panelPrincipal;
    private Panel panelBotones;
    private Label labelId;
    private Label labelNombre;
    private GestorCompras gestor;

    public VentanaAgregarProveedor(GestorCompras gestorCompras){
        this.gestor = new GestorCompras();

        setTitle("Agregar Proveedor");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        panelPrincipal = new Panel();
        panelPrincipal.setLayout(new GridLayout(2, 2, 10, 10));

        labelId = new Label("ID: ");
        labelNombre = new Label("Nombre: ");
        txtId = new TextField();
        txtNombre = new TextField();

        panelPrincipal.add(labelId);
        panelPrincipal.add(txtId);
        panelPrincipal.add(labelNombre);
        panelPrincipal.add(txtNombre);

        panelBotones= new Panel();
        panelBotones.setLayout((new FlowLayout()));
        botonAgregar =new Button("Agregar");
        botonCancelar = new Button("Cancelar");

        botonAgregar.addActionListener(e -> guardarProveedor());
        botonCancelar.addActionListener(e -> dispose());

        panelBotones.add(botonAgregar);
        panelBotones.add(botonCancelar);

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
    private void guardarProveedor() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarMensaje("Por favor, complete todos los campos");
            return;
        }

        Proveedor proveedor = new Proveedor(id, nombre);
        mostrarMensaje("Proveedor agregado exitosamente");
        limpiarCampos();
    }
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(250, 100);
        dialogo.setLocationRelativeTo(null);

        Label label = new Label(mensaje);
        Button botonOk = new Button("OK");

        botonOk.addActionListener(e -> dialogo.dispose());

        dialogo.add(label);
        dialogo.add(botonOk);
        dialogo.setVisible(true);
    }


}
