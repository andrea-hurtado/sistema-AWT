package ec.edu.ups.erp.model;

public class Producto implements Calculable{
    private String nombre;
    private double precioUnitario;
    private int cantidad;

    public Producto(String nombre, double precioUnitario, int cantidad) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getCantidad() { return cantidad; }

    @Override
    public double calcularCostoTotal() {
        return precioUnitario * cantidad;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precioUnitario + " x " + cantidad;
    }
}
