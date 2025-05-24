
package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.GestorCompras;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaMenu extends Frame {
    private Panel panelAgregar;
    private Panel panelListar;
    private Panel panelCrear;
    private Label labelAgregar;
    private Label labelListar;
    private Label labelCrear;
    private Button botonAgregarProducto;
    private Button botonAgregarProveedor;
    private Button botonListarProducto;
    private Button botonListarProveedor;
    private Button botonCrearSolicitud;
    private Button botonGestionarEstados;
    private GestorCompras gestor;

    public VentanaMenu(){
        this.gestor = GestorCompras.getInstance();

        setTitle("Menu Principal");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        panelAgregar = new Panel(new BorderLayout(5, 5));
        labelAgregar = new Label("Agregar", Label.CENTER);
        Panel panelBotonesAgregarVertical = new Panel(new GridLayout(2, 1, 5, 5));

        botonAgregarProducto = new Button("Producto");
        botonAgregarProveedor = new Button("Proveedor");

        panelBotonesAgregarVertical.add(botonAgregarProducto);
        panelBotonesAgregarVertical.add(botonAgregarProveedor);
        panelAgregar.add(labelAgregar, BorderLayout.NORTH);
        panelAgregar.add(panelBotonesAgregarVertical, BorderLayout.CENTER);

        panelListar = new Panel(new BorderLayout(5, 5));
        labelListar = new Label("Listar", Label.CENTER);
        Panel panelBotonesListarVertical = new Panel(new GridLayout(2, 1, 5, 5));

        botonListarProducto = new Button("Producto");
        botonListarProveedor = new Button("Proveedor");

        panelBotonesListarVertical.add(botonListarProducto);
        panelBotonesListarVertical.add(botonListarProveedor);
        panelListar.add(labelListar, BorderLayout.NORTH);
        panelListar.add(panelBotonesListarVertical, BorderLayout.CENTER);

        panelCrear = new Panel(new BorderLayout(5, 5));
        labelCrear = new Label("Crear", Label.CENTER);
        Panel panelBotonesCrearVertical = new Panel(new GridLayout(2, 1, 5, 5));

        botonCrearSolicitud = new Button("Solicitud de Compra");
        botonGestionarEstados = new Button("Gestionar Estados");

        panelBotonesCrearVertical.add(botonCrearSolicitud);
        panelBotonesCrearVertical.add(botonGestionarEstados);
        panelCrear.add(labelCrear, BorderLayout.NORTH);
        panelCrear.add(panelBotonesCrearVertical, BorderLayout.CENTER);

        botonAgregarProducto.addActionListener(e -> {
            VentanaAgregarProducto ventanaProducto = new VentanaAgregarProducto(this.gestor);
            ventanaProducto.setVisible(true);
        });

        botonAgregarProveedor.addActionListener(e -> {
            VentanaAgregarProveedor ventanaProveedor = new VentanaAgregarProveedor(this.gestor);
            ventanaProveedor.setVisible(true);
        });

        botonListarProducto.addActionListener(e -> {
            mostrarMensaje("Lista de Productos", gestor.obtenerListaProductos());
        });

        botonListarProveedor.addActionListener(e -> {
            mostrarMensaje("Lista de Proveedores", gestor.obtenerListaProveedores());
        });

        botonCrearSolicitud.addActionListener(e -> {
            VentanaSolicitudCompra ventanaSolicitud = new VentanaSolicitudCompra(this.gestor);
            ventanaSolicitud.setVisible(true);
        });

        botonGestionarEstados.addActionListener(e -> {
            if (gestor.getSolicitudes().isEmpty()) {
                mostrarMensaje("Error", "No hay solicitudes para gestionar");
            } else {
                VentanaGestionEstados ventanaEstados = new VentanaGestionEstados(gestor);
                ventanaEstados.setVisible(true);
            }
        });

        add(panelAgregar);
        add(panelListar);
        add(panelCrear);

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
        dialogo.setLayout(new BorderLayout(10, 10));
        dialogo.setSize(400, 300);
        dialogo.setLocationRelativeTo(null);

        Panel panelContenido = new Panel(new BorderLayout(5, 5));
        TextArea textArea = new TextArea(mensaje, 10, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setEditable(false);
        panelContenido.add(textArea, BorderLayout.CENTER);

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button botonOk = new Button("OK");
        botonOk.setPreferredSize(new Dimension(80, 25));
        botonOk.addActionListener(e -> dialogo.dispose());
        panelBoton.add(botonOk);

        dialogo.add(panelContenido, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);

        dialogo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogo.dispose();
            }
        });

        dialogo.setVisible(true);
    }
}