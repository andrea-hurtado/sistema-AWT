package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.controllers.GestorCompras;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaMenu extends Frame {
    private Panel panelPrincipal;
    private Panel panelAgregar;
    private Panel panelListar;
    private Panel panelCrear;
    private Panel panelbotonesAgregar;
    private Panel panelbotonesListar;
    private Panel panelbotonesCrear;
    private Label labelAgregar;
    private Label labelListar;
    private Label labelCrear;
    private Button botonAgregarProducto;
    private Button botonAgregarProveedor;
    private Button botonListarProducto;
    private Button botonListarProveedor;
    private Button botonCrearSolicitud;
    private GestorCompras gestor;

    public VentanaMenu(){
        this.gestor = new GestorCompras();

        setTitle("Menu Principal");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10 , 10));

        panelbotonesAgregar = new Panel(new FlowLayout());
        panelbotonesListar = new Panel(new FlowLayout());
        panelbotonesCrear = new Panel(new FlowLayout());


        //Panel para Agregar prouctos y proveedores
        panelAgregar = new Panel(new BorderLayout(5, 5));
        labelAgregar = new Label("Agregar", Label.CENTER);

        botonAgregarProducto= new Button("Producto");
        botonAgregarProveedor = new Button("Proveedor");

        panelbotonesAgregar.add(botonAgregarProducto);
        panelbotonesAgregar.add(botonAgregarProveedor);
        panelAgregar.add(labelAgregar, BorderLayout.NORTH);
        panelAgregar.add(panelbotonesAgregar, BorderLayout.CENTER);

        //Panel para Listar productos y proveedores
        panelListar = new Panel(new BorderLayout(5, 5));
        labelListar = new Label("Listar", Label.CENTER);

        botonListarProducto = new Button("Producto");
        botonListarProveedor = new Button("Proveedor");

        panelbotonesListar.add(botonListarProducto);
        panelbotonesListar.add(botonListarProveedor);
        panelListar.add(labelListar, BorderLayout.NORTH);
        panelListar.add(panelbotonesListar, BorderLayout.CENTER);

        //Panel para crear solicitus de compra
        panelCrear = new Panel(new BorderLayout(5, 5));
        labelCrear = new Label("Crear", Label.CENTER);

        botonCrearSolicitud = new Button("Solicitud de Compra");

        panelbotonesCrear.add(botonCrearSolicitud);
        panelCrear.add(labelCrear, BorderLayout.NORTH);
        panelCrear.add(panelbotonesCrear, BorderLayout.CENTER);

        botonAgregarProducto.addActionListener(e -> {
            VentanaAgregarProducto ventanaProducto = new VentanaAgregarProducto(gestor);
            ventanaProducto.setVisible(true);
        });

        botonAgregarProveedor.addActionListener(e -> {
            VentanaAgregarProveedor ventanaProveedor = new VentanaAgregarProveedor(gestor);
            ventanaProveedor.setVisible(true);
        });

        botonListarProducto.addActionListener(e -> {
            mostrarMensaje("Lista de Productos", gestor.obtenerListaProductos());
        });

        botonListarProveedor.addActionListener(e -> {
            mostrarMensaje("Lista de Proveedores", gestor.obtenerListaProveedores());
        });

        botonCrearSolicitud.addActionListener(e -> {
            // Aquí agregarás la lógica para crear solicitudes
            mostrarMensaje("Aviso", "Función en desarrollo");
        });

        add(panelAgregar);
        add(panelListar);
        add(panelCrear);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new VentanaPrincipal().setVisible(true);
            }
        });


    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Dialog dialogo = new Dialog(this, titulo, true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(250, 100);
        dialogo.setLocationRelativeTo(null);

        TextArea textArea = new TextArea(mensaje, 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setEditable(false);

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button botonOk = new Button("OK");

        botonOk.addActionListener(e -> dialogo.dispose());

        panelBoton.add(botonOk);

        dialogo.add(textArea, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);

        dialogo.setVisible(true);

    }
}