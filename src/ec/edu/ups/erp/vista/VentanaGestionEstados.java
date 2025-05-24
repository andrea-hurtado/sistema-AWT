package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.GestorCompras;
import ec.edu.ups.erp.model.SolicitudCompra;

import java.awt.*;
import java.awt.event.*;

public class VentanaGestionEstados extends Frame {
    private GestorCompras gestor;
    private List listaSolicitudes;
    private Button botonCambiarEstado;
    private Button botonCerrar;
    private TextArea areaDetalles;

    public VentanaGestionEstados(GestorCompras gestor) {
        this.gestor = gestor;

        setTitle("Gestión de Estados de Solicitudes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Panel panelIzquierdo = new Panel(new BorderLayout(5, 5));
        Label labelSolicitudes = new Label("Solicitudes:", Label.CENTER);
        listaSolicitudes = new List();
        actualizarListaSolicitudes();

        panelIzquierdo.add(labelSolicitudes, BorderLayout.NORTH);
        panelIzquierdo.add(listaSolicitudes, BorderLayout.CENTER);

        Panel panelDerecho = new Panel(new BorderLayout(5, 5));
        areaDetalles = new TextArea();
        areaDetalles.setEditable(false);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botonCambiarEstado = new Button("Cambiar Estado");
        botonCerrar = new Button("Cerrar");

        botonCambiarEstado.addActionListener(e -> abrirVentanaCambioEstado());
        botonCerrar.addActionListener(e -> dispose());

        panelBotones.add(botonCambiarEstado);
        panelBotones.add(botonCerrar);

        panelDerecho.add(areaDetalles, BorderLayout.CENTER);
        panelDerecho.add(panelBotones, BorderLayout.SOUTH);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

        listaSolicitudes.addItemListener(e -> mostrarDetallesSolicitud());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void actualizarListaSolicitudes() {
        listaSolicitudes.removeAll();
        for (SolicitudCompra solicitud : gestor.getSolicitudes()) {
            listaSolicitudes.add("Solicitud " + solicitud.getNumero() +
                    " - Estado: " + solicitud.getEstado());
        }
    }

    private void mostrarDetallesSolicitud() {
        if (listaSolicitudes.getSelectedIndex() != -1) {
            String numeroSolicitud = listaSolicitudes.getSelectedItem()
                    .split(" ")[1];
            SolicitudCompra solicitud = gestor.buscarSolicitud(numeroSolicitud);
            if (solicitud != null) {
                StringBuilder detalles = new StringBuilder();
                detalles.append("Número de Solicitud: ").append(solicitud.getNumero())
                        .append("\nEstado actual: ").append(solicitud.getEstado())
                        .append("\n\nProductos:\n");

                solicitud.getProductos().forEach((producto, cantidad) ->
                        detalles.append("- ").append(producto.getNombre())
                                .append(" (Cantidad: ").append(cantidad)
                                .append(", Precio: $").append(producto.getPrecioUnitario())
                                .append(")\n")
                );

                detalles.append("\nTotal: $").append(solicitud.calcularCostoTotal());
                areaDetalles.setText(detalles.toString());
            }
        } else {
            areaDetalles.setText("");
        }
    }

    private void abrirVentanaCambioEstado() {
        if (listaSolicitudes.getSelectedIndex() == -1) {
            mostrarMensaje("Error", "Por favor, seleccione una solicitud");
            return;
        }

        String numeroSolicitud = listaSolicitudes.getSelectedItem().split(" ")[1];
        SolicitudCompra solicitud = gestor.buscarSolicitud(numeroSolicitud);
        if (solicitud != null) {
            VentanaSolicitud ventanaEstado = new VentanaSolicitud(gestor, solicitud);
            ventanaEstado.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    actualizarListaSolicitudes();
                    mostrarDetallesSolicitud();
                }
            });
            ventanaEstado.setVisible(true);
        }
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Dialog dialogo = new Dialog(this, titulo, true);
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