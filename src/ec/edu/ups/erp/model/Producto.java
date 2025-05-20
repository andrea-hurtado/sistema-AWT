package ec.edu.ups.erp.model;

public class Producto implements Calculable {
    private String id;
    private String nombre;
    private double precioUnitario;

    public Producto(String id, String nombre, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    @Override
    public double calcularCostoTotal() {
        return precioUnitario; // Costo unitario para un producto simple
    }

    @Override
    public String toString() {
        return "Producto [ID=" + id + ", Nombre=" + nombre + ", Precio Unitario=$" + precioUnitario + "]";
    }
}
