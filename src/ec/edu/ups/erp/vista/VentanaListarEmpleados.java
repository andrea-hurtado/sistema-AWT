package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.Empleado;
import ec.edu.ups.erp.model.GestorEmpleados;

import java.awt.*;
import java.awt.event.*;

public class VentanaListarEmpleados extends Frame {
    private Panel panelPrincipal;
    private TextField txtBuscar;
    private List listaEmpleados;
    private Button btnBuscar;
    private Button btnCerrar;
    private Panel panelBotones;
    private Panel panelBusqueda;
    private Panel panelLista;
    private Checkbox checkId;
    private Checkbox checkNombre;
    private Checkbox checkCargo;
    private Checkbox checkDepartamento;
    private CheckboxGroup grupoBusqueda;

    public VentanaListarEmpleados() {
        setTitle("Listado de Empleados");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelPrincipal = new Panel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));

        panelBusqueda = new Panel();
        panelBusqueda.setLayout(new FlowLayout());

        grupoBusqueda = new CheckboxGroup();
        checkId = new Checkbox("ID", grupoBusqueda, true);
        checkNombre = new Checkbox("Nombre", grupoBusqueda, false);
        checkCargo = new Checkbox("Cargo", grupoBusqueda, false);
        checkDepartamento = new Checkbox("Departamento", grupoBusqueda, false);

        txtBuscar = new TextField(20);
        btnBuscar = new Button("Buscar");

        panelBusqueda.add(new Label("Buscar por:"));
        panelBusqueda.add(checkId);
        panelBusqueda.add(checkNombre);
        panelBusqueda.add(checkCargo);
        panelBusqueda.add(checkDepartamento);
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        panelLista = new Panel();
        panelLista.setLayout(new BorderLayout());

        listaEmpleados = new List(10);
        panelLista.add(new Label("Empleados:"), BorderLayout.NORTH);
        panelLista.add(listaEmpleados, BorderLayout.CENTER);

        panelBotones = new Panel();
        panelBotones.setLayout(new FlowLayout());
        btnCerrar = new Button("Cerrar");
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(panelLista, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarEmpleados();
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        cargarEmpleados();
    }

    private void cargarEmpleados() {
        listaEmpleados.removeAll();
        for (Empleado emp : GestorEmpleados.getInstance().getEmpleados()) {
            listaEmpleados.add("ID: " + emp.getId() + 
                          " | Nombre: " + emp.getNombre() + 
                          " | Cargo: " + emp.getCargo() + 
                          " | Departamento: " + emp.getDepartamento());
        }
    }

    private void buscarEmpleados() {
        String busqueda = txtBuscar.getText().trim().toLowerCase();
        if (busqueda.isEmpty()) {
            cargarEmpleados();
            return;
        }

        listaEmpleados.removeAll();
        for (Empleado emp : GestorEmpleados.getInstance().getEmpleados()) {
            boolean coincide = false;

            if (checkId.getState() && emp.getId().toLowerCase().contains(busqueda)) {
                coincide = true;
            } else if (checkNombre.getState() && emp.getNombre().toLowerCase().contains(busqueda)) {
                coincide = true;
            } else if (checkCargo.getState() && emp.getCargo().toLowerCase().contains(busqueda)) {
                coincide = true;
            } else if (checkDepartamento.getState() && emp.getDepartamento().toLowerCase().contains(busqueda)) {
                coincide = true;
            }

            if (coincide) {
                listaEmpleados.add("ID: " + emp.getId() + 
                              " | Nombre: " + emp.getNombre() + 
                              " | Cargo: " + emp.getCargo() + 
                              " | Departamento: " + emp.getDepartamento());
            }
        }
    }
}