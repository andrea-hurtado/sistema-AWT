package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.Empleado;
import ec.edu.ups.erp.model.GestorEmpleados;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaAgregarEmpleado extends Frame {
    private TextField txtId;
    private TextField txtNombre;
    private TextField txtCargo;
    private TextField txtDepartamento;
    private Button botonAgregar;
    private Button botonCancelar;
    private Panel panelPrincipal;
    private Panel panelBotones;
    private GestorEmpleados gestor;

    public VentanaAgregarEmpleado() {
        this.gestor = GestorEmpleados.getInstance();

        setTitle("Agregar Empleado");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        panelPrincipal = new Panel();
        panelPrincipal.setLayout(new GridLayout(4, 2, 10, 10));

        Label labelId = new Label("ID: ");
        Label labelNombre = new Label("Nombre: ");
        Label labelCargo = new Label("Cargo: ");
        Label labelDepartamento = new Label("Departamento: ");

        txtId = new TextField();
        txtNombre = new TextField();
        txtCargo = new TextField();
        txtDepartamento = new TextField();

        panelPrincipal.add(labelId);
        panelPrincipal.add(txtId);
        panelPrincipal.add(labelNombre);
        panelPrincipal.add(txtNombre);
        panelPrincipal.add(labelCargo);
        panelPrincipal.add(txtCargo);
        panelPrincipal.add(labelDepartamento);
        panelPrincipal.add(txtDepartamento);

        panelBotones = new Panel();
        panelBotones.setLayout(new FlowLayout());
        botonAgregar = new Button("Agregar");
        botonCancelar = new Button("Cancelar");

        botonAgregar.addActionListener(e -> guardarEmpleado());
        botonCancelar.addActionListener(e -> dispose());

        panelBotones.add(botonAgregar);
        panelBotones.add(botonCancelar);

        add(panelPrincipal);
        add(panelBotones);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void guardarEmpleado() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String cargo = txtCargo.getText().trim();
        String departamento = txtDepartamento.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || cargo.isEmpty() || departamento.isEmpty()) {
            mostrarMensaje("Por favor, complete todos los campos");
            return;
        }

        Empleado empleado = new Empleado(id, nombre, cargo, departamento);
        gestor.agregarEmpleado(empleado);
        mostrarMensaje("Empleado agregado exitosamente");
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCargo.setText("");
        txtDepartamento.setText("");
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