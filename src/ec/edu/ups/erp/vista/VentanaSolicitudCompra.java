package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.GestorCompras;
import ec.edu.ups.erp.model.Producto;
import ec.edu.ups.erp.model.SolicitudCompra;

import java.awt.*;
import java.awt.event.*;

public class VentanaSolicitudCompra extends Frame {
    private Panel panelSuperior;
    private Panel panelProductos;
    private Panel panelListaProductos;
    private Panel panelCantidad;
    private Panel panelDetalle;
    private Panel panelBotones;
    private GestorCompras gestor;
    private TextField txtNumeroSolicitud;
    private List listaProductos;
    private TextField txtCantidad;
    private TextArea txtAreaDetalle;
    private Button botonAgregar;
    private Button botonFinalizar;
    private Button botonCancelar;
    private SolicitudCompra solicitudActual;

    public VentanaSolicitudCompra(GestorCompras gestorCompras) {
        this.gestor = gestorCompras;

        setTitle("Nueva Solicitud de Compra");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        panelSuperior = new Panel(new GridLayout(2, 2, 5, 5));

        panelSuperior.add(new Label("Número de Solicitud:"));
        txtNumeroSolicitud = new TextField();
        txtNumeroSolicitud.setText("SOL-");
        panelSuperior.add(txtNumeroSolicitud);

        panelProductos = new Panel(new GridLayout(1, 2, 5, 5));

        panelListaProductos = new Panel(new BorderLayout());
        panelListaProductos.add(new Label("Productos Disponibles:"), BorderLayout.NORTH);
        listaProductos = new List(10);
        cargarProductosEnLista();
        panelListaProductos.add(listaProductos, BorderLayout.CENTER);

        panelCantidad = new Panel(new GridLayout(3, 1));
        panelCantidad.add(new Label("Cantidad:"));
        txtCantidad = new TextField();
        panelCantidad.add(txtCantidad);
        botonAgregar = new Button("Agregar a la Solicitud");
        panelCantidad.add(botonAgregar);

        panelProductos.add(panelListaProductos);
        panelProductos.add(panelCantidad);

        panelDetalle = new Panel(new BorderLayout());
        panelDetalle.add(new Label("Detalle de la Solicitud:"), BorderLayout.NORTH);
        txtAreaDetalle = new TextArea(10, 40);
        txtAreaDetalle.setEditable(false);
        panelDetalle.add(txtAreaDetalle, BorderLayout.CENTER);

        panelBotones = new Panel(new FlowLayout());
        botonFinalizar = new Button("Finalizar Solicitud");
        botonCancelar = new Button("Cancelar");
        panelBotones.add(botonFinalizar);
        panelBotones.add(botonCancelar);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelProductos, BorderLayout.WEST);
        add(panelDetalle, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        solicitudActual = new SolicitudCompra(txtNumeroSolicitud.getText());

        botonAgregar.addActionListener(e -> agregarProductoASolicitud());
        botonFinalizar.addActionListener(e -> finalizarSolicitud());
        botonCancelar.addActionListener(e -> dispose());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void cargarProductosEnLista() {
        for (Producto p : gestor.getProductos()) {
            listaProductos.add(p.getNombre() + " - " + p.getId());
        }
    }

    private void agregarProductoASolicitud() {
        if (listaProductos.getSelectedIndex() == -1) {
            mostrarMensaje("Por favor seleccione un producto de la lista");
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad <= 0) {
                mostrarMensaje("La cantidad debe ser mayor a 0");
                return;
            }

            String seleccion = listaProductos.getSelectedItem();
            String idProducto = seleccion.split(" - ")[1];
            Producto producto = gestor.buscarProducto(idProducto);

            if (producto != null) {
                solicitudActual.agregarProducto(producto, cantidad);
                actualizarDetalle();
                txtCantidad.setText("");
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor ingrese una cantidad válida");
        }
    }

    private void finalizarSolicitud() {
        if (solicitudActual.getProductos().isEmpty()) {
            mostrarMensaje("Debe agregar al menos un producto a la solicitud");
            return;
        }

        gestor.agregarSolicitud(solicitudActual);
        mostrarMensaje("Solicitud creada exitosamente");
        dispose();
    }

    private void actualizarDetalle() {
        StringBuilder detalle = new StringBuilder();
        detalle.append("Productos en la solicitud:\n\n");

        for (java.util.Map.Entry<Producto, Integer> entry : solicitudActual.getProductos().entrySet()) {
            detalle.append(entry.getKey().getNombre())
                    .append(" - Cantidad: ")
                    .append(entry.getValue())
                    .append(" - Precio unitario: $")
                    .append(entry.getKey().getPrecioUnitario())
                    .append("\n");
        }

        detalle.append("\nTotal: $").append(solicitudActual.calcularCostoTotal());
        txtAreaDetalle.setText(detalle.toString());
    }

    private void mostrarMensaje(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new FlowLayout());
        dialogo.setSize(250, 100);
        dialogo.setLocationRelativeTo(this);

        Label label = new Label(mensaje);
        Button btnOk = new Button("OK");
        btnOk.addActionListener(e -> dialogo.dispose());

        dialogo.add(label);
        dialogo.add(btnOk);
        dialogo.setVisible(true);
    }
}