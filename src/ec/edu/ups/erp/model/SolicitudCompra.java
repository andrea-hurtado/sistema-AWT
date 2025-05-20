package ec.edu.ups.erp.model;

import java.util.HashMap;
import java.util.Map;

public class SolicitudCompra extends Documento implements Calculable {

    private EstadoSolicitud estado;
    // Mapa Producto → Cantidad
    private final Map<Producto, Integer> productos = new HashMap<>();

    public SolicitudCompra(String numero) {
        super(numero);
        this.estado = EstadoSolicitud.EN_REVISIÓN;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        productos.merge(producto, cantidad, Integer::sum);
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    @Override
    public double calcularCostoTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            total += entry.getKey().getPrecioUnitario() * entry.getValue();
        }
        return total;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Solicitud #" + getNumero() + " - Estado: " + estado);
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            System.out.println("Producto: " + entry.getKey().getNombre() + " | Cantidad: " + entry.getValue() + " | Precio unitario: $" + entry.getKey().getPrecioUnitario());
        }
        System.out.println("Total: $" + calcularCostoTotal());
    }

    @Override
    public String toString() {
        return "SolicitudCompra [Número=" + getNumero() + ", Estado=" + estado + ", Total=$" + calcularCostoTotal() + "]";
    }
}
