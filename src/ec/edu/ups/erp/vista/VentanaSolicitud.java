package ec.edu.ups.erp.vista;

import ec.edu.ups.erp.model.GestorCompras;
import ec.edu.ups.erp.model.SolicitudCompra;
import ec.edu.ups.erp.model.EstadoSolicitud;

import java.awt.*;
import java.awt.event.*;

public class VentanaSolicitud extends Frame {

    private GestorCompras gestor;
    private SolicitudCompra solicitud;
    private Label labelEstadoActual;
    private Checkbox checkSolicitada;
    private Checkbox checkRevision;
    private Checkbox checkAprobada;
    private Checkbox checkRechazada;
    private Button botonGuardar;
    private Button botonCancelar;

    public VentanaSolicitud(GestorCompras gestor, SolicitudCompra solicitud) {
        this.gestor = gestor;
        this.solicitud = solicitud;

        setTitle("Cambiar Estado de Solicitud");
        setSize(300, 250);
        setLayout(new FlowLayout());

        labelEstadoActual = new Label("Estado actual: " + solicitud.getEstado());
        add(labelEstadoActual);

        checkSolicitada = new Checkbox("SOLICITADA");
        checkRevision = new Checkbox("EN_REVISIÓN");
        checkAprobada = new Checkbox("APROBADA");
        checkRechazada = new Checkbox("RECHAZADA");

        EstadoSolicitud estadoActual = solicitud.getEstado();
        if (estadoActual == EstadoSolicitud.SOLICITADA) {
            checkSolicitada.setState(true);
        } else if (estadoActual == EstadoSolicitud.EN_REVISIÓN) {
            checkRevision.setState(true);
        } else if (estadoActual == EstadoSolicitud.APROBADA) {
            checkAprobada.setState(true);
        } else if (estadoActual == EstadoSolicitud.RECHAZADA) {
            checkRechazada.setState(true);
        }

        checkSolicitada.addItemListener(e -> desmarcarOtros(checkSolicitada));
        checkRevision.addItemListener(e -> desmarcarOtros(checkRevision));
        checkAprobada.addItemListener(e -> desmarcarOtros(checkAprobada));
        checkRechazada.addItemListener(e -> desmarcarOtros(checkRechazada));

        add(checkSolicitada);
        add(checkRevision);
        add(checkAprobada);
        add(checkRechazada);

        botonGuardar = new Button("Guardar");
        botonCancelar = new Button("Cancelar");
        add(botonGuardar);
        add(botonCancelar);

        botonGuardar.addActionListener(e -> cambiarEstado());
        botonCancelar.addActionListener(e -> dispose());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void desmarcarOtros(Checkbox seleccionado) {
        if (seleccionado != checkSolicitada) checkSolicitada.setState(false);
        if (seleccionado != checkRevision) checkRevision.setState(false);
        if (seleccionado != checkAprobada) checkAprobada.setState(false);
        if (seleccionado != checkRechazada) checkRechazada.setState(false);
    }

    private void cambiarEstado() {
        EstadoSolicitud nuevoEstado = null;

        if (checkSolicitada.getState()) {
            nuevoEstado = EstadoSolicitud.SOLICITADA;
        } else if (checkRevision.getState()) {
            nuevoEstado = EstadoSolicitud.EN_REVISIÓN;
        } else if (checkAprobada.getState()) {
            nuevoEstado = EstadoSolicitud.APROBADA;
        } else if (checkRechazada.getState()) {
            nuevoEstado = EstadoSolicitud.RECHAZADA;
        }

        if (nuevoEstado != null) {
            solicitud.setEstado(nuevoEstado);
            mostrarMensaje("Estado cambiado a: " + nuevoEstado);
            dispose();
        } else {
            mostrarMensaje("Por favor seleccione un estado");
        }
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