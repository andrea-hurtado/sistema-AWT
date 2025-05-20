package ec.edu.ups.erp.model;

import java.util.ArrayList;
import java.util.List;
public class SolicitudCompra extends Documento implements Calculable{
    private String departamento;
    private EstadoSolicitud estado;
    private List<Producto> productos;

    public SolicitudCompra(String codigo, String departamento) {
        super(codigo);
        this.departamento = departamento;
        this.estado = EstadoSolicitud.SOLICITADA;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void cambiarEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    @Override
    public double calcularCostoTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularCostoTotal();
        }
        return total;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Solicitud " + codigo + " (" + estado + ")");
        System.out.println("Departamento: " + departamento);
        System.out.println("Productos:");
        for (Producto p : productos) {
            System.out.println(" - " + p);
        }
        System.out.println("Total: $" + calcularCostoTotal());
    }
}

