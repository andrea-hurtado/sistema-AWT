package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.GestorCompras;
import ec.edu.ups.erp.model.Producto;
import ec.edu.ups.erp.model.SolicitudCompra;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaAgregarProducto extends Frame{
    private TextField txtId;
    private TextField txtNombre;
    private TextField txtPrecio;
    private TextField txtCantidad;
    private Button botonAgregar;
    private Button botonCancelar;
    private Panel panelPrincipal;
    private Panel panelBotones;
    private Panel panelContenedor;
    private Panel panelTitulo;
    private Label labelId;
    private Label labelNombre;
    private Label labelPrecio;
    private Label labelCantidad;
    private Label labelTitulo;
    private GestorCompras gestor;

    public VentanaAgregarProducto(GestorCompras gestorCompras){
        this.gestor =  gestorCompras;

        setTitle("Agregar Producto");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5,5));

        panelTitulo = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelPrincipal = new Panel (new FlowLayout(FlowLayout.CENTER));
        labelTitulo = new Label("Nuevo Producto", Label.CENTER);
        panelTitulo.add(labelTitulo);

        panelPrincipal = new Panel();
        panelPrincipal.setLayout(new GridLayout(4, 2, 5, 5));

        labelId = new Label("ID: ",  Label.RIGHT);
        labelNombre = new Label("Nombre: ", Label.RIGHT);
        labelPrecio= new Label("Precio: ", Label.RIGHT);
        labelCantidad = new Label("Cantidad: ", Label.RIGHT);

        txtId = new TextField(20);
        txtNombre = new TextField(20);
        txtPrecio = new TextField(20);
        txtCantidad = new TextField(20);

        panelContenedor= new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelPrincipal.add(labelId);
        panelPrincipal.add(txtId);
        panelPrincipal.add(labelNombre);
        panelPrincipal.add(txtNombre);
        panelPrincipal.add(labelPrecio);
        panelPrincipal.add(txtPrecio);
        panelPrincipal.add(labelCantidad);
        panelPrincipal.add(txtCantidad);
        panelContenedor.add(panelPrincipal);

        panelBotones= new Panel (new FlowLayout(FlowLayout.CENTER, 10, 5));
        botonAgregar= new Button("Agregar");
        botonCancelar= new Button("Cancelar");

        botonAgregar.setPreferredSize(new Dimension(80, 25));
        botonCancelar.setPreferredSize(new Dimension(80, 25));


        botonAgregar.addActionListener(e -> guardarProducto());
        botonCancelar.addActionListener(e -> dispose());

        panelBotones.add(botonAgregar);
        panelBotones.add(botonCancelar);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelContenedor, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setResizable(false);

        botonCancelar.addActionListener(e -> dispose());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setResizable(false);
    }

    private void guardarProducto() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            mostrarMensaje("Por favor, complete todos los campos necesarios");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);

            Producto producto = new Producto(id, nombre, precio);
            gestor.agregarProducto(producto);

            if (gestor.getSolicitudes().isEmpty()) {
                SolicitudCompra solicitud = new SolicitudCompra("SOL-" + System.currentTimeMillis());
                solicitud.agregarProducto(producto, cantidad);
                gestor.agregarSolicitud(solicitud);
            } else {
                gestor.getSolicitudes().get(0).agregarProducto(producto, cantidad);
            }

            mostrarMensaje("Producto agregado exitosamente");
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarMensaje("El precio y la cantidad deben ser números válidos");
        }
    }


    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
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
